import java.util.*;

public class LibraryDriver {
    public static void main(String[] args) {
        // Create catalog
        LibraryCatalog catalog = new LibraryCatalog();

        // Create sample books
        Book book1 = new Book("123", "Java Basics", "John Smith", true);
        Book book2 = new Book("456", "Advanced Java", "Salma Patel", true);
        Book book3 = new Book("789", "Java Design Patterns", "Zeng Chen", true);
        Book book4 = new Book("159", "The Algorithmic Heart", "Arwa Zahrawi", true);
        Book book5 = new Book("486", "Code of Shadows", "Jason Richter", true);
        Book book6 = new Book("226", "Quantum Dawn", "Elara Vasiliou", true);

        // Add books to catalog
        catalog.addBook(book1);
        catalog.addBook(book2);
        catalog.addBook(book3);
        catalog.addBook(book4);
        catalog.addBook(book5);
        catalog.addBook(book6);

        // Display all books sorted by title
        System.out.println("\nAll books sorted by title:");
        List<Book> sortedBooks = catalog.getBooksSortedByTitle();
        for (Book book : sortedBooks) {
            System.out.println("\t" + book);
        }

        // Find book by ISBN
        System.out.println("\nLooking up book by ISBN (123):");
        Book foundBook = catalog.findBookByIsbn("123");
        if (foundBook != null) {
            System.out.println("Found:\t" + foundBook);
        }

        // Test search by author
        System.out.println("\nFinding books by Salma Patel:");
        List<Book> smithBooks = catalog.findBooksByAuthor("Salma Patel");
        smithBooks.forEach(book -> {
            System.out.print("\t" + book);
        });

        // Create and add members
        LibraryMember member1 = new LibraryMember("M1", "Marcus Bjornsson");
        LibraryMember member2 = new LibraryMember("M2", "Bob Wilson");
        catalog.addMember(member1);
        catalog.addMember(member2);

        // Test burrowing operations
        System.out.println("\nPerforming checkouts...");
        System.out.println(catalog.checkoutBook("123", member1));
        System.out.println(catalog.checkoutBook("789", member2));
        System.out.println(catalog.checkoutBook("486", member1));

        System.out.println("\nBorrowed books for " + member1.getName() + ":");
        List<Book> m1Borrowings = catalog.getCurrentBorrowings(member1);
        for (Book book : m1Borrowings) {
            System.out.println("\t" + book);
        }

        // Return a book
        System.out.println("\nReturning book:");
        catalog.returnBook("123", member1);

        System.out.println("\nBorrowed books for " + member1.getName() + ":");
        m1Borrowings = catalog.getCurrentBorrowings(member1);
        for (Book book : m1Borrowings) {
            System.out.println("\t" + book);
        }

        // Checkout same book again to increase its popularity
        System.out.println("\nMore checkouts to demonstrate popularity...");
        catalog.checkoutBook("123", member2);
        catalog.returnBook("123", member2);
        catalog.checkoutBook("123", member1);
        catalog.returnBook("123", member1);

        // View most popular books
        System.out.println("\nMost popular books (by times borrowed):");
        List<Book> popularBooks = catalog.getMostPopularBooks(3);
        for (Book book : popularBooks) {
            int loanCount = catalog.getBookLoanCount(book.getIsbn());
            System.out.println("\t" + book + " - borrowed " + loanCount + " times");
        }

        // View borrowing history for Marcus
        System.out.println("\nMarcus's complete borrowing history:");
        List<Book> marcusHistory = catalog.getBorrowingHistory(member1);
        for (Book book : marcusHistory) {
            System.out.println("\t" + book.getTitle());
        }

        // Try to checkout an unavailable book
        System.out.println("\nTrying to checkout an unavailable book:");
        boolean checkoutResult = catalog.checkoutBook("486", member2);
        System.out.println("Checkout successful: " + checkoutResult);

        System.out.println();
    }
}