
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