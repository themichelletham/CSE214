
/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework05;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		boolean run = true;
		
		System.out.println("Welcome to ARK Website Generator! What would you like to do today?");
		
		WebSite website = new WebSite();
		
		do {
			printMenu();
			System.out.print("Enter Selection: ");
			char selection = input.next().toUpperCase().charAt(0);
			
			switch(selection) {
				case 'I':
					System.out.print("Please enter the name of the .txt file: ");
					String txtFile = input.next();
					WebSite ws = new WebSite();
					
					try {
						System.out.println("Loading tree...");
						ws.buildTree(txtFile);
						System.out.println("The tree has been loaded.");
					} catch (FileNotFoundException e) {
						System.out.println("Incorrect File! Please try again.\n");
					}
					break;
			
				case 'A':
					System.out.print("What would you like to name the department?: ");
					String departmentName = input.nextLine();
					try {
						website.addDepartment(departmentName);
						System.out.println("The department has been added.");
					} catch (PageAlreadyExistsException e) {
						e.getMessage();
					}
					
					break;
					
				case 'R':
					//Removes the current Department
					try {
						website.removeDepartment();
					} catch (PageDoesNotExistException e) {
						e.getMessage();
					}
					
					break;
					
				case 'C':
					System.out.println("You are currently in " + website.getCursor());
					break;
				
				case 'G':
					System.out.println("Which department would you like to go to? : ");
					break;
				
				case 'H':
					break;
				
				case 'P':
					if (website.getHomepage() != null) {
						website.printFormat();
					} else {
						System.out.println("There is currently no Website's format to print.");
					}
					break;
					
				case 'E':
					website.setHomepage(null);
					System.out.println("The tree is now empty!");
					break;
					
				case 'Q':
					run = false;
					input.close();
					break;
				
				default:
					System.out.println("Incorrect selection. Try again!\n");
			}
			
		} while (run);
	}
	
	/**
	 * Prints menu
	 */
	public static void printMenu() {
		System.out.println("Menu: ");
		System.out.println("\t(I) - Import .txt File");
		System.out.println("\t(A) - Add Department");
		System.out.println("\t(R) - Remove Department");
		System.out.println("\t(C) - Current Department");
		System.out.println("\t(G) - Go to Sub-Department");
		System.out.println("\t(H) - Head Department");
		System.out.println("\t(P) - Print Format");
		System.out.println("\t(E) - Empty Tree");
		System.out.println("\t(Q) - Quit");
	}
}
