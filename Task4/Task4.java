import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        // Create a reversed version of the input string using StringBuilder
        StringBuilder reversedBuilder = new StringBuilder(input);
        reversedBuilder.reverse();
        String reversed = reversedBuilder.toString();
        
        // Check if the input string is equal to the reversed string
        if (input.equals(reversed)) {
            System.out.println("The input string is a palindrome.");
        } else {
            System.out.println("The input string is not a palindrome.");
        }
        
        scanner.close();
    }
}