import java.util.Arrays;
import java.util.Scanner;

public class EnhancedSumAndAverage {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter numbers separated by spaces: ");
            String input = scanner.nextLine();
            
            // Split input into string array and convert to double array
            double[] numbers = Arrays.stream(input.split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            
            if (numbers.length == 0) {
                System.out.println("No numbers entered!");
                return;
            }
            
            double sum = Arrays.stream(numbers).sum();
            double average = sum / numbers.length;
            
            System.out.println("\nArray: " + Arrays.toString(numbers));
            System.out.printf("Sum: %.2f", sum);
            System.out.printf("\nAverage: %.2f", average);
            System.out.printf("\nCount: %d", numbers.length);
        }
    }
}