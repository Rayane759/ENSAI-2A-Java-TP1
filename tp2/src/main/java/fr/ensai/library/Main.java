package fr.ensai.library;

public class Main {

    public static void main(String[] args) {

        Author tolkien = new Author("J.R.R. Tolkien", 81, "UK");

        Book fellowshipOfTheRing = new Book(
                "978-0-618-26025-6",
                "The Fellowship of the Ring",
                tolkien,
                1954,
                423);

        System.out.println(fellowshipOfTheRing.toString());

        Library librairie = new Library("Librairie", List.of());

        try {
            librairie.loadBooksFromCSV("src/main/resources/books.csv");
        } catch (IOException e) {
            System.err.println("Error loading books from CSV: " + e.getMessage());
        }

        for (Book book : librairie.getBooks()) {
            System.out.println(book);
        }
    }
}