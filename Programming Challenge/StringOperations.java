import java.util.Scanner;

public class StringOperations {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();
            
            // 1. Print string length
            System.out.println("1. Length: " + input.length());
            
            // 2. Convert to uppercase
            System.out.println("2. Uppercase: " + input.toUpperCase());
            
            // 3. Convert to lowercase
            System.out.println("3. Lowercase: " + input.toLowerCase());
            
            // 4. Print first character
            if (!input.isEmpty()) {
                System.out.println("4. First character: " + input.charAt(0));
            } else {
                System.out.println("4. First character: (String is empty)");
            }
            
            // 5. Print last character
            if (!input.isEmpty()) {
                System.out.println("5. Last character: " + input.charAt(input.length() - 1));
            } else {
                System.out.println("5. Last character: (String is empty)");
            }
            
            // 6. Print substring (2nd to 5th character)
            if (input.length() >= 5) {
                System.out.println("6. Substring (2nd-5th): " + input.substring(1, 5));
            } else {
                System.out.println("6. Substring (2nd-5th): (String too short)");
            }
        }
    }
}