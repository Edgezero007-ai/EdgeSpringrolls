import java.util.Scanner;

public class VowelConsonantChecker {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine().toLowerCase();
            int vowels = 0, consonants = 0;
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                
                // Check if character is a letter
                if (Character.isLetter(ch)) {
                    // Check for vowels
                    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }   System.out.println("Number of vowels: " + vowels);
            System.out.println("Number of consonants: " + consonants);
        }
    }
}