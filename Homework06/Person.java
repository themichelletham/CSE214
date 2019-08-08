
/**
 * Person Class
 */

/**
 * @author Michelle Tham, SBU ID: 111810145
 *
 */
package Homework06;
public class Person {
	private int age;
	private double height;
	private double weight;
	private String name;
	private String gender;
	
	// Default Constructor
	public Person() {
	}
	
	// Specified Constructor
	public Person(String name, String gender, int age, double height, double weight) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	// Getters
	
	// In the case that the names in the files are: "name", it will remove the quotation marks
	// Since Strings are immutable, StringBuilder is used
	/**
	 * @return the name 
	 */
	public String getName() {
		if (this.name.contains("\"")) {
			StringBuilder sb = new StringBuilder(this.name);
			sb.deleteCharAt(sb.indexOf("\""));
			this.name = sb.toString();
			getName();
		}
		return this.name;
	}
	
	// In the case that the genders contain any other than female or male,
	// This method will make sure it is either or
	/**
	 * @return the gender
	 */
	public String getGender() {
		if (this.gender.contains("f") || this.gender.contains("F")) {
			this.gender = "Female";
		} else if (this.gender.contains("m") || this.gender.contains("M")){
			this.gender = "Male";
		}
		return this.gender;
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * @return the height 
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * @return the weight 
	 */
	public double getWeight() {
		return this.weight;
	}
	
	// Setters
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	// toString Method
	public String toString() {
		return this.getName() + " is a " + this.getAge() + " year old " + this.getGender().toLowerCase() +
				" who is " + ((int)this.getHeight() / 12) + " feet " + ((int)this.getHeight() % 12) +
				" inches tall and weighs " + this.getWeight() + " pounds.";
	}
	
	// Check if two Persons are equal: if every data member is the same
	public boolean equals(Person otherPerson) {
		return ((this.name.equalsIgnoreCase(otherPerson.getName())) && (this.gender.equalsIgnoreCase(otherPerson.getGender()))
				&& (this.age == otherPerson.getAge()) && (this.height == otherPerson.getHeight()) && (this.weight == otherPerson.getWeight()));
	}
	
	// Compare the two Persons by name
	public int compareTo(Person otherPerson) {
		int minLength = Math.min(this.getName().length(), otherPerson.getName().length());
		
		for (int i = 0; i < minLength; i++) {
			if (this.getName().charAt(i) != otherPerson.getName().charAt(i)) {
				return (int)(this.getName().charAt(i) - otherPerson.getName().charAt(i));
			}
		}
		// If both names are equal
		return 0;
	}
}
