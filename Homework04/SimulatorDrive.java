package Homework04;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main method that runs the airport
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
public class SimulatorDrive {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
			
		try {
			System.out.println("Welcome to ARK Private International Airport!\n");
			
			System.out.print("Enter the number of planes waiting to take off: ");
			int numPlanes = input.nextInt();
			input.nextLine();
			
			System.out.print("Enter the arrival probability: ");
			double arrivalProbability = input.nextDouble();
			input.nextLine();
	
			System.out.print("Enter the duration of the simulation: ");
			int duration = input.nextInt();
			input.nextLine();
			
			Simulator airport = new Simulator(numPlanes, 0, arrivalProbability);
			
			for (int i = 0; i < numPlanes; i++) {
				System.out.print("Enter the destination of Flight " + (i+1) + ": ");
				airport.getAirport()[i] = new Airplane(input.nextLine(), duration);
			}
			airport.simulate(duration);
			
			System.out.println("\nThank you for working with us. If you need " +
					"more updates, please let us know!\n Good bye!");
		} catch (InputMismatchException e) {
			e.getMessage();
		}
	}
}
