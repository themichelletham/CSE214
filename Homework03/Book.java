package Homework03;

/**
 * Book class contains information about the Book
 * Variables include the Book's name, year of publication, genre,
 * condition, ISBN number
 * It will implement the toString() method to print the description
 * 
 * 
 * @author Michelle Tham, SBU ID: 111810145
 * 
 */

public class Book {
	private long ISBN;
	private int yearPublished;
	private String name;
	private String author;
	private String genre;
	private Condition condition;
	
	/**
	 * No parameter constructor.
	 * Every data member is set to null or -1.
	 * */
	public Book() {
		ISBN = -1;
		yearPublished = -1;
		name = null;
		author = null;
		genre = null;
		condition = null;
	}
	
	/**
	 * Specified Constructor.
	 * 
	 * @param ISBN Number identification
	 * @param yearPublished date
	 * @param name title
	 * @param author
	 * @param genre
	 * @param condition to identify which condition enum
	 */
	public Book(String name, String author, String genre, int yearPublished, long ISBN, String condition) {
		this.ISBN = ISBN;
		this.yearPublished = yearPublished;
		this.name = name;
		this.author = author;
		this.genre = genre;
		
		/* Matches which condition is given in the parameter */
		if (condition.toUpperCase().equals("OLD")) {
			this.condition = Condition.OLD;
		} else if (condition.toUpperCase().equals("NEW")) {
			this.condition = Condition.NEW;
		}
	}
	
	/** @return Book's ISBN Number */
	public long getISBN() {
		return ISBN;
	}
	
	/** @param ISBN the ISBN to set */
	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}
	
	/** @return Book's year of publication */
	public int getYearPublished() {
		return yearPublished;
	}
	
	/** @param yearPublished the yearPublished to set */
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	
	/** @return Book's name */
	public String getName() {
		return name;
	}
	
	/**  @param name the name to set */
	public void setName(String name) {
		this.name = name;
	}
	
	/** @return Book's author */
	public String getAuthor() {
		return author;
	}
	
	/** @param author the author to set */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/** @return Book's genre */
	public String getGenre() {
		return genre;
	}
	
	/** @param genre the genre to set */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/** @return Book's condition */
	public Condition getCondition() {
		return condition;
	}
	
	/**  @param condition sets the condition (enum) of the Book */
	public void setCondition(String condition) {
		if (condition.toUpperCase().equals("OLD")) {
			this.condition = Condition.OLD;
		} else if (condition.toUpperCase().equals("NEW")) {
			this.condition = Condition.NEW;
		}
	}
	
	/** 
	 * @Overrides toString method
	 * @return String of data members in tabular format
	 * In the order: name, author, genre, yearPublished, ISBN, condition
	 * */
	public String toString() {
		return String.format("%-25s | %-15s | %-15s | %-9d | %-12d | %-8s",
				this.name, this.author, this.genre, this.yearPublished, this.ISBN, this.condition);
	}
	
	/**
	 * @return String of data members in sentence format
	 * */ 
	public String toStringSentence() {
		return this.name + " was written by " + this.author + " in the year " + this.yearPublished +
				". It is of the " + this.genre + " genre. The ISBN Number is " + this.ISBN +
				" and it is " + this.condition;
	}
	
	/**
	 * equals checks each data member in each book are the same
	 * @return true if all same, false if not
	 * */
	public boolean equals(Book newBook) {
		if ((this.getName().equalsIgnoreCase(newBook.getName()) && this.getAuthor().equalsIgnoreCase(newBook.getAuthor()) &&
				this.getGenre().equalsIgnoreCase(newBook.getGenre()) && this.getISBN() == newBook.getISBN()) &&
				this.getYearPublished() == newBook.getYearPublished() && this.getCondition().equals(newBook.getCondition())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * compareTo compares two Strings
	 * @param newS is the String being compared to the Book object
	 * @param method is the method the user wishes the stack should be sorted
	 * 
	 * uses the method to see which String to be used
	 * @return int -- difference between the two strings
	 * */
	public int compareTo(String newS, char method) {
		String s = "";
		switch (method) {
			case 'N':
				s = this.getName();	
				break;
				
			case 'A':
				s = this.getAuthor();
				break;
				
			case 'G':
				s = this.getGenre();
				break;
				
			case 'Y':
				s = this.getYearPublished() + "";
				break;
				
			case 'C':
				s = this.getCondition() + "";
				break;
			
			case 'I':
				s = this.getISBN() + "";
				break;
		}
		
		// Compares each character in each String
		int minLength = Math.min(s.length(), newS.length());
		for (int i = 0; i < minLength; i++) {
			if (s.charAt(i) != newS.charAt(i)) {
				return (int) (s.charAt(i) - newS.charAt(i));
			}
		}
		return 0;
	}
}