/**
 * College Class contains academic statistics of each person in the .csv file
 * provided. The College class should contain variables for the college’s name, 
 * location, most popular major, average total SAT score, average GPA, and 
 * percentage admitted. In addition, it should also implement the toString() 
 * method, which should print all of the student’s data in tabular form.
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */

package ExtraCreditProject;

public class College {
	private String name;
	private String location;
	private String major;
	private int satScore;
	private double gpa;
	private int percentAdmit;
	
	/**
	 * Default Constructor
	 * Every value is null or -1
	 */
	public College() {
		name = null;
		location = null;
		major = null;
		satScore = -1;
		gpa = -1;
		percentAdmit = -1;
	}
	
	/**
	 * Specified Constructor
	 * 
	 * @param name, name of College
	 * @param location, location of College
	 * @param major, College's most popular major
	 * @param satScore, College's average total SAT score
	 * @param gpa, Collge's admitted student's average gpa (100 scale)
	 * @param percentAdmit, College's percentage of admitted students
	 */
	public College(String name, String location, String major, int satScore, double gpa, int percentAdmit) {
		this.name = name;
		this.location = location;
		this.major = major;
		this.satScore = satScore;
		this.gpa = gpa;
		this.percentAdmit = percentAdmit;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * @return the satScore
	 */
	public int getSatScore() {
		return satScore;
	}

	/**
	 * @param satScore the satScore to set
	 */
	public void setSatScore(int satScore) {
		this.satScore = satScore;
	}

	/**
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	/**
	 * @return the percentAdmit
	 */
	public int getPercentAdmit() {
		return percentAdmit;
	}

	/**
	 * @param percentAdmit the percent Admit to set
	 */
	public void setPercentAdmit(int percentAdmit) {
		this.percentAdmit = percentAdmit;
	}
	
	/**
	 * @return College statistic in sentence form
	 */
	public String toString() {
		return this.getName() + " is a school in " + this.getLocation() + ". The most popular major is " 
				+ this.getMajor() + ". The average total SAT score is " + this.getSatScore()
				+ " and the average GPA is " + this.getGpa() + ". The admitted students percentage is "
				+ this.getPercentAdmit() + "%.";
	}
	
	/**
	 * Checks if two Colleges are equal: if every data member is the same
	 * 
	 *  @param otherCollege to be compared
	 *  @return true if Colleges are the same, false if different
	 */
	public boolean equals(College otherCollege) {
		return ((this.name.equalsIgnoreCase(otherCollege.getName())) && (this.getLocation().equalsIgnoreCase(otherCollege.getLocation()))
				&& (this.getMajor().equalsIgnoreCase(otherCollege.getMajor())) && (this.getSatScore() == otherCollege.getSatScore())
				&& (this.getGpa() == otherCollege.getGpa()) && (this.getPercentAdmit() == otherCollege.getPercentAdmit()));
	}
	
	/**
	 * Compares two Colleges by name
	 * 
	 *  @param College to be compared
	 *  @return integer difference
	 */
	public int compareTo(College otherCollege) {
		int minLength = Math.min(this.getName().length(), otherCollege.getName().length());
		
		for (int i = 0; i < minLength; i++) {
			if (this.getName().charAt(i) != otherCollege.getName().charAt(i)) {
				return (int)(this.getName().charAt(i) - otherCollege.getName().charAt(i));
			}
		}
		
		// if both names are equal
		return 0;
	}
}
