import java.sql.*;
import java.util.Scanner;

public class CashTransfer {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    private static final String DB_USER = "bank_user";
    private static final String DB_PASSWORD = "secure_password";
    
    // Transaction limits
    private static final double DAILY_LIMIT = 10000.00;
    private static final double MINIMUM_BALANCE = 100.00;

    private String senderAccountId;
    private String recipientAccountId;
    private double amount;

    public CashTransfer(String senderAccountId, String recipientAccountId, double amount) {
        this.senderAccountId = senderAccountId;
        this.recipientAccountId = recipientAccountId;
        this.amount = amount;
    }

    public boolean executeTransfer() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false); // Start transaction
            
            // 1. Validate accounts exist
            if (!accountExists(conn, senderAccountId) || !accountExists(conn, recipientAccountId)) {
                System.out.println("Invalid account number(s)");
                return false;
            }
            
            // 2. Check sender has sufficient balance (including minimum balance requirement)
            double senderBalance = getBalance(conn, senderAccountId);
            if (senderBalance < amount + MINIMUM_BALANCE) {
                System.out.println("Insufficient funds. Minimum balance must be maintained.");
                return false;
            }
            
            // 3. Check daily transaction limit
            if (amount > DAILY_LIMIT) {
                System.out.println("Transfer amount exceeds daily limit of $" + DAILY_LIMIT);
                return false;
            }
            
            // 4. Check sender and recipient are different
            if (senderAccountId.equals(recipientAccountId)) {
                System.out.println("Cannot transfer to same account");
                return false;
            }
            
            // 5. Execute the transfer
            updateBalance(conn, senderAccountId, -amount); // Deduct from sender
            updateBalance(conn, recipientAccountId, amount); // Add to recipient
            
            // 6. Log the transaction
            logTransaction(conn, senderAccountId, recipientAccountId, amount);
            
            conn.commit(); // Commit transaction if all steps succeed
            System.out.println("Transfer successful!");
            return true;
            
        } catch (SQLException e) {
            System.err.println("Transfer failed: " + e.getMessage());
            return false;
        }
    }

    // Helper methods
    private boolean accountExists(Connection conn, String accountId) throws SQLException {
        String sql = "SELECT 1 FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountId);
            return stmt.executeQuery().next();
        }
    }

    private double getBalance(Connection conn, String accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            throw new SQLException("Account not found");
        }
    }

    private void updateBalance(Connection conn, String accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, amount);
            stmt.setString(2, accountId);
            int rows = stmt.executeUpdate();
            if (rows != 1) {
                throw new SQLException("Failed to update balance");
            }
        }
    }

    private void logTransaction(Connection conn, String from, String to, double amount) throws SQLException {
        String sql = "INSERT INTO transactions (from_account, to_account, amount, timestamp) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, from);
            stmt.setString(2, to);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Online Banking Cash Transfer");
        System.out.print("Enter your account number: ");
        String senderId = scanner.nextLine();
        
        System.out.print("Enter recipient account number: ");
        String recipientId = scanner.nextLine();
        
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        
        CashTransfer transfer = new CashTransfer(senderId, recipientId, amount);
        transfer.executeTransfer();
    }
}