/* 
* Name: Michelle Tham
* SBU ID: 111810145
* CSE 214 Recitation 09
* Assignment 1
*/ 

package Homework01;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class PersonManager {
	static PersonDataManager personDataManager;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Starting...");
		
		// Variable run to continue the selection
		boolean run = true;
		do {
			printMenu();
			
			System.out.print("Please select an option: ");
			char option = input.next().toUpperCase().charAt(0);
			
			switch(option) {
				case 'I': // Import from File
					System.out.print("Please enter a location: ");
					String location = input.next();
					
					try {
						personDataManager = personDataManager.buildFromFile(location);
						System.out.println("Loading...");
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}

					System.out.println("Person data loaded successfully!");
					// Extra Credit Big O Notation
					System.out.println("This method's Big(O) Notation was O(N²)");
					
					break;
					
				case 'A': // Add Person
					try {
						// Prompt user to enter data about the person
						System.out.print("Please enter the name of the person: ");
						String name = input.next();
						input.nextLine(); // To make sure it follows the next line
						
						System.out.print("Please enter the age: ");
						int age = input.nextInt();
						
						System.out.print("Please enter the gender (M or F): ");
						String gender = input.next().toUpperCase();
						input.nextLine(); // To make sure it follows the next line
						
						System.out.print("Please enter the height (in inches): ");
						double height = input.nextDouble();
						
						System.out.print("Please enter the weight (in pounds): ");
						double weight = input.nextDouble();
					
						// Uses data to addPerson
						personDataManager.addPerson(new Person(name, gender, age, height, weight));
						System.out.println(name + " has been added to the list!");
						
						/* Check the people array
						for (int i = 0; i < personDataManager.people.length; i++) {
							System.out.println(personDataManager.people[i]);
						}
						*/
						
						// Extra Credit Big O Notation
						System.out.println("This method's Big(O) Notation was O(N)");
					} catch (PersonAlreadyExistsException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
				case 'R': // Remove Person
					try {
						System.out.print("Please enter the name of the person: ");
						String personName = input.next();
						input.nextLine(); // To make sure it follows the next line
					
						// Uses name to removePerson
						personDataManager.removePerson(personName);
						System.out.println(personName + " has been removed!");
						// Extra Credit
						System.out.println("This method's Big(O) Notation was O(N²)");
					} catch (PersonDoesNotExistException e) {
						System.out.println(e.getMessage());
						
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
				case 'G': // Get Information on Person
					try {	
						System.out.print("Please enter the name of the person: ");
						String n = input.next();
						input.nextLine(); // To make sure it follows the next line
					
						personDataManager.getPerson(n);
					} catch (PersonDoesNotExistException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					
					// Extra Credit
					System.out.println("This method's Big(O) Notation was O(N)");
					
					break;
					
				case 'P': // Print Table
					personDataManager.printTable();
					
					// Extra Credit
					System.out.println("This method's Big(O) Notation was O(N)");
					
					break;
				
				case 'S': // Save to File
					System.out.print("Please select a name for the file: ");
					String file = input.next();
					
					try {
						File newFile = new File(file);
						PrintWriter writer = new PrintWriter(newFile);
						writer.print(personDataManager.getTable());
						writer.close();
						
					} catch (IOException e) {
						e.getMessage();
					}
					
					System.out.println("A file named " + file + " has been created!");
					
					// Extra Credit
					System.out.println("This method's Big(O) Notation was O(N)");
					
					break;
					
				case 'Q':
					System.out.println("Sorry to see you go!");
					
					// Extra Credit
					System.out.println("This method's Big(O) Notation was O(1)");
					
					run = false;
					input.close();
					break;
					
				default:
					System.out.println("That is not a selection. Please try again!");
			}
			System.out.println();
		} while(run);
	}
	
	public static void printMenu() {
		System.out.println("Menu: ");
		System.out.println("\t(I) - Import from File");
		System.out.println("\t(A) - Add Person");
		System.out.println("\t(R) - Remove Person");
		System.out.println("\t(G) - Get Information on Person");
		System.out.println("\t(P) - Print Table");
		System.out.println("\t(S) - Save to File");
		System.out.println("\t(Q) - Quit\n");
	}
}
