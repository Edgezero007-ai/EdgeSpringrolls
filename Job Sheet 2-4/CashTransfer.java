import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Remove the public class CashTransferApp from this file.
// Move the following code to a new file named CashTransferApp.java:

// public class CashTransferApp {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Online Banking Cash Transfer");
//         System.out.print("Enter your account number: ");
//         String senderId = scanner.nextLine();

//         System.out.print("Enter recipient account number: ");
//         String recipientId = scanner.nextLine();

//         System.out.print("Enter amount to transfer: ");
//         double amount = scanner.nextDouble();

//         CashTransfer transfer = new CashTransfer(senderId, recipientId, amount);
//         transfer.executeTransfer();

//         scanner.close(); // Close the scanner to avoid resource leak
//     }
// }

public class CashTransfer {
    private String senderId;
    private String recipientId;
    private double amount;

    public CashTransfer(String senderId, String recipientId, double amount) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    public void executeTransfer() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "username", "password");

            // Disable auto-commit mode
            connection.setAutoCommit(false);

            // Prepare the SQL statements
            String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

            // Prepare the debit statement
            preparedStatement = connection.prepareStatement(debitSQL);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, senderId);
            preparedStatement.executeUpdate();

            // Prepare the credit statement
            preparedStatement = connection.prepareStatement(creditSQL);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, recipientId);
            preparedStatement.executeUpdate();

            // Commit the transaction
            connection.commit();

            System.out.println("Transfer successful. Amount: " + amount);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
            try {
                if (connection != null) {
                    // Rollback the transaction in case of an error
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Close resources in the reverse order of their creation
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}