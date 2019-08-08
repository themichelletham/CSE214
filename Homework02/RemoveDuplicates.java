package Homework02;

public class RemoveDuplicates {
	public IntNode original_list;
	public int length;
	
	public RemoveDuplicates(IntNode original_list, int length) {
		this.original_list = original_list;
		this.length = length;
	}
	
	public void remove_duplicates() {
		// original_list is the head of the linkedlist
		IntNode head = original_list;
		
		// curr will be the starting point of the list to traverse
		IntNode curr = head;
		
		// Can't perform remove_duplicates if linkedlist is empty
		if (original_list == null) {
			return;
		}
		
		// Traverse through list
		while (curr.getNext() != null) {
			// Compare the curr node with next
			if (curr.getData() == curr.getNext().getData()) {
				curr.setNext(curr.getNext().getNext());
			} else {
				// Advance curr to next
				curr = curr.getNext();
			}
		}
	}
	
	public IntNode getList() {
		return original_list;
	}
}
