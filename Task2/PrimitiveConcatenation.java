public class PrimitiveConcatenation {
    public static void main(String[] args) {
        // Create primitive variables
        byte b = 3;
        short s = 1;
        int i = 0;
        float f = 2.0f;
        char c = 'H';
        boolean bool = true;
        String space = " ";
        String word = "world";
        
        // Concatenate to form "H310 world 2.0 true"
        String result = "" + c + b + s + i + space + word + space + f + space + bool;
        System.out.println(result);
        
        // Demonstrate the given example
        byte zero = 0;
        String output = 'W' + zero + 'w';  // This will perform addition first!
        System.out.println("Unexpected output: " + output);
        
        // Correct way to do the example
        String correctOutput = "" + 'W' + zero + 'w';
        System.out.println("Correct output: " + correctOutput);
    }
}