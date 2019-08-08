
/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package Homework06;
import java.util.Comparator;
public class WeightComparator implements Comparator<Person>{
	public WeightComparator() {
		
	}
	
	public int compare(Person person1, Person person2) {
		return (int)(person1.getWeight() - person2.getWeight());
	}
}
