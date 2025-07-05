Exercise 4a: Data Structures

Your code needs to implement these functionalities:
1. Book Management
• Store the library’s book collection
• Allow quick lookups by ISBN
• Maintain books in alphabetical order by title • Track which books are currently available
• Track most frequently borrowed books
2. Book Borrowing System
• Implement checkout and return functionality
• Keep track of which books each member has borrowed • Maintain a chronological history of all borrowings
• Allow looking up a member’s past borrowings
You need to employ appropriate data structures to enable the above functionalities. Think about the collections covered in the lecture and choose the most stuiable ones for your implementation. Consider the following:
• When to use ordered vs unordered collections
• Trade-offs between sorting on demand vs maintaining sorted order • How to efficiently track and update book popularity
• How to handle multiple copies of the same book

Exercise 4b: Management Logic
Your LibraryCatalog class should implement these methods:
    // Basic book operations
void addBook(Book book);
Book findBookByIsbn(String isbn);
List<Book> findBooksByAuthor(String author);
// Sorted book operations
List<Book> getBooksSortedByTitle();
List<Book> getMostPopularBooks(int limit);
// Member operations
void addMember(LibraryMember member);
List<Book> getCurrentBorrowings(LibraryMember member); List<Book> getBorrowingHistory(LibraryMember member);
// Borrowing operations
boolean checkoutBook(String isbn, LibraryMember member); boolean returnBook(String isbn, LibraryMember member);
1
Provide efficient operations for all required functionalities. Consider the following issues:
• How would you implement getMostPopularBooks() efficiently? • What are the trade-offs of your collection choices?
• How would performance change with a large library catalog?

Exercise 4c (stretch target): Extension Ideas
Think about how to modify your code to incorporate these additional features:
• Implement a waitlist system for popular books
• Add a book recommendation system based on borrowing history • Create a fine calculation system for overdue books
