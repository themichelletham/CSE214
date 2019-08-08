/**
 * @author Michelle Tham, SBU ID: 111810145
 * Homework 6
 */
package Homework06;
import java.util.Comparator;
public class HeightComparator implements Comparator<Person>{
	public HeightComparator() {
		
	}
	
	public int compare(Person person1, Person person2) {
		return (int)(person1.getHeight() - person2.getHeight());
	}
}
