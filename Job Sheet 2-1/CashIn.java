import java.sql.*;
import java.time.LocalDateTime;

public class CashIn {
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gcash_db";
    private static final String DB_USER = "gcash_user";
    private static final String DB_PASSWORD = "secure_password";

    /**
     * Adds cash to a user's account balance and records the transaction
     * @param accountId The account ID to deposit to
     * @param amount The amount to deposit (must be positive)
     * @param name The name associated with the transaction
     * @return true if successful, false if failed
     */
    public boolean cashIn(int accountId, double amount, String name) {
        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false); // Start transaction

            // 1. Update account balance
            updateBalance(conn, accountId, amount);

            // 2. Record transaction
            recordTransaction(conn, accountId, amount, name, "Cash-In");

            conn.commit(); // Commit transaction
            System.out.printf("Successfully cashed in ₱%.2f to account %d%n", amount, accountId);
            return true;

        } catch (SQLException e) {
            System.err.println("Cash-in failed: " + e.getMessage());
            return false;
        }
    }

    // Helper method to update account balance
    private void updateBalance(Connection conn, int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated != 1) {
                throw new SQLException("Account not found or update failed");
            }
        }
    }

    // Helper method to record transaction
    private void recordTransaction(Connection conn, int accountId, double amount, 
                                 String name, String type) throws SQLException {
        String sql = "INSERT INTO transactions (amount, name, account_ID, date, type) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, name);
            stmt.setInt(3, accountId);
            stmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(5, type);
            stmt.executeUpdate();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        CashIn cashIn = new CashIn();
        
        // Test cases
        boolean firstTransaction = cashIn.cashIn(1001, 200.00, "Juan Dela Cruz");
        boolean secondTransaction = cashIn.cashIn(1001, 300.00, "Juan Dela Cruz");
        
        if (firstTransaction && secondTransaction) {
            System.out.println("Both transactions completed successfully");
        }
    }
}