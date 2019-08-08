package Homework06;
import java.util.HashMap;

/**
 * PersonDataHashMap Class
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 * Homework 6
 */

 class PersonDataHashMap{
	HashMap<String, Person> hm;
	
	public PersonDataHashMap() {
		 hm = new HashMap<String, Person>();
	}
	
	/**
	 * @return the hm
	 */
	public HashMap<String, Person> getHm() {
		return hm;
	}

	/**
	 * @param hm the hm to set
	 */
	public void setHm(HashMap<String, Person> hm) {
		this.hm = hm;
	}

}
