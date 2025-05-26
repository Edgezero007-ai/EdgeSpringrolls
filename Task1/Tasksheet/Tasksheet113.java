public class Tasksheet113{
    public static void main(String[] args) {
        int check_number = 10;  // Initialize check_number to 10
        String message;         // Declare String variable 'message'

        // Loop from 1 to check_number (inclusive)
        for (int i = 1; i <= check_number; i++) {
            // Check if the number is even or odd using ternary operator
            message = (i % 2 == 0) ? i + " is even number" : i + " is odd number";
            
            // Print the message
            System.out.println(message);
        }
    }
}