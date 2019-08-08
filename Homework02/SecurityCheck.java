package Homework02;

public class SecurityCheck {
	private Line LineA;
	private Line LineB;
	
	public SecurityCheck() {
		LineA = new Line();
		LineB = new Line();
	}
	
	public Line getLineA() {
		return this.LineA;
	}
	
	public Line getLineB() {
		return this.LineB;
	}
	
	public void addPerson(Person person) {
		if ((LineA.getNumPeople() == 0) || (LineA.getNumPeople() <= LineB.getNumPeople())) {
			LineA.addPerson(person.getName(), person.getTicketNo());
		} else if ((LineB.getNumPeople() == 0) || (LineA.getNumPeople() > LineB.getNumPeople())) {
			LineB.addPerson(person.getName(), person.getTicketNo());
		}
	}
	
	public void removePerson(Person person) throws personNotFoundException {
		if (LineA.findName(person.getName()) || LineB.findName(person.getName())) {
			if (LineA.findName(person.getName())) {
				LineA.removePerson(person.getName());
			} else if (LineB.findName(person.getName())) {
				LineB.removePerson(person.getName());
			}
			
			// Balances the two lines
			if (LineA.getNumPeople() < LineB.getNumPeople()) {
				Person temp = LineB.popLastPerson(LineB.getTail());
				LineA.addPerson(temp.getName(), temp.getTicketNo());
			} else if (LineA.getNumPeople() > LineB.getNumPeople()) {
				Person temp = LineA.popLastPerson(LineA.getTail());
				LineB.addPerson(temp.getName(), temp.getTicketNo());
			}
			
		} else {
			throw new personNotFoundException("Person does not exist.");
		}
	}
	
	public void printSecurityCheck() {
		System.out.println("Line A: " + LineA.toString());
		System.out.println("Line B: " + LineB.toString());
	}
	
	public void printBackwardsSecurityCheck() {
		System.out.println("Line A: " + LineA.backToString());
		System.out.println("Line B: " + LineB.backToString());
	}
}
