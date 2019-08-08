package Homework02;

public class Person {
	private String name;
	private Integer ticketNo;
	private Person nextPerson;
	private Person prevPerson;
	
	public Person (String name, Integer ticketNo) {
		this.name = name;
		this.ticketNo = ticketNo;
		this.nextPerson = null;
		this.prevPerson = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Integer getTicketNo() {
		return this.ticketNo;
	}
	
	public Person getNextPerson() {
		return this.nextPerson;
	}
	
	public Person getPrevPerson() {
		return this.prevPerson;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTicketNo(Integer ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public void setNextPerson(Person nextPerson) {
		this.nextPerson = nextPerson;
	}
	
	public void setPrevPerson(Person prevPerson) {
		this.prevPerson = prevPerson;
	}
}
