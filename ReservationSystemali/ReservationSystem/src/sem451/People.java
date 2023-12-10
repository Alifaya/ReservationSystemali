package sem451;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class People implements FileNames, DataTasks{

	private Map<String, Person> people;
	public People() {
		people = new HashMap<>();
	}

	public int howManyPeople() {
		return people.size();
	}
	public void clearAllPeople() {
		people.clear();
	}
	public void printPeople() {
		for (Map.Entry<String, Person> e : people.entrySet()) 
		{
		      System.out.println(e.getValue());
		}
	}
	
	public boolean addPerson(Person p) {
		return addPerson(p.name(),p.getId(),p.getAge());
	}
	
	public boolean addPerson(String name, String id, int age) {
		if(people.containsKey(id)) 
		{
			System.out.println("Sorry, id is used! User cannot be created.");
			return false;
		}
		people.put(id, new Person(name,id,age));
		System.out.println(name+" has been added.");
		System.out.println("No. of users is: "+people.size());
		return true;
	}
	
	public boolean removePerson(String id) {
		Person p = people.remove(id);
		if(p==null) {
			System.out.println("Sorry, no such a user!");
			return false;
		}
		System.out.println(p.name()+" has been removed.");
		System.out.println("No. of users is: "+people.size());
		return true;
	}
	
	
	public Person getPersonById(String id) {
		Person p = people.get(id);
		if(p==null) {
			System.out.println("Sorry, no such a user!");
			return null;
		}
		return p;
	}

	
	public void loadDataFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
	    // Create a File object for the people file
	    File peopleFile = new File(PEOPLE_FILE);

	    // Check if the file exists
	    if (!peopleFile.exists()) {
	        System.out.println("For People, File Not Found to load!");
	        return;
	    }

	    // Try with resources to ensure proper resource management
	    try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(peopleFile))) {
	        // Cast and read the object from the file
	        people = (Map<String, Person>) o.readObject();

	        // Inform the user about successful data loading
	        System.out.println("Finished loading data from People file.");
	        System.out.println(people.size() + " person(s) imported.");
	    } catch (FileNotFoundException e) {
	        // Handle specific exception when file is not found
	        System.out.println("FileNotFoundException: " + e.getMessage());
	        throw e;  // Re-throwing the exception to maintain the method's contract
	    } catch (IOException e) {
	        // Handle IO exceptions (like issues with reading from the file)
	        System.out.println("IOException: " + e.getMessage());
	        throw e;  // Re-throwing the exception to maintain the method's contract
	    } catch (ClassNotFoundException e) {
	        // Handle exceptions related to class casting
	        System.out.println("ClassNotFoundException: " + e.getMessage());
	        throw e;  // Re-throwing the exception to maintain the method's contract
	    }
	}


	@Override
	public void saveDataToFile()  throws FileNotFoundException, IOException, Exception{
		if(people.isEmpty()) System.out.println("No users to save!");
		else 
		{
			ObjectOutputStream o;			
			o = new ObjectOutputStream(new FileOutputStream(PEOPLE_FILE));
			o.writeObject(people);
			o.close(); 
		}
	}
}
