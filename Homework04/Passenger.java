/**
 * Passenger class represents the Passenger object in the airport
 * 
 * @author Michelle Tham, SBU ID: 111810145
 * 
 */
package Homework04;

public class Passenger {
	private String passenger;
	private String departLoc;
	private String destinLoc;
	private double timeArrived;
	private String travelClass;
	public static final String[] classes = {"FIRST", "BUSINESS", "PREMIUM ECONOMY", "ECONOMY"};
	private final String[] names = {"Andrew", "Andy", "Andrea", "Alexander", "Adam", "Ayaz", "Anna", "Anthony" , "Alexandra", "Adrian",
			"Ayla", "Alice", "Bart", "Beatrice", "Ben", "Bill", "Bruce ","Chris", "Caitlyn", "Cynthia", 
			"Carmen", "Dominque", "Dora", "Dorothy", "David", "Eldon", "Elice", "Frances", "Frank", "Fanny",
			"Greg", "George", "Garmain", "Helen", "Herman", "Henry", "Isabelle", "Jasmine", "Jahnavi", "Jordan",
			"Judy", "Jonathan", "James", "Julius", "Julia", "Jeffrey", "Jean", "Jake", "Jessica", "Josephine", 
			"Jacob", "Joanna", "Kaitlyn", "Kate", "Kristin", "Kimberly", "Kenneth", "Lysander", "Linda", "Michelle", 
			"Melanie", "Mickey", "Maureen", "Michael", "Mandy", "Mathew", "Nick", "Nathan", "Ophelia", "Owen",
			"Pamela", "Preston", "Peter", "Quintin", "Russ", "Rachel", "Roman", "Richard", "Steven", "Stephanie", 
			"Sara", "Sam", "Sean", "Stephen", "Steve", "Tristin", "Taika", "Theodore", "Tina", "Tabitha", 
			"Ursala", "Vincent", "Vance", "Valerie", "Vivian", "Will", "Xavier", "Yasmine", "Zachary", "Zayn"};
	
	/**
	 * Default constructor
	 */
	public Passenger(double minute) {
		passenger = names[(int) Math.random() * 101];
		departLoc = "NYC";
		destinLoc = null;
		timeArrived = minute;
		travelClass = classes[(int) Math.random() * 5];
	}
	
	/**
	 * @return the passenger
	 */
	public String getPassenger() {
		return passenger;
	}

	/**
	 * @param passenger the passenger to set
	 */
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	/**
	 * @return the departLoc
	 */
	public String getDepartLoc() {
		return departLoc;
	}

	/**
	 * @param departLoc the departLoc to set
	 */
	public void setDepartLoc(String departLoc) {
		this.departLoc = departLoc;
	}

	/**
	 * @return the destinationLoc
	 */
	public String getDestinationLoc() {
		return destinLoc;
	}

	/**
	 * @param destinationLoc the destinationLoc to set
	 */
	public void setDestinLoc(String destinLoc) {
		this.destinLoc = destinLoc;
	}
	
	/**
	 * @return the timeArrived
	 */
	public double getTimeArrived() {
		return timeArrived;
	}

	/**
	 * @param timeArrived the timeArrived to set
	 */
	public void setTimeArrived(double timeArrived) {
		this.timeArrived = timeArrived;
	}

	/**
	 * @return the travelClass
	 */
	public String getTravelClass() {
		return travelClass.toUpperCase();
	}

	/**
	 * @param travelClass the travelClass to set
	 */
	public void setTravelClass(String travelClass) {
		this.travelClass = travelClass.toUpperCase();
	}
	
	/**
	 * @return difference of index
	 * Compares based on travelClasss
	 */
	public int compareTo(Passenger otherP) {
		if (this.indexOf(this.getTravelClass(), classes) < otherP.indexOf(this.getTravelClass(), classes)) {
			return -1;
		} else if (this.indexOf(this.getTravelClass(), classes) > otherP.indexOf(this.getTravelClass(), classes)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * @return indexOf travelClass to compare with each other 
	 */
	public int indexOf(String travelClass, String[] classes) {
		for (int i = 0; i < classes.length; i++) {
			if (classes[i].equalsIgnoreCase(travelClass)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @return String representation of Passenger
	 */
	public String toString() {
		return "[" + this.getPassenger() + ", " + this.getTravelClass() + ", " + this.getTimeArrived() + " minutes]";
	}
}
