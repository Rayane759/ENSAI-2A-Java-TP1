package fr.ensai.library;

/**
 * Represents a magazine.
 */
public class Magazine extends Item{

    // Attributes
    private String issn;
    private int issueNumber;

    /**
     * Constructs a new Magazine object.
     */
    public Magazine(String issn, String title, int issueNumber, int year, int pageCount) {
        this.issn = issn;
        super.title = title;
        this.issueNumber = issueNumber;
        super.year = year;
        super.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Magazine " + title;
    }

}
