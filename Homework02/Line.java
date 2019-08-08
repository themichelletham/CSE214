package Homework02;

public class Line {
	private Person head;
	private Person tail;
	private Person cursor;
	private int numPeople;
	
	public Line() {
		this.head = null;
		this.tail = null;
		this.cursor = null;
		this.numPeople = 0;
	}

	public Person getHead() {
		return head;
	}
	
	public Person getTail() {
		return tail;
	}
	
	public Person getCursor() {
		return cursor;
	}
	
	public int getNumPeople() {
		return this.numPeople;
	}
	
	public void addPerson(String name, int ticketNo) {
		Person newPerson = new Person (name, ticketNo);
		// If Line is empty, add to head
		if (this.head == null) {
			this.head = newPerson;
			this.tail = newPerson;
			this.cursor = head;
		} else { // If Line is NOT empty, find correct spot
			// If the newPerson is before head
			if (newPerson.getTicketNo() < head.getTicketNo()) {
				newPerson.setNextPerson(head);
				head.setPrevPerson(newPerson);
				head = newPerson;
			} else {
				traverseCompareTicket(newPerson);
				if (cursor.getNextPerson() == null) {
					// If at end of the line, add person there
					cursor.setNextPerson(newPerson);
					cursor.getNextPerson().setPrevPerson(cursor);
					cursor = cursor.getNextPerson();
					tail = cursor;
				} else {
					// Add newPerson in correct position between two People
					newPerson.setNextPerson(cursor.getNextPerson());
					newPerson.setPrevPerson(cursor);
					cursor.getNextPerson().setPrevPerson(newPerson);
					cursor.setNextPerson(cursor);
					cursor = cursor.getNextPerson();
				}
			}
		}
		numPeople++;
	}
	
	public void removePerson(String name) throws personNotFoundException {
		// If name exist
		if (findName(name)) {
			// If only one person in line
			if (head != null && head == tail) {
				head = null;
				cursor = null;
			} else if (cursor == head) { // If cursor is at head
				head = cursor.getNextPerson();
				head.setPrevPerson(null);
				cursor = head;
			} else if (cursor == tail) { // If cursor is at tail
				tail = tail.getPrevPerson();
				tail.setNextPerson(null);
				cursor = tail;
			} else {
				// At middle / correct position
				cursor.getPrevPerson().setNextPerson(cursor.getNextPerson());
				cursor = cursor.getNextPerson();
				cursor.setPrevPerson(cursor.getPrevPerson().getPrevPerson());
			}
		} else {
			throw new personNotFoundException("Person not found.");
		}
		numPeople--;
	}
	
	public void removePerson(int ticketNo) throws personNotFoundException {
		// If ticket exist
		if (findTicket(ticketNo)) {
			if (head != null && head == tail) {
				head = null;
				cursor = null;
			} else if (cursor == head) {// If cursor is at head 
				head = cursor.getNextPerson();
				head.setPrevPerson(null);
				cursor = head;
			} else if (cursor == tail) { // If cursor is at tail
				tail = tail.getPrevPerson();
				tail.setNextPerson(null);
				cursor = tail;
			} else {
				// At middle / correct position
				cursor.getPrevPerson().setNextPerson(cursor.getNextPerson());
				cursor = cursor.getNextPerson();
				cursor.setPrevPerson(cursor.getPrevPerson().getPrevPerson());
			}
		} else {
			throw new personNotFoundException("Person not found.");
		}
		numPeople--;
	}
	
	public Person popLastPerson(Person person) {
		Person last = tail;
		tail = tail.getPrevPerson();
		tail.setNextPerson(null);
		
		return last;
	}
 	
	public void traverseCompareTicket(Person newPerson) {
		resetCursor();
		
		while (cursor.getNextPerson() != null) {
			// Traverse if not in the correct position
			
			if (!(cursor.getTicketNo() < newPerson.getTicketNo() && 
					newPerson.getTicketNo() < cursor.getNextPerson().getTicketNo())) {
				cursor = cursor.getNextPerson();
			}
		}
	}
	
	public boolean findTicket(int ticketNo) {
		resetCursor();
		
		while (cursor != null) {
			// If name is found
			if (cursor.getTicketNo() == ticketNo) {
				return true;
			} else { // traverse
				cursor = cursor.getNextPerson();
			}
		}
		
		return false;
	}
	
	public boolean findName(String name) {
		resetCursor();
		
		while (cursor != null) {
			// If name is found
			if (cursor.getName().equalsIgnoreCase(name)) {
				return true;
			} else { // traverse
				cursor = cursor.getNextPerson();
			}
		}
		
		return false;
	}
	
	public void resetCursor() {
		this.cursor = this.head;
	}
	
	public String toString() {
		resetCursor();
		StringBuilder s = new StringBuilder();
		
		while (cursor != null) {
			s.append("[Name: " + cursor.getName() + ", TicketNo: " + cursor.getTicketNo() + "]");
			cursor = cursor.getNextPerson();
		}
		
		resetCursor();
		return s.toString();
	}
	
	public String backToString() {
		cursor = tail;
		StringBuilder s = new StringBuilder();
		
		while (cursor != null) {
			s.append("[Name: " + cursor.getName() + ", TicketNo: " + cursor.getTicketNo() + "]");
			cursor = cursor.getPrevPerson();
		}
		
		return s.toString();
	}
}
