/**
 * Simulator class
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package Homework04;
import java.util.Scanner;
public class Simulator {
	private int numFlights;
	private int numArrived;
	private final int CAPACITY = 35;
	private double arrivalProbability;
	private Airplane[] airport;
	
	
	/**
	 * Constructor 
	 */
	public Simulator(int numFlights, int numArrived, double arrivalProbability) {
		this.numFlights = numFlights;
		this.numArrived = numArrived;
		this.arrivalProbability = arrivalProbability;
		this.airport = new Airplane[10];
	}
	
	/**
	 * @return the airport
	 */
	public Airplane[] getAirport() {
		return airport;
	}

	/**
	 * @param airport the airport to set
	 */
	public void setAirport(Airplane[] airport) {
		this.airport = airport;
	}

	/**
	 * @return the numFlights
	 */
	public int getNumFlights() {
		return numFlights;
	}

	/**
	 * @param numFlights the numFlights to set
	 */
	public void setNumFlights(int numFlights) {
		this.numFlights = numFlights;
	}

	/**
	 * @return the numArrived
	 */
	public int getNumArrived() {
		return numArrived;
	}

	/**
	 * @param numArrived the numArrived to set
	 */
	public void setNumArrived(int numArrived) {
		this.numArrived = numArrived;
	}

	/**
	 * @return the arrivalProbability
	 */
	public double getArrivalProbability() {
		return arrivalProbability;
	}

	/**
	 * @param arrivalProbability the arrivalProbability to set
	 */
	public void setArrivalProbability(double arrivalProbability) {
		this.arrivalProbability = arrivalProbability;
	}

	/**
	 * @return the CAPACITY
	 */
	public int getCAPACITY() {
		return CAPACITY;
	}
	
	/**
	 * Borrowed code from carWash simulator
	 */
	public void simulate(int duration) {
		Scanner input = new Scanner(System.in);
		// No simulation
		if (duration <= 0 || arrivalProbability < 0 || 
				arrivalProbability > 1) {
			System.out.println("NO SIMULATION");
			return;
		}
		
		// Checks whether or not a new passenger arrived
		BooleanSource arrival = new BooleanSource(arrivalProbability);
		
		for (double minute = 0; minute <= duration; minute += 0.5) {
			if (minute % 1 == 0) {
				System.out.println("\nMinute " + (int) minute + ":");
			}
			
			for (int plane = 0; plane < numFlights; plane++) {
				try {
					// Event: Has a person arrived?
					if (duration - 5 >= minute) {
						if (arrival.occurs()) {
							Passenger p = new Passenger(minute);
							p.setDestinLoc(this.airport[plane].getDestinationCity());
							this.airport[plane].enqueue(p);
						}
					}
				} catch (FullQueueException e) {
					e.getMessage();
				}
			}
			
			if (minute % 1 == 0) {
				System.out.println("There are " + this.numFlights + " airplanes at the airport.");
				for (int flight = 0; flight < numFlights; flight++) {
					System.out.println("Flight " + (flight + 1) + " is going to " + airport[flight].getDestinationCity() +
							" with " + airport[flight].getNumPeople() + " passengers.");
				}
				
				for (int flight = 0; flight < numFlights; flight++) {
					System.out.print((flight + 1) + " (" + airport[flight].getDestinationCity() + "): ");
					for (int person = 0; person < airport[flight].getNumPeople(); person++) {
						airport[flight].getLine()[person].toString();
					}
					System.out.println();
				}
			}
			
			// Every 20 minutes, a plane arrives
			if (minute % 20 == 0 && minute != 0) {
				System.out.println("A new flight has arrived. It will be refueling now!");
				numArrived++;
			} // After 10 minutes of refueling, plane is ready to take off
			
			// Planes are taking off : make objects null, numFlights to 0
			if (minute % 30 == 0 && minute != 0) {
				System.out.println("The " + this.numFlights + " flights are taking off.");
				numFlights = 0;
				
				if (minute % 5 == 0) {
					System.out.println("\nThe flight that returned is ready to depart.");
				
					System.out.print("Would you like to set a destination for it?: ");
					char flightSet = input.next().toUpperCase().charAt(0);
					input.nextLine();
				
					// Selection
					if (flightSet == 'Y') {
						numFlights++;
						System.out.print("Where would you like it to go?: ");
						String destination = input.nextLine();
						
						// Keep track where is the next available airplane index
						int i = 0;
						while (airport[i] == null && i < airport.length) {
							i++;
						}
						airport[i] = new Airplane(destination, (int) (duration - minute));
						numArrived--;
					} else if (flightSet == 'N') {
						System.out.println("Waiting for a plane to land...");
					} else {
						System.out.println("Invalid response.");
					}
				}
			}
		}
	}
}
