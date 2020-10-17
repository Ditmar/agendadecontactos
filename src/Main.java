import java.util.ArrayList;
import java.util.Scanner;

import schedule.Contact;
import schedule.ScheduleManagement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		ScheduleManagement schedule = new ScheduleManagement();
		Integer option = -1;
		do {
			
			System.out.println("1.- Anadir Contacto");
			System.out.println("2.- Buscar Contacto");
			System.out.println("3.- Eliminar Contacto");
			System.out.println("4.- Mostrar Todos");
			System.out.println("5.- Filtro de contactos");
			System.out.println("6.- Existe el contacto");
			System.out.println("7.- Quien es el mayor");
			System.out.println("8.- Busqueda por nombre");
			System.out.println("9.- recucuperar solo nombres");
			System.out.println("10.- Salir");
			option = Integer.parseInt(keyboard.nextLine());
			switch(option) {
				case 1: {
					System.out.println("Ponga su nombre");
					String name = keyboard.nextLine();
					System.out.println("Ponga su email");
					String email = keyboard.nextLine();
					System.out.println("Ponga su edad");
					Integer old = Integer.parseInt(keyboard.nextLine().trim());
					schedule.addContact(name, email, old);
					break;
				}
				case 2: {
					System.out.println("Busqueda por email, escriba el email");
					String email = keyboard.nextLine();
					ArrayList<Contact> contact = schedule.search(email);
					if (contact.size() > 0) {
						int number = 0;
						for (Contact item : contact) {
							System.out.println(number + ".- " + item.getName() + " Email " + item.getEmail() + " Edad " + item.getOld());
							number++;
						}
					} else {
						System.out.println("No hay Resultados");
					}
					
					break;
				}
				case 3: {
					System.out.println("Eliminacion por email, escriba el email");
					String email = keyboard.nextLine();
					schedule.delete(email);
					break;
				}
				case 4: {
					schedule.showAll();
					break;
				}
				case 5: {
					
					Contact[] data = schedule.filter(50);
					for (int i = 0; i < data.length; i++) {
						System.out.println(data[i].getName() +  " Edad = " + data[i].getOld());
					}
					break;
				}
				case 6: {
					System.out.println("ponga el nombre para buscar");
					String name = keyboard.nextLine();
					if(schedule.ifExist(name)) {
						System.out.println("Si existe!");
					} else {
						System.out.println("No existe!");
					};
					break;
				}
				case 7: {
					Contact contact = schedule.getMaxAge();
					System.out.print("El Contacto más viejo es " + contact.getName() +  " Edad = " + contact.getOld());
					break;
				}
				case 8: {
					System.out.println("ponga el nombre para buscar");
					String name = keyboard.nextLine();
					Contact[] contact = schedule.searchContactByName(name);
					for (int i = 0; i < contact.length; i++) {
						System.out.println(contact[i].getName() +  " Edad = " + contact[i].getOld());
					}
					break;
				}
				case 9: {
					String[] names = schedule.getOnlyNames();
					for (int i = 0; i < names.length; i++) {
						System.out.println("Nombres -> " + names[i]);
					}
					break;
				}
				
				
			}
			
		} while (option != 10);
	}

}
