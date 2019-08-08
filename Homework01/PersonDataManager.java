// Name: Michelle Tham
// SBU ID: 111810145
// Assignment 1

package Homework01;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PersonDataManager {
	Person[] people;
	int numPeople = 0;
	String[] description;
	
	public PersonDataManager() {
		people = new Person[0];
		description = new String[0];
	}
	
	public static PersonDataManager buildFromFile(String location) throws IllegalArgumentException {
		File file = new File(location);
		PersonDataManager pdm = new PersonDataManager();
		
		try {
			// Start array of people
			pdm.ensureCapacity(1);
			
			// Scan CSV for description and people
			Scanner scanCSV = new Scanner(file);
			String tableDescription = scanCSV.nextLine();
			pdm.description = tableDescription.split(",");
			
			for (int i = 0; i < pdm.description.length; i++) {
				pdm.description[i] = pdm.description[i].trim();
			}
			
			// Splits values of the Person Description (line in file)
			while (scanCSV.hasNextLine()) {
				String[] values = scanCSV.nextLine().split(",");
				
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].trim();
				}
				
				// Uses the individual values to make a Person object
				Person newPerson = new Person(values[0], values[1], Integer.parseInt(values[2]), 
						Double.parseDouble(values[3]), Double.parseDouble(values[4]));
				pdm.addPerson(newPerson);
			}
			scanCSV.close();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}  catch (PersonAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return pdm;
	}
	
	public void addPerson(Person newPerson) throws PersonAlreadyExistsException {
		boolean samePerson = false;
		int i = 0;
			
		while ((!samePerson) && (i < numPeople) && (numPeople > 0)) {
			// Checks if the person is the same in the array people
			if (this.people[i].equals(newPerson)) {
				samePerson = true;
				break;
			}
			
			i++;
		}
			
		if (samePerson) {
			throw new PersonAlreadyExistsException("Person already exists.");
		} else {
			if (numPeople == this.people.length) {
				ensureCapacity(numPeople * 2 + 1);
			}
			this.people[numPeople] = newPerson;
			numPeople++;
			trimToSize();
		}
	}
	
	public void getPerson(String name) throws PersonDoesNotExistException {
		int index = indexOf(name);
		
		if (index != -1) {
			System.out.println(people[index].toString());
		} else {
			throw new PersonDoesNotExistException("Person does not exist.");
		}
	}
	
	public void removePerson(String name) throws PersonDoesNotExistException {	
		int index = indexOf(name);
		
		if (index == -1) {
			throw new PersonDoesNotExistException("Person does not exist.");
		} else {
			// Shifts each value to the left and trims the array people by one
			arrayCopy(people, index + 1, people, index, people.length - 1);
			numPeople--;
			trimToSize();
		}
	}
	
	public void printTable() {
		sort();
		// Formatted table
		for (int i = 0; i < description.length; i++) {
			if (i != description.length - 1) {
				System.out.printf("%-16s|", description[i]);
			} else {
				System.out.printf("%-16s", description[i]);
			}
		}
		
		System.out.println();
		System.out.println("==================================================================================");
		
		for (int i = 0; i < people.length; i++) {
			if (people[i] != null) {
				System.out.printf("%-16s|%-16s|%-16d|%-16s|%-16s%n" ,people[i].getName(), people[i].getGender(),
						people[i].getAge(), Integer.toString(((int)people[i].getHeight() / 12)) + " feet "  + 
						Integer.toString(((int)people[i].getHeight() % 12)) + " inches", (int)people[i].getWeight() + " pounds");
			}
		}
	}
	
	// Method to send table for a new file
	public String getTable() {
		StringBuilder table = new StringBuilder();
		for (int i = 0; i < description.length; i++) {
			if (i != description.length - 1) {
				table.append(String.format("%-16s|", description[i]));
			} else {
				table.append(String.format("%-16s%n", description[i]));
			}
		}
		
		table.append("==================================================================================\n");
		
		for (int i = 0; i < people.length; i++) {
			if (people[i] != null) {
				table.append(String.format("%-16s|%-16s|%-16d|%-16s|%-16s%n" ,people[i].getName(), people[i].getGender(),
						people[i].getAge(), Integer.toString(((int)people[i].getHeight() / 12)) + " feet "  + 
						Integer.toString(((int)people[i].getHeight() % 12)) + " inches", (int)people[i].getWeight() + " pounds"));
			}
		}
		
		return table.toString();
	}
	
	
	// Helper method for getPerson and removePerson
	public int indexOf(String name) {
		for (int i = 0; i < numPeople; i++) {
			if (people[i].getName().equals(name)) {
				return i;
			}
		}
		// Person does not exist 
		return -1;
	}
	
	// Helper method for length of people
	public void ensureCapacity(int minCapacity) {
		if (people == null) {
			people = new Person[minCapacity];
		} else if (people.length < minCapacity) {
			Person[] biggerArray;
			biggerArray = new Person[minCapacity];
			arrayCopy(people, 0, biggerArray, 0, numPeople);
			people = biggerArray;
		}
	}
	
	public void ensureCapacity(Person[] p, int minCapacity) {
		if (p.length < minCapacity) {
			Person[] biggerArray;
			biggerArray = new Person[minCapacity];
			arrayCopy(p, 0, biggerArray, 0, numPeople);
			p = biggerArray;
		}
	}
	
	// Implementation that will copy the contents of an array to another
	public void arrayCopy(Person[] src, int srcPos, Person[] dest, int desPos, int length) {
		for (int i = desPos; i < length; i++, srcPos++) {
			dest[i] = src[srcPos];
		}
	}
	
	// Method for length of people = capacity of people
	public void trimToSize() {
		Person[] trimmedArray;
		if (people.length != numPeople) {
				trimmedArray = new Person[numPeople];  
				arrayCopy(people, 0, trimmedArray, 0, numPeople);
				people = trimmedArray;
		}
	}
	
	public void sort() {
		// Alphabetize 
		if (numPeople > 1) {
			for (int j = 0; j < numPeople; j++) {
				for (int k = 0; k < numPeople - j - 1; k++) {
					if (this.people[k].compareTo(this.people[k+1]) > 0) {
						Person temp = people[k];
						System.out.println(temp.getName());
						people[k] = people[k+1];
						people[k+1] = temp;
						// Save the index and break, once at correct position
						/*index = j - 1;
						break;*/
					}
				}
			}
			// Shifts everyone from the correct index to the right
			//arrayCopy(people, index, people, index + 1, numPeople - index);
		}
	}
}

// EXCEPTIONS
class PersonAlreadyExistsException extends Exception {
	public PersonAlreadyExistsException() {
		super();
	}
	
	public PersonAlreadyExistsException(String message) {
		super(message);
	}
}

class PersonDoesNotExistException extends Exception {
	public PersonDoesNotExistException() {
		super();
	}
	
	public PersonDoesNotExistException(String message) {
		super(message);
	}
}