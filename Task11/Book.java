// Book.java
public class Book {
    private String title;
    private String author;
    private int yearPublished;
    private double price;

    // Constructor to initialize all attributes
    public Book(String title, String author, int yearPublished, double price) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
    }

    // toString method to display book details
    @Override
    public String toString() {
        return "Title: '" + title + "'\n" +
               "Author: '" + author + "'\n" +
               "Year Published: " + yearPublished + "\n" +
               "Price: $" + String.format("%.2f", price);
    }
}