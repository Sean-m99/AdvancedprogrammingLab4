import java.util.*;

class BorrowingRecord {
    private Book book;
    private LibraryMember member;
    private Date borrowDate;
    private Date returnDate;

    public BorrowingRecord(Book book, LibraryMember member) {
        this.book = book;
        this.member = member;
        this.borrowDate = new Date();
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() { return book; }
    public LibraryMember getMember() { return member; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getReturnDate() { return returnDate; }
}


public class LibraryCatalog {
    // Quick ISBN-based lookup
    private Map<String, Book> booksByIsbn;
    // Books sorted by title
    private TreeSet<Book> booksByTitle;
    // Track loan count for popularity
    private Map<String, Integer> bookLoanCounts;
    // Current borrowings: member -> their borrowed books
    private Map<LibraryMember, Set<Book>> currentBorrowings;
    // Complete borrowing history
    private LinkedList<BorrowingRecord> borrowingHistory;


    public LibraryCatalog() {
        this.booksByIsbn = new HashMap<>();
        this.booksByTitle = new TreeSet<>((b1, b2) -> 
            b1.getTitle().compareToIgnoreCase(b2.getTitle()));
        this.bookLoanCounts = new HashMap<>();
        this.currentBorrowings = new HashMap<>();
        this.borrowingHistory = new LinkedList<>();
    }

    public void addBook(Book book) {
        booksByIsbn.put(book.getIsbn(), book);
        booksByTitle.add(book);
        bookLoanCounts.putIfAbsent(book.getIsbn(), 0);  
    }

    public Book findBookByIsbn(String isbn) {
        return booksByIsbn.get(isbn);
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : booksByIsbn.values()) {
            if (author == book.getAuthor()) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public List<Book> getBooksSortedByTitle() {
        return new ArrayList<>(booksByTitle);
    }

    public List<Book> getMostPopularBooks(int limit) {
        return bookLoanCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(limit)
            .map(entry -> booksByIsbn.get(entry.getKey()))
            .toList();
    }

    public void addMember(LibraryMember member) {
        currentBorrowings.putIfAbsent(member, new HashSet<>());
    }

    public List<Book> getCurrentBorrowings(LibraryMember member) {
        return new ArrayList<>(currentBorrowings.getOrDefault(member, new HashSet<>()));
    }

    public List<Book> getBorrowingHistory(LibraryMember member) {
        List<Book> history = new ArrayList<>();
        for (BorrowingRecord record : borrowingHistory) {
            if (record.getMember().equals(member)) {
                history.add(record.getBook());
            }
        }
        return history;
    }

    public boolean checkoutBook(String isbn, LibraryMember member) {
        Book book = booksByIsbn.get(isbn);        
        // Validate book exists and is available
        if (book == null || !book.isAvailable()) {
            return false;
        }

        // Update book status
        book.setAvailable(false);
        // Update current borrowings
        currentBorrowings.computeIfAbsent(member, k -> new HashSet<>()).add(book);
        // Update loan count for popularity tracking
        bookLoanCounts.merge(isbn, 1, Integer::sum);
        // Add to borrowing history
        borrowingHistory.add(new BorrowingRecord(book, member));
        return true;
    }

    public boolean returnBook(String isbn, LibraryMember member) {
        Book book = booksByIsbn.get(isbn);
        Set<Book> memberBooks = currentBorrowings.get(member);
        // Validate book exists and was borrowed by this member
        if (book == null || memberBooks == null || !memberBooks.contains(book)) {
            return false;
        }
        // Update book status and borrowing records
        book.setAvailable(true);
        // Remove from current borrowings
        memberBooks.remove(book);
        if (memberBooks.isEmpty()) {
            currentBorrowings.remove(member);
        }
        // Update return date in history
        for (BorrowingRecord record : borrowingHistory) {
            if (record.getBook().equals(book) && 
                record.getMember().equals(member) && 
                record.getReturnDate() == null) {
                record.setReturnDate(new Date());
                break;
            }
        }
        return true;
    }

    // Helper method to get book loan count
    public int getBookLoanCount(String isbn) {
        return bookLoanCounts.getOrDefault(isbn, 0);
    }
}