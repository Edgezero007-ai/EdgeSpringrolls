// BookTest.java
public class BookTest {
    public static void main(String[] args) {
        // Create three Book objects with different values
        Book book1 = new Book("Java Programming", "John Smith", 2021, 39.99);
        Book book2 = new Book("Python Basics", "Jane Doe", 2020, 29.99);
        Book book3 = new Book("C++ Essentials", "Michael Johnson", 2019, 49.99);

        // Print details of each book
        System.out.println("Book 1:");
        System.out.println(book1);
        System.out.println();

        System.out.println("Book 2:");
        System.out.println(book2);
        System.out.println();

        System.out.println("Book 3:");
        System.out.println(book3);
    }
}