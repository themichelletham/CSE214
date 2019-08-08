/**
 * Exception when the airplane class status is full
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework04;

public class ClassFullException extends Exception {
	public ClassFullException() {
		super();
	}
	
	public ClassFullException(String message) {
		super(message);
	}
}
