/**
 * CollegeManager will allow the user to interact with the database by 
 * listing the colleges, add to the list, remove, and retrieve colleges 
 * from the list.
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package ExtraCreditProject;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class CollegeManager {
	static CollegeDataManager collegeDataManager;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean run = true;		// Flag for do while loop
		
		System.out.println("Starting...\n");
		
		do {
			printMenu();
			System.out.print("Please select an option: ");
			char option = input.next().toUpperCase().charAt(0);
			
			switch(option) {
				case 'I':
					System.out.print("Please enter a location: ");
					String fileLocation = input.next();
					
					try {
						collegeDataManager.buildFromFile(fileLocation);
						System.out.println("Loading...");
						System.out.println("College data loaded Successfully!");
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'A':
					try {
						System.out.print("Please enter the name of the college: ");
						String name = input.nextLine();
						
						System.out.print("Please enter the location of the college: ");
						String location = input.nextLine();
						
						System.out.print("Please enter the most popular major of the college: ");
						String major = input.nextLine();
						
						System.out.print("Please enter the SAT Score: ");
						int satScore = input.nextInt();
						
						System.out.print("Please enter the GPA (4.0 Scale): ");
						double gpa = input.nextDouble();
						
						System.out.print("Please enter the percent admittance rate: ");
						int percentAdmit = input.nextInt();
						
						collegeDataManager.addCollege(new College(name, location, major, satScore, gpa, percentAdmit));
					} catch (CollegeAlreadyExistsException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'R':
					try {
						System.out.print("Please enter the name of the college: ");
						String collegeName = input.nextLine();
						input.nextLine(); // To make sure it follows the next line
					
						// Uses name to removeCollege
						collegeDataManager.removeCollege(collegeName);
						System.out.println(collegeName + " has been removed!");
					} catch (CollegeDoesNotExistException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'G':
					try {
						System.out.print("Please enter the name of the college: ");
						String n = input.next();
						input.hasNextLine();
						
						collegeDataManager.getCollege(n);
					} catch (CollegeDoesNotExistException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 'P':
					collegeDataManager.printTable();
					break;
					
					// Sorting Extra Credit
				case 'S':
					System.out.println("Please select by what: ");
					printSortMenu();
					System.out.print("Choose an option: ");
					char sortOpt = input.next().toUpperCase().charAt(0);
					collegeDataManager.sort(sortOpt);
					break;
				
				case 'Q':
					System.out.println("Sorry to see you go!");
					run = false;
					input.close();
					break;
			}
		} while (run);
	}
	
	public static void printMenu() {
		System.out.println("Menu: ");
		System.out.println("\t(I) - Import from File");
		System.out.println("\t(A) - Add College");
		System.out.println("\t(R) - Remove College");
		System.out.println("\t(G) - Get Statistics on College");
		System.out.println("\t(P) - Print Table");
		System.out.println("\t(S) - Sort"); // Extra Credit
		System.out.println("\t(Q) - Quit");
	}
	
	// Extra Credit
	public static void printSortMenu() {
		System.out.println("\t\t(N) - Name");
		System.out.println("\t\t(L) - Location");
		System.out.println("\t\t(M) - Major");
		System.out.println("\t\t(X) - SAT Score");
		System.out.println("\t\t(Y) - GPA");
		System.out.println("\t\t(Z) - Percent Admit");
	}
}
