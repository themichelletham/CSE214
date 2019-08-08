/**
 * CollegeHashMap: Hashmap of colleges
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package ExtraCreditProject;
import java.util.HashMap;
public class CollegeHashMap {
	HashMap<String, College> hm;
	
	public CollegeHashMap() {
		hm = new HashMap<String, College>();
	}

	/**
	 * @return the hm
	 */
	public HashMap<String, College> getHm() {
		return hm;
	}

	/**
	 * @param hm the hm to set
	 */
	public void setHm(HashMap<String, College> hm) {
		this.hm = hm;
	}
	
	
}
