

package com.lms;

import java.util.List;
import java.util.Scanner;

public class MainUtil {

    private static Library lb = new Library();
    private static SerializationHandler sh = new SerializationHandler();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws BookNotFoundException, NotEnoughBooksException {

        // Initialize the library with some books
        initLibrary();

        // Main menu loop
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayAvailableBooks();
                    break;
                case 2:
                    searchBookByAuthor();
                    break;
                case 3:
                    searchBookByGenre();
                    break;
                case 4:
                	System.out.println("Enter Book details:");

                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Availability: ");
                    int availability = scanner.nextInt();

                    scanner.nextLine(); // consume the newline character left by nextInt()
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();

                    // Adding the book to the library
                    lb.addBook(isbn, title, author, price, availability, genre);
                    break;
                case 5:
                    removeBookByISBN();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    saveLibrary();
                    break;
                case 9:
                    loadLibrary();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initLibrary() {
        lb.setLname("UstTechademy");
        lb.setAddress("Bangalore");

        Book obj1 = new Book("A-z1", "Java", "james", 100, 4, "prog");
        Book obj2 = new Book("abp", "Python", "kames", 32, 5, "it");

        lb.addBook(obj1);
        lb.addBook(obj2);
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Display Available Books");
        System.out.println("2. Search Book by Author");
        System.out.println("3. Search Book by Genre");
        System.out.println("4. Add New Book");
        System.out.println("5. Remove Book by ISBN");
        System.out.println("6. Borrow a Book");
        System.out.println("7. Return a Book");
        System.out.println("8. Save Library");
        System.out.println("9. Load Library");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("ISBN\t | Title\t | Author\t | Price\t | Available\t | Type ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        for (Book dt : lb.displayAvailableBooks()) {
            System.out.println(dt.getISBN() + " \t" + dt.getTitle() + " \t" + dt.getAuthor() + " \t" + dt.getPrice() + " \t" + dt.getAvailability() + " \t" + dt.getGenre());
        }
    }

    private static void searchBookByAuthor() {
        System.out.print("\nEnter author name to search: ");
        String author = scanner.nextLine();

        List<Book> booksByAuthor = lb.searchByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            System.out.println("No books found by author: " + author);
        } else {
            System.out.println("Books by " + author + ": " + booksByAuthor);
        }
    }

    private static void searchBookByGenre() {
        System.out.print("\nEnter genre to search: ");
        String genre = scanner.nextLine();

        List<Book> booksByGenre = lb.searchByGenre(genre);
        if (booksByGenre.isEmpty()) {
            System.out.println("No books found in genre: " + genre);
        } else {
            System.out.println("Books in genre " + genre + ": " + booksByGenre);
        }
    }

    private static void removeBookByISBN() {
        System.out.print("\nEnter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();

        try {
            lb.removeBook(isbn);
            System.out.println("Book removed successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrowBook() {
        System.out.print("\nEnter member name: ");
        String name = scanner.nextLine();
        System.out.print("Enter member ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter quantity to borrow: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter ISBN of the book to borrow: ");
        String isbn = scanner.nextLine();

        Member member = new Member();
        member.setID(id);
        member.setName(name);
        member.setQnty(quantity);

        try {
            Book book = lb.getBookByISBN(isbn);
            if (book.getAvailability() >= quantity) {
                member.borrowBook(isbn);
                lb.updateBookAvailability(isbn, book.getAvailability() - quantity);
                System.out.println("Book borrowed successfully.");
            } else {
                throw new NotEnoughBooksException(isbn + " not enough books.");
            }
        } catch (BookNotFoundException | NotEnoughBooksException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("\nEnter ISBN of the book to return: ");
        String isbn = scanner.nextLine();

        // In a real application, you would find the Member who borrowed the book and update their records
        System.out.println("Book returned successfully.");
    }

    private static void saveLibrary() {
        System.out.print("\nEnter file name to save library: ");
        String fileName = scanner.nextLine();

        sh.serializeLibrary(lb, fileName);
        System.out.println("Library saved successfully to " + fileName);
    }

    private static void loadLibrary() {
        System.out.print("\nEnter file name to load library: ");
        String fileName = scanner.nextLine();

        Library loadedLibrary = sh.deserializeLibrary(fileName);
        lb = loadedLibrary != null ? loadedLibrary : lb;
        System.out.println("Library loaded successfully from " + fileName);
    }
}

