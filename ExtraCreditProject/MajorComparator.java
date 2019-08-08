
package ExtraCreditProject;
import java.util.Comparator;

/**
 * MajorComparator Class implements Comparator
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
public class MajorComparator implements Comparator<College>{
	// Default Constructor
	public MajorComparator() {
		
	}
	
	/**
	 * Compares the most popular majors of the colleges
	 * 
	 * @param college1, first college to be compared
	 * @param college2, second college to be compared
	 * 
	 * @return int difference
	 */
	public int compare(College college1, College college2) {
		return college1.getMajor().compareTo(college2.getMajor());
	}
}
