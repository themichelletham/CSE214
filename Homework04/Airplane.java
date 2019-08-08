
/**
 * Airline class
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package Homework04;
public class Airplane {
	private static final int CAPACITY = 35;
	private String departureCity = "NYC";
	private String destinationCity;
	private int timeToTakeOff;
	
	private Passenger[] line;
	private Passenger front;
	private Passenger rear;
	private int numPeople;
	private int numFirst;
	private int numBusiness;
	private int numPremEcon;
	private int numEcon;
	
	public Airplane() {
		departureCity = null;
		timeToTakeOff = -1;
		line = new Passenger[35];
		front = null;
		rear = front;
		numPeople = 0;
		numFirst = 0;
		numBusiness = 0;
		numPremEcon = 0;
		numEcon = 0;
	}
	
	public Airplane(String destinationCity, int timeToTakeOff) {
		this.destinationCity = destinationCity;
		this.timeToTakeOff = timeToTakeOff;
		line = new Passenger[35];
		front = null;
		rear = front;
		numPeople = 0;
		numFirst = 0;
		numBusiness = 0;
		numPremEcon = 0;
		numEcon = 0;
	}
	
	/**
	 * @return the departureAirport
	 */
	public String getDepartureCity() {
		return departureCity;
	}

	/**
	 * @param departureAirport the departureAirport to set
	 */
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	/**
	 * @return the destinationAirport
	 */
	public String getDestinationCity() {
		return destinationCity;
	}

	/**
	 * @param destinationAirport the destinationAirport to set
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	/**
	 * @return the timeToTakeOff
	 */
	public int getTimeToTakeOff() {
		return timeToTakeOff;
	}

	/**
	 * @param timeToTakeOff the timeToTakeOff to set
	 */
	public void setTimeToTakeOff(int timeToTakeOff) {
		this.timeToTakeOff = timeToTakeOff;
	}

	/**
	 * @return the capacity
	 */
	public static int getCapacity() {
		return CAPACITY;
	}
	
	/**
	 * @return the line
	 */
	public Passenger[] getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(Passenger[] line) {
		this.line = line;
	}

	/**
	 * @return the front
	 */
	public Passenger getFront() {
		return front;
	}

	/**
	 * @param front the front to set
	 */
	public void setFront(Passenger front) {
		this.front = front;
	}

	/**
	 * @return the rear
	 */
	public Passenger getRear() {
		return rear;
	}

	/**
	 * @param rear the rear to set
	 */
	public void setRear(Passenger rear) {
		this.rear = rear;
	}

	/**
	 * @return the numPeople
	 */
	public int getNumPeople() {
		return numPeople;
	}

	/**
	 * @param numPeople the numPeople to set
	 */
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
	
	/**
	 * @return the numFirst
	 */
	public int getNumFirst() {
		return numFirst;
	}

	/**
	 * @param numFirst the numFirst to set
	 */
	public void setNumFirst(int numFirst) {
		this.numFirst = numFirst;
	}

	/**
	 * @return the numBusiness
	 */
	public int getNumBusiness() {
		return numBusiness;
	}

	/**
	 * @param numBusiness the numBusiness to set
	 */
	public void setNumBusiness(int numBusiness) {
		this.numBusiness = numBusiness;
	}

	/**
	 * @return the numPremEcon
	 */
	public int getNumPremEcon() {
		return numPremEcon;
	}

	/**
	 * @param numPremEcon the numPremEcon to set
	 */
	public void setNumPremEcon(int numPremEcon) {
		this.numPremEcon = numPremEcon;
	}

	/**
	 * @return the numEcon
	 */
	public int getNumEcon() {
		return numEcon;
	}

	/**
	 * @param numEcon the numEcon to set
	 */
	public void setNumEcon(int numEcon) {
		this.numEcon = numEcon;
	}

	public void enqueue(Passenger p) throws FullQueueException {
		// Case: numPeople in line has room for more
		if (numPeople != line.length) {
			// Add person to next available spot in line
			if ((p.getTravelClass().equalsIgnoreCase("FIRST") && numFirst < 5) ||
					(p.getTravelClass().equalsIgnoreCase("BUSINESS") && numBusiness < 5) ||
					(p.getTravelClass().equalsIgnoreCase("PREMIUM ECONOMY") && numPremEcon < 10) ||
					(p.getTravelClass().equalsIgnoreCase("ECONOMY") && numEcon < 15)) {
				switch(p.getTravelClass()) {
					case "FIRST":
						numFirst++;
						break;
						
					case "BUSINESS":
						numBusiness++;
						break;
						
					case "PREMIUM ECONOMY":
						numPremEcon++;
						break;
					
					case "ECONOMY":
						numEcon++;
						break;
					
					default:
						throw new IllegalArgumentException();
				}
				
				line[numPeople] = p;
				if (numPeople == 1) {
					front = line[numPeople];
				}
				
				rear = line[numPeople++];
				sort();
			} else {
				throw new FullQueueException("Travel Class for this Queue is full");
			}
		} else {
			throw new FullQueueException("Queue is full.");
		}
	}
	
	/**
	 * @return first person in queue 
	 */
	public Passenger peek() throws EmptyQueueException {
		if (numPeople > 0) {
			return line[0];
		} else {
			throw new EmptyQueueException("Queue is empty.");
		}
	}
	
	/**
	 * @return numPeople in queue
	 */
	public int size() {
		return numPeople;
	}
	
	/**
	 * @return if queue is empty 
	 */
	public boolean isEmpty() {
		return (line[0] == null);
	}
	
	/**
	 * Insertion Sort
	 */
	public void sort() {
		for(int i = 1; i < numPeople; i++) {
			for(int j = i; j > 0; j--) {
				if(line[j].compareTo(line[j-1]) < 0) {
					Passenger temp = line[j];
					line[j] = line[j-1];
					line[j-1] = temp;
				}
			}
		}
	}
}
