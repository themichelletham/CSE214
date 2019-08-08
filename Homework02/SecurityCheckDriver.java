package Homework02;

import java.util.Scanner;

public class SecurityCheckDriver {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		SecurityCheck security = new SecurityCheck();
		boolean run = true;
		
		do {
			menu();
			System.out.print("Enter an option: ");
			String option = input.next().toUpperCase();
			
			switch (option) {
				case "A":
					System.out.print("Enter the number of people to be added: ");
					int numNew = input.nextInt();
					input.nextLine();
					
					for (int i = 0; i < numNew; i++) {
						System.out.print("Enter Person " + (i + 1) +
								"'s name and ticket number: ");
						String name = input.next();
						int ticketNo = input.nextInt();
						input.nextLine();
						security.addPerson(new Person (name, ticketNo));
					}
					break;
				
				case "R":
					System.out.print("Enter the number of people to be removed: ");
					int numRemove = input.nextInt();
					input.nextLine();
					
					int i = 0;
					while (i < numRemove) {
						System.out.print("Enter Person " + (i + 1) + "'s ticket number: ");
						int ticketNo = input.nextInt();
						try {
							if (security.getLineA().findTicket(ticketNo)) {
								security.getLineA().removePerson(ticketNo);
							} else if (security.getLineB().findTicket(ticketNo)) {
								security.getLineB().removePerson(ticketNo);
							} else {
								throw new personNotFoundException("Person not found.");
							}
						} catch (personNotFoundException e) {
							System.out.println(e.getMessage());
							continue;
						}
						i++;
					}
					break;
				
				case "P":
					security.printSecurityCheck();
					break;
				
				case "PR":
					security.printBackwardsSecurityCheck();
					break;
				
				case "Q":
					System.out.println("Quitting...");
					run = false;
					input.close();
					break;
				
				default:
					System.out.println("That is not a selection. Please try again!");
			}
			System.out.println();
		} while (run);

	}
	
	public static void menu() {
		System.out.println("Menu: ");
		System.out.println("\t (A) - Add Person");
		System.out.println("\t (R) - Remove Person");
		System.out.println("\t (P) - Print Lines");
		System.out.println("\t (PR) - Print Lines in Reverse Order");
		System.out.println("\t (Q) - Quit");
	}
}
