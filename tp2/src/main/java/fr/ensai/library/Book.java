package fr.ensai.library;

/**
 * Represents a book.
 */
public class Book extends Item{

    // Attributes
    private String isbn;
    private Author author;

    /**
     * Constructs a new Book object.
     */
    public Book(String isbn, String title, Author author, int year, int pageCount) {
        this.isbn = isbn;
        super.title = title;
        this.author = author;
        super.year = year;
        super.pageCount = pageCount;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book " + title + " written by " + author.toString();
    }

}
