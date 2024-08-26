package com.lms;

import java.util.List;

public class MainUtil {

	public static void main(String[] args) throws BookNotFoundException, NotEnoughBooksException {
		
		Book obj1 = new Book("A-z1","Java","james",100,4,"prog");
		Book obj2 = new Book("abp","Pyhton","kames",32,5,"it");
		
		Library lb = new Library();
		
		lb.setLname("UstTechademy");
		lb.setAddress("Bangalore");
		lb.addBook(obj1);
		lb.addBook(obj2);
		
		SerializationHandler sh = new SerializationHandler();
		
		sh.serializeLibrary(lb, "library.txt");
		Library dt1 = sh.deserializeLibrary("library.txt");
		
		System.out.println(dt1.displayAvailableBooks());
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		System.out.println("ISBN |\t"+" Title |\t"+" Author |\t"+" Price |\t"+" Available |\t"+" Type ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		for(int i =0;i<lb.displayAvailableBooks().size();i++) {
			Book dt = lb.displayAvailableBooks().get(i);
			System.out.println(dt.getISBN()+" \t"+dt.getTitle()+" \t"+dt.getAuthor()+" \t"+dt.getPrice()+" \t"+dt.getAvailability()+" \t"+dt.getGenre());
		}
		
		// SearchByAuthor
		String author = "james";
		
		List<Book> sa = lb.searchByAuthor(author);
		
		if(sa.size()<=0) {
			throw new BookNotFoundException("Book is not available.");
		}
		else {
			System.out.println(sa);
		}

		// SearchBygenre
		String genre = "it";
		
		List<Book> lg = lb.searchByAuthor(author);
		
		if(lg.size()<=0) {
			throw new BookNotFoundException("Book is not available.");
		}
		else {
			System.out.println(lg);
		}
		
		//RemoveBookByISBN
		String isbn = "hhh";
		lb.removeBook(isbn);
		
		//BorrowBook
		Member mb = new Member();
		
		mb.setID(11);
		mb.setName("Hero");
		mb.setQnty(7);
		
		String bisbn = "abp";
		
		for(int i =0;i<lb.displayAvailableBooks().size();i++) {
			Book bk = lb.displayAvailableBooks().get(i);
			if(mb.getQnty() < bk.getAvailability()) {
				mb.borrowBook(bisbn);
			}
			else {
				throw new NotEnoughBooksException(bisbn+" not enough books.");
			}
		}
		
		//DisplayBorrowBooks
		System.out.println(mb.displayAvailableBooks());
		
		//ReturnBook
		mb.returnBook(bisbn);
	}
	
}
