public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;
    private int timesLoaned;  // Track how many times this book has been borrowed

    public Book(String isbn, String title, String author, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    // Getters and setters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    public String toString() {
        String returnString =   "'" + title + "' " +
                                "by " + author + ", " +
                                "(ISBN " + isbn + ") " +
                                "Loan status: ";
        if (isAvailable) return returnString + "Available.";
        return returnString + "Out on loan.";
    }
}