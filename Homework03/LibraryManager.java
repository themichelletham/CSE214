package Homework03;

/**
 * Driver Class.
 * Allows the user to interact with the stack of books
 * 
 * @author Michelle Tham, SBU ID: 111810145
 * 
 * */

import java.util.Scanner;
import java.util.EmptyStackException;
import java.util.InputMismatchException;

public class LibraryManager {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		BooksStack stackingBooks = new BooksStack();
		boolean run = true;
		
		do {
			brief();
			System.out.print("Select an option: ");
			char option = input.next().toUpperCase().charAt(0);
			input.nextLine();
			
			switch(option) {
			
				/** 
				 * Case A: Add newBook to stack
				 * Prompt user for name, author, genre, yearPublished, ISBN, condition
				 * BookAlreadyExistsException is thrown if there is another book with same information
				 * */
				case 'A': 
					try {
						System.out.print("Enter the name: ");
						String name = input.nextLine();
						
						System.out.print("Enter the author: ");
						String author = input.nextLine();
						
						System.out.print("Enter the genre: ");
						String genre = input.nextLine();
						
						System.out.print("Enter the year it was published: ");
						int yearPublished = input.nextInt();
						
						System.out.print("Enter the ISBN Number: ");
						long ISBN = input.nextLong();
						
						System.out.print("Enter the condition: ");
						String condition = input.next().toUpperCase();
						
						stackingBooks.push(new Book(name, author, genre, yearPublished, ISBN, condition));
						
						System.out.println(name + " has been added!");
					} catch (BookAlreadyExistsException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
					System.out.println();
					break;
				
				/**
				 * Case R: Removes a book from stack
				 * Prompt user to enter the name of book
				 * EmptyStackException is thrown when stack is empty
				 * */
				case 'R':
					try {
						System.out.print("Enter the name of the book: ");
						String name = input.nextLine();
						
						BooksStack temp = stackingBooks.findBook(name);
						Book poppedBook = stackingBooks.pop();
						stackingBooks.reset(temp);
						System.out.println(poppedBook.getName() + " has been removed!");
					} catch (EmptyStackException e) {
						e.getMessage();
					}
					System.out.println();
					break;
				
				/**
				 * Case G: Returns the book on the top of the stack
				 * Prompt user to enter the name of book
				 * EmptyStackException is thrown when stack is empty
				 * */
				case 'G':
					try {
						System.out.print("Enter the name of the book: ");
						String name = input.nextLine();
						
						// originalLength keeps track on how many books and compares it later
						int originalLength = stackingBooks.size();
						BooksStack temp = stackingBooks.findBook(name);
						
						// If all of the books are in temp, then the needed book is not found
						if (originalLength == temp.size()) {
							System.out.println(name + " does not exist.");
						} else {
							System.out.println(stackingBooks.peek().toStringSentence());
						}
						// Put all of the books back into the original stack
						stackingBooks.reset(temp);
					} catch (EmptyStackException e) {
						e.getMessage();
					}
					System.out.println();
					
					break;
				
				/**
				 * Case P: Prints the stack of books in tabular format
				 * */
				case 'P':
					try {
						System.out.printf("\n%-25s | %-15s | %-15s | %-9s | %-12s | %-8s\n", 
								"Name", "Author", "Genre", "Year", "ISBN Number", "Condition");
						System.out.println("=====================================================================================================");
						BooksStack temp = new BooksStack();
						while (stackingBooks.getIndexOfTop() != -1) {
							System.out.println(stackingBooks.peek().toString());
							temp.push(stackingBooks.pop());
						}
						stackingBooks.reset(temp);
					} catch (BookAlreadyExistsException e) {
						e.getMessage();
					}
					System.out.println();
					System.out.println();
					
					break;
				
				/**
				 * Case S: Sorts the stack of books based on the user input
				 * Prompts user to enter a method, sorts
				 * */
				case 'S':
					System.out.println("Sorting Methods:");
					System.out.println("\t(N) - Name");
					System.out.println("\t(A) - Author");
					System.out.println("\t(G) - Genre");
					System.out.println("\t(Y) - Year");
					System.out.println("\t(C) - Condition");
					System.out.println("\t(I) - ISBN Number");
					
					System.out.print("Enter a sorting option: ");
					char sortOpt = input.next().toUpperCase().charAt(0);
					input.nextLine();
					stackingBooks.sort(sortOpt);
					System.out.print("The book stack has been sorted by");
					
					switch(sortOpt) {
						case 'N':
							System.out.println(" name.\n");
							break;
							
						case 'A':
							System.out.println(" author.\n");
							break;
							
						case 'G':
							System.out.println(" genre.\n");
							break;
							
						case 'Y':
							System.out.println(" year.\n");
							break;
							
						case 'C':
							System.out.println(" condition.\n");
							break;
						
						case 'I':
							System.out.println(" ISBN Number.\n");
							break; 
					}
					
					break;
				
				/**
				 * Case Q: Quits from program
				 * */
				case 'Q':
					System.out.println("Quitting...");
					input.close();
					run = false;
					break;
				
				/**
				 * Default statement, none of the selections were chosen
				 * */
				default:
					System.out.println("Invalid option! Try again.\n");
			}
		} while (run);
	}
	
	/** brief displays the menu to the user */
	public static void brief() {
		System.out.println("Menu:");
		System.out.println("\t(A) - Add Book");
		System.out.println("\t(R) - Remove Book");
		System.out.println("\t(G) - Get Book");
		System.out.println("\t(P) - Print Books");
		System.out.println("\t(S) - Sort Books");
		System.out.println("\t(Q) - Quit");
	}
}
