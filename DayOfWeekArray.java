import java.util.Scanner;

public class DayOfWeekArray {
    public static void main(String[] args) {
        String[] days = {"Monday", "Tuesday", "Wednesday", 
                         "Thursday", "Friday", "Saturday", "Sunday"};
        
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a number (1-7): ");
            
            if (input.hasNextInt()) {
                int num = input.nextInt();
                if (num >= 1 && num <= 7) {
                    System.out.println(days[num - 1]);
                } else {
                    System.out.println("Invalid number!");
                }
            } else {
                System.out.println("Numbers only!");
            }
        }
    }
}