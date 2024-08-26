package com.lms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Book> books;
	private String lname;
	private String address;
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void addBook(Book book) {
		if(books==null) {
			books = new ArrayList<Book>();
		}
		books.add(book);
	}
	
	public void removeBook(String ISBN) {
		for(Book bk : books) {
			if(bk.getISBN().equals(ISBN)) {
				books.remove(bk);
				System.out.println("Successfully deleted one book.");
				break;
			}
		}
	}
	
	public List<Book> searchByAuthor(String author){
		List<Book> ba = new ArrayList<>();
		for(int i=0;i<books.size();i++) {
			Book b = books.get(i);
			if(b.getAuthor().equals(author)) {
				ba.add(b);
			}
		}
		return ba;
	}

	public List<Book> searchByGenre(String genre){
		List<Book> ba = new ArrayList<>();
		for(int i=0;i<books.size();i++) {
			Book b = books.get(i);
			if(b.getGenre().equals(genre)) {
				ba.add(b);
			}
		}
		return ba;
	}
	
	public List<Book> displayAvailableBooks(){
		return books;
	}
	
}
