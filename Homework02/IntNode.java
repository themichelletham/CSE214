package Homework02;

public class IntNode {
	private int data;
	private IntNode next;
	
	public IntNode(int data) {
		this.data = data;
		next = null;
	}
	
	public int getData() {
		return this.data;
	}
	
	public IntNode getNext() {
		return this.next;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void setNext(IntNode next) {
		this.next = next;
	}
}

