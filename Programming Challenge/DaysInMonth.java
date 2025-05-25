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
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    System.out.println("31 days");
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    System.out.println("30 days");
                    break;
                case 2:
                    System.out.println("28 days");
                    break;
                default:
                    System.out.println("Invalid month number!");
                    break;
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