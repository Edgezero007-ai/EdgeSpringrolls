import java.sql.*;
import java.util.Scanner;

public class CheckBalance {
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gcash_db";
    private static final String DB_USER = "gcash_user";
    private static final String DB_PASSWORD = "secure_password";

    /**
     * Retrieves the account balance for a given user ID
     * @param userId The user ID to check balance for
     * @return The account balance, or -1 if user not found
     */
    public double checkBalance(int userId) {
        String sql = "SELECT amount FROM Balance WHERE user_ID = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getDouble("amount");
            } else {
                System.out.println("User account not found!");
                return -1;
            }
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Main method to demonstrate the check balance functionality
     */
    public static void main(String[] args) {
        // Create dummy data in database (run once)
        initializeDummyData();
        
        // User interface
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("GCash Balance Check");
            System.out.print("Enter your User ID: ");
            int userId = scanner.nextInt();
            
            CheckBalance balanceChecker = new CheckBalance();
            double balance = balanceChecker.checkBalance(userId);
            
            if (balance >= 0) {
                System.out.printf("Your current balance is: ₱%.2f%n", balance);
            }
        }
    }

    /**
     * Helper method to initialize dummy data in the database
     */
    private static void initializeDummyData() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Balance ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY, "
                + "amount DECIMAL(15,2) NOT NULL, "
                + "user_ID INT NOT NULL UNIQUE)";
                
        String insertDataSQL = "INSERT IGNORE INTO Balance (amount, user_ID) VALUES "
                + "(5000.00, 1001), "
                + "(12500.50, 1002), "
                + "(3200.75, 1003)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            // Create table if not exists
            stmt.execute(createTableSQL);
            
            // Insert dummy data
            int rowsAffected = stmt.executeUpdate(insertDataSQL);
            if (rowsAffected > 0) {
                System.out.println("Dummy data initialized successfully");
            }
            
        } catch (SQLException e) {
            System.err.println("Error initializing dummy data: " + e.getMessage());
        }
    }
}