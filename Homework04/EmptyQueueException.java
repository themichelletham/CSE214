/**
 * emptyQueueException
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework04;
public class EmptyQueueException extends Exception {
	public EmptyQueueException() {
		super();
	}
	
	public EmptyQueueException(String message) {
		super(message);
	}
}
