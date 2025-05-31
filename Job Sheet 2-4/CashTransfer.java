import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CashTransfer {
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gcash_db";
    private static final String DB_USER = "gcash_user";
    private static final String DB_PASSWORD = "secure_password";
    
    // Transaction limits and constraints
    private static final double DAILY_LIMIT = 50000.00;
    private static final double MINIMUM_BALANCE = 100.00;
    private static final double MAX_SINGLE_TRANSFER = 10000.00;

    /**
     * Transfers money between accounts with validation checks
     * @param fromAccount Account ID to transfer from
     * @param toAccount Account ID to transfer to
     * @param amount Amount to transfer
     * @param currentUser Name of the user initiating transfer
     * @return true if successful, false if failed
     */
    public boolean cashTransfer(int fromAccount, int toAccount, double amount, String currentUser) {
        // Validate basic constraints
        if (amount <= 0) {
            System.out.println("Transfer amount must be positive");
            return false;
        }
        
        if (fromAccount == toAccount) {
            System.out.println("Cannot transfer to the same account");
            return false;
        }
        
        if (amount > MAX_SINGLE_TRANSFER) {
            System.out.printf("Amount exceeds maximum single transfer limit of ₱%.2f%n", MAX_SINGLE_TRANSFER);
            return false;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false); // Start transaction
            
            // 1. Check daily transfer limit
            double dailyTotal = getDailyTransfers(conn, fromAccount);
            if (dailyTotal + amount > DAILY_LIMIT) {
                System.out.printf("This transfer would exceed your daily limit of ₱%.2f%n", DAILY_LIMIT);
                return false;
            }
            
            // 2. Verify sender has sufficient balance
            double senderBalance = getBalance(conn, fromAccount);
            if (senderBalance < amount + MINIMUM_BALANCE) {
                System.out.printf("Insufficient funds. You must maintain a minimum balance of ₱%.2f%n", MINIMUM_BALANCE);
                return false;
            }
            
            // 3. Verify recipient account exists
            if (!accountExists(conn, toAccount)) {
                System.out.println("Recipient account not found");
                return false;
            }
            
            // 4. Execute the transfer
            updateBalance(conn, fromAccount, -amount); // Deduct from sender
            updateBalance(conn, toAccount, amount);   // Add to recipient
            
            // 5. Record the transaction
            recordTransaction(conn, fromAccount, toAccount, amount, currentUser, "Transfer");
            
            conn.commit(); // Commit if all operations succeed
            System.out.printf("Successfully transferred ₱%.2f to account %d%n", amount, toAccount);
            return true;
            
        } catch (SQLException e) {
            System.err.println("Transfer failed: " + e.getMessage());
            return false;
        }
    }

    // Helper methods
    private double getBalance(Connection conn, int accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            throw new SQLException("Account not found");
        }
    }

    private boolean accountExists(Connection conn, int accountId) throws SQLException {
        String sql = "SELECT 1 FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            return stmt.executeQuery().next();
        }
    }

    private void updateBalance(Connection conn, int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountId);
            if (stmt.executeUpdate() != 1) {
                throw new SQLException("Failed to update balance");
            }
        }
    }

    private double getDailyTransfers(Connection conn, int accountId) throws SQLException {
        String sql = "SELECT COALESCE(SUM(amount), 0) AS total FROM transactions " +
                     "WHERE account_ID = ? AND date >= CURDATE() AND type = 'Transfer'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getDouble("total") : 0;
        }
    }

    private void recordTransaction(Connection conn, int fromAccount, int toAccount, 
                                 double amount, String name, String type) throws SQLException {
        String sql = "INSERT INTO transactions (amount, name, account_ID, transferToID, date, type) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, name);
            stmt.setInt(3, fromAccount);
            stmt.setInt(4, toAccount);
            stmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(6, type);
            stmt.executeUpdate();
        }
    }

    // Interactive test method
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CashTransfer transfer = new CashTransfer();
            
            System.out.println("GCash Money Transfer");
            System.out.print("Enter your account ID: ");
            int fromAccount = scanner.nextInt();
            
            System.out.print("Enter recipient account ID: ");
            int toAccount = scanner.nextInt();
            
            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            
            transfer.cashTransfer(fromAccount, toAccount, amount, name);
        }
    }
}