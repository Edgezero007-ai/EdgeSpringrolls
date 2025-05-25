import java.util.Scanner;

public class StringBuilderOperations {
    public static void main(String[] args) {
        // Prompt for input with at least 10 characters
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt for input with at least 10 characters
            System.out.print("Enter a string (minimum 10 characters): ");
            String input = scanner.nextLine();
            
            // Validate input length
            while (input.length() < 10) {
                System.out.print("String too short! Enter at least 10 characters: ");
                input = scanner.nextLine();
            }
            
            // Create StringBuilder
            StringBuilder sb = new StringBuilder(input);
            
            // 1. Print length
            System.out.println("\n1. Length: " + sb.length());
            
            // 2. Print first character
            System.out.println("2. First character: " + sb.charAt(0));
            
            // 3. Print last character
            System.out.println("3. Last character: " + sb.charAt(sb.length() - 1));
            
            // 4. Index of first 'a'
            int aIndex = sb.indexOf("a");
            System.out.println("4. First 'a' at index: " + (aIndex != -1 ? aIndex : "Not found"));
            
            // 5. Substring from index 3 to 6
            System.out.println("5. Substring (3-6): " + sb.substring(3, 7)); // endIndex is exclusive
            
            // 6. Append '123'
            sb.append("123");
            System.out.println("6. After appending '123': " + sb);
            
            // 7. Insert 'xyz' at index 4
            sb.insert(4, "xyz");
            System.out.println("7. After inserting 'xyz' at 4: " + sb);
            
            // 8. Delete substring (2-4)
            sb.delete(2, 5); // endIndex is exclusive (deletes 2,3,4)
            System.out.println("8. After deleting (2-4): " + sb);
            
            // 9. Delete character at index 8
            sb.deleteCharAt(8);
            System.out.println("9. After deleting char at 8: " + sb);
            
            // 10. Reverse the string
            sb.reverse();
            System.out.println("10. Reversed: " + sb);
        }
    }
}