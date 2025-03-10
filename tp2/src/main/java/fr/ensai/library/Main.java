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

        Magazine magazine8 = new Magazine(
            "888-8-888-88888-8",
            "Quelque",
            8888,
            1888,
            88
        );

        Magazine magazine9 = new Magazine(
            "999-9-999-99999-9",
            "Chose",
            9999,
            1999,
            99
        );

        Library librairie = new Library("Librairie", List.of());

        try {
            librairie.loadBooksFromCSV("src/main/resources/books.csv");
        } catch (IOException e) {
            System.err.println("Error loading items from CSV: " + e.getMessage());
        }

        for (Item item : librairie.displayItems()) {
            System.out.println(item);
        }
    }
}