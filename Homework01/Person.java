package Homework01;
/* 
* Name: Michelle Tham
* SBU ID: 111810145
* CSE 214 Recitation 09
* Assignment 1
*/ 


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
	public String getGender() {
		if (this.gender.contains("f") || this.gender.contains("F")) {
			this.gender = "Female";
		} else if (this.gender.contains("m") || this.gender.contains("M")){
			this.gender = "Male";
		}
		return this.gender;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
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
