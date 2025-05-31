import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gcash_db";
    private static final String DB_USER = "gcash_user";
    private static final String DB_PASSWORD = "secure_password";

    /**
     * Represents a transaction record
     */
    public static class Transaction {
        private int id;
        private double amount;
        private String name;
        private int accountId;
        private Timestamp date;
        private String type;
        private Integer transferToId;
        private Integer transferFromId;

        // Constructor, getters, and toString() omitted for brevity
        // (Include them in actual implementation)

        public int getAccountId() {
            return accountId;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", amount=" + amount +
                    ", name='" + name + '\'' +
                    ", accountId=" + accountId +
                    ", date=" + date +
                    ", type='" + type + '\'' +
                    ", transferToId=" + transferToId +
                    ", transferFromId=" + transferFromId +
                    '}';
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public void setDate(Timestamp timestamp) {
            this.date = timestamp;
        }

        public void setTransferFromId(int transferFromId) {
            this.transferFromId = transferFromId;
        }

        public void setTransferToId(int transferToId) {
            this.transferToId = transferToId;
        }

		public void setType(String type) {
			this.type = type;
		}
    }

    /**
     * Views all transactions in the database
     * @return List of all transactions
     */
    public List<Transaction> viewAll() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                transactions.add(extractTransaction(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error viewing all transactions: " + e.getMessage());
        }
        return transactions;
    }

    /**
     * Views all transactions for a specific user
     * @param userId The user's account ID
     * @return List of the user's transactions
     */
    public List<Transaction> viewUserAll(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transactions.add(extractTransaction(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error viewing user transactions: " + e.getMessage());
        }
        return transactions;
    }

    /**
     * Views a single transaction by ID
     * @param transactionId The transaction ID
     * @return The transaction, or null if not found
     */
    public Transaction viewTransaction(int transactionId) {
        String sql = "SELECT * FROM transactions WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractTransaction(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing transaction: " + e.getMessage());
        }
        return null;
    }

    /**
     * Helper method to extract transaction data from ResultSet
     */
    private Transaction extractTransaction(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setId(rs.getInt("ID"));
        t.setAmount(rs.getDouble("amount"));
        t.setName(rs.getString("name"));
        t.setAccountId(rs.getInt("account_ID"));
        t.setDate(rs.getTimestamp("date"));
        t.setType(rs.getString("type"));
        t.setTransferToId(rs.getInt("transferToID"));
        t.setTransferFromId(rs.getInt("transferFromID"));
        return t;
    }

    public static void main(String[] args) {
        Transactions txViewer = new Transactions();

        // Test viewAll
        System.out.println("All transactions:");
        txViewer.viewAll().forEach(System.out::println);

        // Test viewUserAll
        int testUserId = 1001;
        System.out.println("\nTransactions for user " + testUserId + ":");
        txViewer.viewUserAll(testUserId).forEach(System.out::println);

        // Test viewTransaction
        int testTxId = 5;
        System.out.println("\nTransaction with ID " + testTxId + ":");
        System.out.println(txViewer.viewTransaction(testTxId));
    }
}