package Homework03;

/**
 * BookAlreadyExistsException is thrown when the Book
 * object already exists in the stack
 * 
 * @author Michelle Tham, SBU ID: 111810145
 * */

public class BookAlreadyExistsException extends Exception {
	/** Default no parameter Constructor. */
	public BookAlreadyExistsException() {
		super();
	}
	
	/**
	 * Specified Constructor.
	 * 
	 * @param String message as the message to be shown to user when thrown
	 * */
	public BookAlreadyExistsException(String message) {
		super(message);
	}
}
