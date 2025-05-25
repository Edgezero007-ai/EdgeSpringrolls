import java.util.Scanner;

public class AdvancedWordCounter {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a sentence: ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                System.out.println("No words found");
            } else {
                // Split by any non-word character (including punctuation)
                String[] words = input.split("[^\\p{L}']+");
                
                // Filter out empty strings from the array
                long wordCount = java.util.Arrays.stream(words)
                        .filter(word -> !word.isEmpty())
                        .count();
                
                System.out.println("Number of words: " + wordCount);
            }
        }
    }
}