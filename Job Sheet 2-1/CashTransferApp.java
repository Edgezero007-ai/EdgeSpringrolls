import java.util.Scanner;
public class CashTransferApp {
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

        scanner.close(); // Close the scanner to avoid resource leak
    }
}

class CashTransfer {
    private String senderId;
    private String recipientId;
    private double amount;

    public CashTransfer(String senderId, String recipientId, double amount) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
    }

    public void executeTransfer() {
        System.out.println("Transferring $" + amount + " from " + senderId + " to " + recipientId + ".");
        // Add transfer logic here
    }
}