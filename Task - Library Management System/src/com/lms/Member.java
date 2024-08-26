package com.lms;

import java.util.ArrayList;
import java.util.List;

public class Member extends Library {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String name;
	private List<Book> borrowedBooks;
	private int qnty;
	
	Library lb = new Library();
	
	public Member() {
		Book obj1 = new Book("A-z1","Java","james",100,4,"prog");
		Book obj2 = new Book("abp","Pyhton","kames",32,5,"it");
		
		lb.addBook(obj1);
		lb.addBook(obj2);
	}
	
	
	public Member(int iD, String name, int qnty) {
		super();
		ID = iD;
		this.name = name;
		this.qnty = qnty;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public int getQnty() {
		return qnty;
	}
	public void setQnty(int qnty) {
		this.qnty = qnty;
	}
	
	public void borrowBook(String ISBN) {
		borrowedBooks = new ArrayList<>();
		for(Book b:lb.displayAvailableBooks()) {
			if(ISBN.equals(b.getISBN())) {
				borrowedBooks.add(b);
			}
		}
	}
	
	public void returnBook(String ISBN) {
		List<Book> lbb = displayBorrowedBooks();
		for(Book b: lbb) {
			if(b.getISBN().equals(ISBN)) {
				lbb.remove(b);
				System.out.println("Successfully returned book "+ ISBN);
				break;
			}
		}
	}
	
	public List<Book> displayBorrowedBooks(){
		return borrowedBooks;
	}
	
}
