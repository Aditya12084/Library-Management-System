import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        isBorrowed = true;
    }

    public void returned() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author +
                ", Status: " + (isBorrowed ? "Borrowed" : "Available");
    }
}

// Library class
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    // Add a book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Remove a book by ISBN
    public void removeBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            books.remove(book);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    // View all books
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Search book by title
    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with that keyword.");
        }
    }

    // Borrow a book
    public void borrowBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            if (!book.isBorrowed()) {
                book.borrow();
                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is already borrowed.");
            }
        } else {
            System.out.println("Book not found!");
        }
    }

    // Return a book
    public void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            if (book.isBorrowed()) {
                book.returned();
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Book was not borrowed.");
            }
        } else {
            System.out.println("Book not found!");
        }
    }

    // Helper method to find book by ISBN
    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View Books");
            System.out.println("4. Search Book");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case 2:
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = sc.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 3:
                    library.viewBooks();
                    break;
                case 4:
                    System.out.print("Enter title or author to search: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;
                case 5:
                    System.out.print("Enter ISBN to borrow: ");
                    String borrowIsbn = sc.nextLine();
                    library.borrowBook(borrowIsbn);
                    break;
                case 6:
                    System.out.print("Enter ISBN to return: ");
                    String returnIsbn = sc.nextLine();
                    library.returnBook(returnIsbn);
                    break;
                case 0:
                    System.out.println("Exiting... Thanks!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}