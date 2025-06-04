import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
        } else {
            System.out.println("This book is already borrowed.");
        }
    }

    public void returned() {
        if (isBorrowed) {
            isBorrowed = false;
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + (isBorrowed ? " (Borrowed)" : " (Available)");
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Book added successfully.");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        System.out.println("Library Books:");
        int index = 1;
        for (Book book : books) {
            System.out.println(index + ". " + book);
            index++;
        }
    }

    public void borrowBook(int index) {
        if (index < 1 || index > books.size()) {
            System.out.println("Invalid book number.");
            return;
        }
        Book book = books.get(index - 1);
        if (!book.isBorrowed()) {
            book.borrow();
            System.out.println("You borrowed: " + book.getTitle());
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook(int index) {
        if (index < 1 || index > books.size()) {
            System.out.println("Invalid book number.");
            return;
        }
        Book book = books.get(index - 1);
        if (book.isBorrowed()) {
            book.returned();
            System.out.println("You returned: " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
}

public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Welcome to Library Management System!");
        while (!exit) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    handleAddBook();
                    break;
                case 2:
                    library.listBooks();
                    break;
                case 3:
                    handleBorrowBook();
                    break;
                case 4:
                    handleReturnBook();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. List Books");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Exit");
    }

    private static void handleAddBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine().trim();
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }
        library.addBook(title, author);
    }

    private static void handleBorrowBook() {
        library.listBooks();
        int bookNo = getIntInput("Enter the book number to borrow: ");
        library.borrowBook(bookNo);
    }

    private static void handleReturnBook() {
        library.listBooks();
        int bookNo = getIntInput("Enter the book number to return: ");
        library.returnBook(bookNo);
    }

    private static int getIntInput(String prompt) {
        int num = -1;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                num = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return num;
    }
}

