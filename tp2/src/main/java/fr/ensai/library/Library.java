package fr.ensai.library;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
from Book import Book

/* lombok : Package pour aller plus vite (avoir des "Getter" plus vite)

/**
 * Represents a library.
 */
public class Library {

    // Attributes
    private String name;
    private ArrayList<Item> items;
    private ArrayList<Loan> activeLoans;
    private ArrayList<Loan> completedLoans;

    /**
     * Constructs a new Library object.
     */
    public Library(String name, ArrayList<Item> items, ArrayList<Loan> activeLoans, ArrayList<Loan> completedLoans) {
        this.name = name;
        this.items = items;
        this.activeLoans = activeLoans;
        this.completedLoans = completedLoans;
    }

    /**
     * Adds a book to the library’s collection.
     * 
     * @param book The book to be added to the library.
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Displays all books in the library.
     * Prints each book using the toString() method or prints a message if no books are available.
     */
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No items available in the library.");
        } else {
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    /**
     * Displays all loans in the library.
     * Prints each loan using the toString() method or prints a message if no loans are active or completed.
     */
    public void displayActiveLoans() {
        if (activeLoans.isEmpty()) {
            System.out.println("No active loans in the library.");
        } else {
            for (Loan loan : activeLoans) {
                System.out.println(loan);
            }
        }
        if (completedLoans.isEmpty()) {
            System.out.println("No completed loans in the library.");
        } else {
            for (Loan loan : completedLoans) {
                System.out.println(loan);
            }
        }
    }

    /**
     * Looks if the item is present in active loans.
     * Returns a message if there is no active loans.
     * 
     * @param item The item we are looking for
     */
    public Loan findActiveLoanForItem(Item item) {
        if (activeLoans.isEmpty()) {
            System.out.println("No active loans in the library.");
        } else {
            for (Loan loan : activeLoans) {
                if (equals(loan.item item)) {
                    return loan
                }
            }
            System.out.println("No active loans in the library for this item.");
        }
    }

    /**
     * List books written by an author
     * 
     * @param author The author we are interested in
     */
    public ArrayList<Book> getBooksByAuthor(Author author) {
        ArrayList<Book> booksByAuthor = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Book) {  // Check if the item is a Book
                Book book = (Book) item;
                if (book.getAuthor().equals(author)) {  // Compare the author
                    booksByAuthor.add(book);
                }
            }
        }
        return booksByAuthor;
    }

    /**
     * Checks if the item is available
     * Creates a loan if the item is available
     * 
     * @param item The item which is wanted
     * @param student The student who wants to loan the item
     */
    public boolean loanItem(Item item, Student student) {
        // Check if the item is currently on loan
        for (Loan loan : activeLoans) {
            if (loan.getItem().equals(item)) {  // If item is already on loan
                System.out.println("This item is already loaned.");
                return false;
            }
        }
        
        // Create a new loan
        String startDate = java.time.LocalDate.now().toString();  // Get today's date
        Loan newLoan = new Loan(student, item, startDate);
        
        // Add the loan to the activeLoans list
        activeLoans.add(newLoan);
        System.out.println("Loan created successfully.");
        
        return true;
    }

    /**
     * Finds if the loan exists
     * Adds a return date if the loan exists
     * Moves an item from activeLoans to completedLoans
     * 
     * @param item The item which is returned
     */
    public boolean renderItem(Item item) {
        // Find the loan for the item in the activeLoans list
        Loan loanToReturn = null;
        for (Loan loan : activeLoans) {
            if (loan.getItem().equals(item)) {
                loanToReturn = loan;
                break;
            }
        }

        if (loanToReturn == null) {
            System.out.println("No active loan found for this item.");
            return false;
        }

        // Add a return date to the loan (assuming the Loan class has a method setReturnDate)
        String returnDate = java.time.LocalDate.now().toString();  // Get today's date
        loanToReturn.setReturnDate(returnDate);
        
        // Move the loan to completedLoans
        activeLoans.remove(loanToReturn);
        completedLoans.add(loanToReturn);
        
        System.out.println("Item returned successfully.");
        
        return true;
    }


    /**
     * Loads books from a CSV file and adds them to the library.
     * 
     * @param filePath The path to the CSV file containing book data.
     * @throws IOException If there is an error reading the file, an
     *                     {@link IOException} will be thrown.
     */
    public void loadBooksFromCSV(String filePath) {

        URL url = getClass().getClassLoader().getResource(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(url.getFile()))) {
            Map<String, Author> authors = new HashMap<>();
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    String isbn = data[0].trim();
                    String title = data[1].trim();
                    String authorName = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    int pageCount = Integer.parseInt(data[4].trim());

                    // Check if author already exists in the map
                    Author author = authors.get(authorName);
                    if (author == null) {
                        author = new Author(authorName);
                        authors.put(authorName, author);
                        System.out.println(author.toString());
                    }
                    Book book = new Book(isbn, title, author, year, pageCount);

                    this.addIem(book);
                }
            }
        } catch (

        IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

}
