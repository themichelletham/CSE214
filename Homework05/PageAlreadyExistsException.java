/**
 * SiteAlreadyExistsException
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package Homework05;
public class PageAlreadyExistsException extends Exception{
	public PageAlreadyExistsException() {
		super();
	}
	
	public PageAlreadyExistsException(String message) {
		super(message);
	}
}
