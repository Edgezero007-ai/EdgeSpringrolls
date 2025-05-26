package Task2;

public class Task2 {
    public static void main(String[] args) {
        // Create primitives with specific values
        byte b = 3;
        short s = 1;
        int i = 10;
        float f = 2.0f;
        char c = ' ';
        boolean bool = true;
        String word = "w0rld";
        
        // Concatenate to form the required output
        String result = "H" + b + s + i + "\n" + word + c + f + c + bool;
        
        // Print the result
        System.out.println(result);
        
        // Additional example from the prompt
        byte zero = 0;
        String output = "W" + zero + "w";
        System.out.println(output);
    }
}