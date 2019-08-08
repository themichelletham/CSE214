

/**
 * @author Michelle Tham, SBU ID: 111810145
 * Homework 6
 */

package Homework06;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PersonDataManager {
	private int numPeople = 0;
	private ArrayList<Person> peopleList;
	private String[] description;
	private static PersonDataManager pdm;
	private static PersonDataHashMap pdhm;
	
	public PersonDataManager() {
		numPeople = 0;
		peopleList = new ArrayList<>();
		description = new String[0];
	}
	
	/**
	 * @return the numPeople
	 */
	public int getNumPeople() {
		return numPeople;
	}

	/**
	 * @param numPeople the numPeople to set
	 */
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	/**
	 * @return the peopleList
	 */
	public ArrayList<Person> getPeopleList() {
		return peopleList;
	}

	/**
	 * @param peopleList the peopleList to set
	 */
	public void setPeopleList(ArrayList<Person> peopleList) {
		this.peopleList = peopleList;
	}

	/**
	 * @return the description
	 */
	public String[] getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String[] description) {
		this.description = description;
	}

	/**
	 * @return the pdm
	 */
	public static PersonDataManager getPdm() {
		return pdm;
	}

	/**
	 * @param pdm the pdm to set
	 */
	public static void setPdm(PersonDataManager pdm) {
		PersonDataManager.pdm = pdm;
	}

	/**
	 * @return the pdhm
	 */
	public static PersonDataHashMap getPdhm() {
		return pdhm;
	}

	/**
	 * @param pdhm the pdhm to set
	 */
	public static void setPdhm(PersonDataHashMap pdhm) {
		PersonDataManager.pdhm = pdhm;
	}
	
	/**
	 * @param location, the location of the file
	 * @return PersonDataHashMash
	 * @throws IllegalArgumentException, FileNotFoundException
	 */
	public static PersonDataHashMap buildFromFile(String location)
		throws IllegalArgumentException, FileNotFoundException {
		pdm = new PersonDataManager();
		pdhm = new PersonDataHashMap();
		try {
			File file = new File(location);
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
				pdm.add(values[0], newPerson);
			}
			scanCSV.close();
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (PersonAlreadyExistsException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return pdhm;
	}
	
	/**
	 * @param name, name of the Person
	 * @param newPerson, newPerson
	 * @throws PersonAlreadyExistsException when person already exists in list
	 */
	public void add(String name, Person newPerson) throws PersonAlreadyExistsException {
		boolean samePerson = false;
		int i = 0;
		
		while ((!samePerson) && (i < numPeople) && (numPeople > 0)) {
			// Checks if the person is the same in the array people
			if (this.peopleList.get(i).equals(newPerson)) {
				samePerson = true;
				break;
			}
			
			i++;
		}

		if (samePerson) {
			throw new PersonAlreadyExistsException("Person already exists.");
		} else {
			this.peopleList.add(newPerson);
			pdhm.getHm().put(name, newPerson);
			numPeople++;
		}
	}
	
	/**
	 * @param name, the name of the person
	 * @throws PersonDoesNotExistsException when person does not exist in list
	 */
	public void get(String name) throws PersonDoesNotExistException {
		System.out.println(pdhm.getHm().get(name).toString());
	}
	
	/**
	 * @param name, the name of the person
	 * @throws PersonDoesNotExistException when person does not exist in list
	 */
	public void remove(String name) throws PersonDoesNotExistException {
		int index = peopleList.indexOf(name);
		
		if (index == -1) {
			throw new PersonDoesNotExistException("Person does not exist.");
		} else {
			// Shifts each value to the left and trims the array people by one
			arrayCopy(peopleList, index + 1, peopleList, index, peopleList.size() - 1);
			numPeople--;
		}
		
		pdhm.getHm().remove(name);
	}
	
	/**
	 * Prints people table
	 */
	public void printTable() {
		for (int i = 0; i < description.length; i++) {
			if (i != description.length - 1) {
				System.out.printf("%-16s|", description[i]);
			} else {
				System.out.printf("%-16s", description[i]);
			}
		}
		
		System.out.println();
		System.out.println("==================================================================================");
		
		for (int i = 0; i < peopleList.size(); i++) {
			if (peopleList.get(i) != null) {
				System.out.printf("%-16s|%-16s|%-16d|%-16s|%-16s%n", peopleList.get(i).getName(), peopleList.get(i).getGender(),
						peopleList.get(i).getAge(), Integer.toString(((int)peopleList.get(i).getHeight() / 12)) + " feet "  + 
						Integer.toString(((int)peopleList.get(i).getHeight() % 12)) + " inches", (int)peopleList.get(i).getWeight() + " pounds");
			}
		}
	}
	
	/**
	 * Sort by either name, weight, or height
	 */
	public void sort(char sortOpt) {
		switch (sortOpt) {
			case 'N':
				Collections.sort(peopleList, new NameComparator());
				break;
			
			case 'W':
				Collections.sort(peopleList, new WeightComparator());
				break;
			
			case 'H':
				Collections.sort(peopleList, new HeightComparator());
				break;
				
			default:
				System.out.println("Not a sorting option.");
		}
	}
	
	public void arrayCopy(ArrayList<Person> src, int srcPos, ArrayList<Person> dest, int desPos, int length) {
		for (int i = desPos; i < length; i++, srcPos++) {
			dest.set(i, src.get(srcPos));
		}
	}
 }
