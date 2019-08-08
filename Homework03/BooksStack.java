package Homework03;

import java.util.EmptyStackException;

/**
 * BooksStacks creates Book objects and stores them in an array stack.
 * It will also print the Book objects in a tabular format.
 * @see Book object
 * 
 * @author Michelle Tham, SBU ID: 111810145
 * 
 * */

public class BooksStack {
	private Book[] books;
	private int indexOfTop;
	
	/** Default no parameter Constructor. */
	public BooksStack() {
		books = new Book[100];
		indexOfTop = -1;
	}
	
	/** @return the array of books */
	public Book[] getBooks() {
		return books;
	}
	
	/** @param length to set the length of the array stack */
	public void setBooks(int length) {
		books = new Book[length];
	}
	
	/** @return the index of the top of the stack*/
	public int getIndexOfTop() {
		return indexOfTop;
	}
	
	/** @param index to set the top of the stack */
	public void setIndexOfStop(int index) {
		indexOfTop = index;
	}
	
	/**
	 * push adds a new book to the top of the book stack.
	 * Precondition: newBook != null
	 * Postcondition: newBook is added to the stack
	 * 
	 * @param newBook is added to the top of the array books.
	 * @return Nothing.
	 * @throws BookAlreadyExistsException if newBook already exists in stack
	 * */
	public void push(Book newBook) throws BookAlreadyExistsException {
		if (newBook != null) {
			if (indexOfTop == -1) {
				indexOfTop++;
				books[indexOfTop] = newBook;
			} else {
				if (checkDuplicates(newBook)) {
					throw new BookAlreadyExistsException("Book already exists.");
				} else {
					books[++indexOfTop] = newBook;
				}
			}
		}
	}
	
	/**
	 * pop removes the Book on the top of the stack
	 * Precondition: None.
	 * Postcondition: If stack is not empty, throws EmptyStackException
	 * 
	 * @param None.
	 * @return Book being removed.
	 * @throws EmptyStackException if books is empty
	 * */
	public Book pop() throws EmptyStackException {
		// If stack is not empty, pop
		if (indexOfTop != -1) {
			Book temp = books[indexOfTop];
			books[indexOfTop--] = null;
			return temp;
		} else {
			throw new EmptyStackException();
		}
	}
	
	/**
	 * peek returns the Book at the top of the stack
	 * Precondition: None.
	 * Postcondition: None.
	 * 
	 * @param None.
	 * @return Book at the top of the stack
	 * @throws EmptyStackException if books stack is empty
	 * */
	public Book peek() throws EmptyStackException {
		// if stack is not empty, peek
		if (indexOfTop != -1) {
			return books[indexOfTop];
		} else {
			throw new EmptyStackException();
		}	
	}
	
	/** @return true if stack is empty, else return false */
	public boolean isEmpty() {
		if (indexOfTop == -1) {
			return true;
		} else {
			return false;
		}
	}
	
	/** @return size of the stack */
	public int size() {
		return indexOfTop + 1;
	}
	
	/** 
	 * sort sorts the stack of books based on the user's input
	 * Note: Books are in a stack. It should be the least on top and the greatest on the bottom
	 * (Reversing order of sort)
	 * 
	 * @param method is the method for the books to be sorted.
	 * @return Nothing.
	 * */
	public void sort(char method) {
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; j < this.size() - i - 1; j++) {
				if (books[j].compareTo(books[j + 1].getName(), method) < 0) {
					Book temp = books[j + 1];
					books[j + 1] = books[j];
					books[j] = temp;
				}	
			}
		}
	}
	
	/**
	 * checkDuplicates checks if the book already exists
	 * @return true if found duplicate, false if not found
	 * */
	public boolean checkDuplicates(Book newBook) {
		for (int i = 0; i < this.size(); i++) {
			if (newBook.equals(books[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * findBook traverses through a stack until book is found (at the top of the stack)
	 * pops books if not the needed book, and pushes into a temp stack
	 * @param String name -- name of Book to be found
	 * @return temp stack with the other books
	 * */
	public BooksStack findBook(String name) {
		BooksStack temp = new BooksStack();
		try {
			while (this.peek() != null) {
				if (!this.peek().getName().equalsIgnoreCase(name)) {
					temp.push(this.pop());
				} else {
					break;
				}
			}
		} catch (EmptyStackException e) {
			e.getMessage();
		} catch (BookAlreadyExistsException e) {
			e.getMessage();
		} 
		return temp;
	}
	
	/**
	 * reset resets so the elements in the temp stack are back in the original stack
	 * @return Nothing.
	 * */
	public void reset(BooksStack temp) {
		try {
			while (temp.peek() != null) {
				this.push(temp.pop());
			}
		} catch (EmptyStackException e) {
			e.getMessage();
		} catch (BookAlreadyExistsException e) {
			e.getMessage();
		}
	}
}
