import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        // Prompt user to enter month number
        try (Scanner input = new Scanner(System.in)) {
            // Prompt user to enter month number
            System.out.print("Enter a month number (1-12): ");
            int month = input.nextInt();
            // Determine number of days using switch-case
            switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> // January
                    // December
                    System.out.println("31 days");
                case 4, 6, 9, 11 -> // March
                    // November
                    System.out.println("30 days");
                case 2 -> // May
                    // February
                    System.out.println("28 days");
                default -> // July
                    // Invalid input
                    System.out.println("Invalid month number!");
            }
            // January
            // March
            // May
            // July
            // August
            // October
            // April
            // June
            // September
                        // Close the scanner
        }
    }
}