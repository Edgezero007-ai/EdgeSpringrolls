import java.util.Scanner;

public class NumberPattern {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a positive integer: ");
            int n = scanner.nextInt();
            
            if (n <= 0) {
                System.out.println("Please enter a positive integer.");
            } else {
                System.out.println("Pattern:");
                for (int i = 1; i <= n; i++) {
                    // Print spaces
                    for (int j = 1; j <= n - i; j++) {
                        System.out.print("  ");
                    }
                    // Print increasing numbers
                    for (int j = 1; j <= i; j++) {
                        System.out.print(j + " ");
                    }
                    // Print decreasing numbers
                    for (int j = i - 1; j >= 1; j--) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}