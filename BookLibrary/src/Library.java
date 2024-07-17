import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Book findBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(String isbn, User user) {
        Book book = findBook(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(String isbn) {
        Book book = findBook(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println(book.getTitle() + " is returned.");
        } else {
            System.out.println("Book is not borrowed.");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        User user1 = new User("Alice", 1);
        User user2 = new User("Bob", 2);

        library.addBook(new Book("1984", "George Orwell", "1234567890"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "1234567891"));

        library.displayBooks();

        System.out.println("Enter ISBN to borrow:");
        String isbnToBorrow = scanner.next();
        library.borrowBook(isbnToBorrow, user1);

        library.displayBooks();

        System.out.println("Enter ISBN to return:");
        String isbnToReturn = scanner.next();
        library.returnBook(isbnToReturn);

        library.displayBooks();

        scanner.close();
    }
}


