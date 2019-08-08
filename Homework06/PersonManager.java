package Homework06;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
public class PersonManager {
	static PersonDataManager pdm = new PersonDataManager();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		boolean run = true;
		System.out.println("Starting...\n");
		
		do {
			printMenu();
			System.out.print("Please select an option: ");
			char option = input.next().toUpperCase().charAt(0);
			
			switch(option) {
				case 'I':
					System.out.print("Please enter a location: ");
					String location = input.next();
					try {
						pdm.buildFromFile(location);
						System.out.println("Loading...");
						System.out.println("Person data loaded successfully!");
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				
				case 'A':
					try {
						System.out.print("Please enter the name of the person: ");
						String name = input.nextLine();
						
						System.out.print("Please enter the age: ");
						int age = input.nextInt();
						
						System.out.print("Please enter the gender (M or F): ");
						String gender = input.next().toUpperCase();
						input.nextLine(); // To make sure it follows the next line
						
						System.out.print("Please enter the height (in inches): ");
						double height = input.nextDouble();
						
						System.out.print("Please enter the weight (in pounds): ");
						double weight = input.nextDouble();
						
						pdm.add(name, new Person(name, gender, age, height, weight));
					} catch (PersonAlreadyExistsException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'R':
					try {
						System.out.print("Please enter the name of the person: ");
						String personName = input.next();
						input.nextLine(); // To make sure it follows the next line
					
						// Uses name to removePerson
						pdm.remove(personName);
						System.out.println(personName + " has been removed!");
					} catch (PersonDoesNotExistException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'G':
					try {	
						System.out.print("Please enter the name of the person: ");
						String n = input.next();
						input.nextLine(); // To make sure it follows the next line
					
						pdm.get(n);
					} catch (PersonDoesNotExistException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'P':
					pdm.printTable();
					break;
					
				case 'S':
					System.out.println("Please select by what:");
					printSortMenu();
					System.out.print("Choose an option: ");
					char sortOpt = input.next().toUpperCase().charAt(0);
					pdm.sort(sortOpt);
					break;
					
				case 'Q':
					System.out.println("Sorry to see you go!");
					run = false;
					input.close();
					break;
					
				default:
					System.out.println("That is not a selection. Please try again!");
			}
			System.out.println();
		} while (run);
	}
	
	public static void printMenu() {
		System.out.println("Menu: ");
		System.out.println("\t(I) - Import from File");
		System.out.println("\t(A) - Add Person");
		System.out.println("\t(R) - Remove Person");
		System.out.println("\t(G) - Get Information on Person");
		System.out.println("\t(P) - Print Table");
		System.out.println("\t(S) - Sort");
		System.out.println("\t(Q) - Quit\n");
	}
	
	public static void printSortMenu() {
		System.out.println("\t\t(N) - Name");
		System.out.println("\t\t(W) - Weight");
		System.out.println("\t\t(H) - Height\n");
	}
}
