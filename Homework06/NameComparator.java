
/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework06;
import java.util.Comparator;
public class NameComparator implements Comparator<Person>{
	public NameComparator() {
		
	}

	public int compare(Person person1, Person person2) {
		return person1.compareTo(person2);
	}
}
