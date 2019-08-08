/**
 * SATComparator Class implements Comparator
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package ExtraCreditProject;
import java.util.Comparator;
public class SATComparator implements Comparator<College> {
	// Default Constructor
	public SATComparator() {
		
	}
	/**
	 * Compares the SAT Scores of the colleges
	 * 
	 * @param college1, first college to be compared
	 * @param college2, second college to be compared
	 * 
	 * @return int difference
	 */
	public int compare(College college1, College college2) {
		return college1.getSatScore() - college2.getSatScore();
	}
}
