import java.util.Scanner;

public class RunningSum {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int sum = 0;
            int count = 0;
            
            System.out.println("Enter integers to sum (type '=' to finish):");
            
            while (true) {
                System.out.print("Enter number #" + (count + 1) + ": ");
                
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    sum += num;
                    count++;
                    System.out.println("Running total: " + sum); // Show progress
                }
                else {
                    String input = scanner.next();
                    if (input.equals("=")) {
                        break;
                    }
                    System.out.println("Invalid input! Use integers or '=' to finish.");
                }
            }
            
            System.out.println("Final sum of " + count + " numbers: " + sum);
        }
    }
}