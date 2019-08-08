
package ExtraCreditProject;
import java.util.Comparator;

/**
 * GPAComparator Class implements Comparator
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
public class GPAComparator implements Comparator<College> {
	// Default Constructor
	public GPAComparator() {
		
	}
	
	/**
	 * Compares the average GPA of the colleges
	 * 
	 * @param college1, first college to be compared
	 * @param college2, second college to be compared
	 * 
	 * @return int difference
	 */
	public int compare(College college1, College college2) {
		return (int)(college1.getGpa() - college2.getGpa());
	}
}
