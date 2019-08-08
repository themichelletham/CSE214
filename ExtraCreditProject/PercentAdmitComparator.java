/**
 * PercentAdmitComparator Class implements Comparator
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package ExtraCreditProject;
import java.util.Comparator;
public class PercentAdmitComparator implements Comparator<College> {
	// Default Constructor
	public PercentAdmitComparator() {
		
	}
	
	/**
	 * Compares the Percent Admitted of the colleges
	 * 
	 * @param college1, first college to be compared
	 * @param college2, second college to be compared
	 * 
	 * @return int difference
	 */
	public int compare(College college1, College college2) {
		return college1.getPercentAdmit() - college2.getPercentAdmit();
	}
}
