/**
 * Borrowed BooleanSource from Car Simulator
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework04;
public class BooleanSource {
	private double probability;
	
	public BooleanSource(double p) throws IllegalArgumentException {
		if (p < 0.0 || p > 1.0) {
			throw new IllegalArgumentException();
		}
		probability = p;
	}
	
	public boolean occurs() {
		return (Math.random() < probability);
	}
}
