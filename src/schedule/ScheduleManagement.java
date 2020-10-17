package schedule;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//Ditmar|ditmarcastro@gmail.com|33
//Vany|vany@gmail.com|30
public class ScheduleManagement {
	private HashMap<String, Contact> mainlist;
	private String path = "/Users/Ditmar/progra2/files/db.txt";
	public ScheduleManagement() {
		mainlist = new HashMap<>();
		loadHashMap();
	}
	private  void loadHashMap () {
		mainlist.clear();
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			String cad;
			while ((cad = bf.readLine()) != null) {
				Contact contact = new Contact();
				String[] params = cad.split("[|]");
				contact.setName(params[0]);
				contact.setEmail(params[1]);
				contact.setOld(Integer.parseInt(params[2].trim()));
				mainlist.put(contact.getEmail(), contact);
			}
		} catch (IOException error) {
			error.printStackTrace();
		}
		
	}
	public void addContact (String name, String email, Integer old) {
		Contact contact = new Contact();
		contact.setEmail(email);
		contact.setName(name);
		contact.setOld(old);
		try {
			FileOutputStream fos = new FileOutputStream(path, true);
			PrintStream ouput = new PrintStream(fos);
			ouput.println(contact.getName() + "|" + contact.getEmail()+"|" + contact.getOld());
		}catch (IOException error) {
			error.printStackTrace();
		}
		loadHashMap();
	}
	public ArrayList<Contact> search(String email) {
		Collection<Contact> auxlist = mainlist.values();
		ArrayList<Contact> result = new ArrayList<>();
		for (Contact item: auxlist) {
			if (item.getEmail().contains(email)) {
				result.add(item);
			}
		}
		return result;
	}
	public void delete(String email) {
		mainlist.remove(email);
	}
	public void showAll() {
		Collection<Contact> auxlist = mainlist.values();
		int number = 1;
		for (Contact item: auxlist) {
			System.out.println(number + ".- " + item.getName() + " Email " + item.getEmail() + " Edad " + item.getOld());
			number++;
		}
	}
	public Contact[] filter(int old) {
		ArrayList<Contact> aux = new ArrayList(); 
		mainlist.forEach((k, v) -> {
			if (v.getOld() <= old) {
				aux.add(v);
			}
		});
		return aux.toArray(new Contact[0]);
	} 
	public Boolean ifExist(String name) {
		Stream<Contact> st = mainlist.values().stream();
		return st.anyMatch(n -> n.getName().equals(name));
	}
	public Contact getMaxAge() {
		Stream<Contact> st = mainlist.values().stream();
		return st.max((a, b) -> a.getOld() - b.getOld()).get();
	}
	public Contact[] searchContactByName(String name) {
		Stream<Contact> st = mainlist.values().stream();
		return st.filter(n -> n.getName().contains(name)).toArray(n -> new Contact[n]);
	}
	public String[] getOnlyNames() {
		Stream<Contact> st = mainlist.values().stream();
		return st.map(n -> n.getName()).toArray(n -> new String[n]);
	}
}
