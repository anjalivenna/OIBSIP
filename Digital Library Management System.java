import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book [ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable + "]";
    }
}

// User class
class User {
    private String name;
    private String email;
    private int userId;

    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User [ID: " + userId + ", Name: " + name + ", Email: " + email + "]";
    }
}

// Library class containing the core logic
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Add a new book to the library
    public void addBook(String title, String author, String isbn) {
        Book newBook = new Book(title, author, isbn);
        books.add(newBook);
        System.out.println("Book added: " + newBook);
    }

    // Remove a book from the library
    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book with ISBN " + isbn + " removed.");
    }

    // Search for a book by title or ISBN
    public Book searchBook(String searchTerm) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchTerm) || book.getIsbn().equalsIgnoreCase(searchTerm)) {
                return book;
            }
        }
        return null;
    }

    // List all available books
    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Issue a book to a user
    public void issueBook(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Book issued: " + book + " to User: " + user.getName());
                } else {
                    System.out.println("Book is not available.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Return a book
    public void returnBook(String isbn, User user) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Book returned: " + book + " by User: " + user.getName());
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Add a new user to the library
    public void addUser(int userId, String name, String email) {
        User user = new User(userId, name, email);
        users.add(user);
        System.out.println("New user added: " + user);
    }

    // List all users
    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}

// Admin class
class Admin {
    private Library library;
    private Scanner scanner;

    public Admin(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void showAdminMenu() {
        int choice;
        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. List Books");
            System.out.println("4. Add User");
            System.out.println("5. List Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    addUser();
                    break;
                case 5:
                    library.listUsers();
                    break;
                case 6:
                    System.out.println("Exiting Admin Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        library.addBook(title, author, isbn);
    }

    private void removeBook() {
        System.out.print("Enter book ISBN to remove: ");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
    }

    private void addUser() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        library.addUser(userId, name, email);
    }
}

// User Module
class UserModule {
    private Library library;
    private User user;
    private Scanner scanner;

    public UserModule(Library library, User user) {
        this.library = library;
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    public void showUserMenu() {
        int choice;
        do {
            System.out.println("\nUser Menu:");
            System.out.println("1. Search Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. List Books");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    library.listBooks();
                    break;
                case 5:
                    System.out.println("Exiting User Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void searchBook() {
        System.out.print("Enter book title or ISBN to search: ");
        String searchTerm = scanner.nextLine();
        Book book = library.searchBook(searchTerm);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private void issueBook() {
        System.out.print("Enter ISBN of the book to issue: ");
        String isbn = scanner.nextLine();
        library.issueBook(isbn, user);
    }

    private void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn, user);
    }
}

// Main class to run the application
class DigitalLibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library();

        // Add some initial books
        library.addBook("Java Programming", "James Gosling", "12345");
        library.addBook("Data Structures", "Robert Lafore", "67890");

        // Admin login simulation
        Admin admin = new Admin(library);
        User user = new User(1, "John Doe", "john@example.com");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWelcome to the Digital Library Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    admin.showAdminMenu();
                    break;
                case 2:
                    UserModule userModule = new UserModule(library, user);
                    userModule.showUserMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);
    }
}
