import java.sql.*;

public class GcashApp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/goashapp_db";
        String user = "goashapp_user";
        String password = "your_password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }
}