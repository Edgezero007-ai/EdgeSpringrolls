import java.util.Scanner;

public class IntegerChecker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter an integer: ");
            
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                
                if (number == 0) {
                    System.out.println("Zero");
                } else {
                    String parity = (number % 2 == 0) ? "Even" : "Odd";
                    String sign = (number > 0) ? "positive" : "negative";
                    System.out.println(parity + " and " + sign);
                }
            } else {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
    }
}