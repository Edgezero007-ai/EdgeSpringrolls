package Task9;

import static java.lang.Math.*; // Static import for Math class

public class Task9 {

    public static int add(int a, int b) {
        return a + b; // Simple addition
    }

    public static int subtract(int a, int b) {
        return a - b; // Simple subtraction
    }

    public static int multiply(int a, int b) {
        return a * b; // Simple multiplication
    }

    public static float divide(int a, int b) {
        if (b == 0) {
            return Float.NaN; // Handle division by zero
        }
        return (float)a / b; // Division with float result
    }

    public static void main(String[] args) {
        // Demonstrate math operations
        System.out.println("Math Operations Demonstration");
        System.out.println("----------------------------");
        
        // Addition examples
        System.out.println("Addition:");
        System.out.println("5 + 3 = " + add(5, 3));
        System.out.println("-2 + 7 = " + add(-2, 7));
        
        // Subtraction examples
        System.out.println("\nSubtraction:");
        System.out.println("10 - 4 = " + subtract(10, 4));
        System.out.println("5 - 9 = " + subtract(5, 9));
        
        // Multiplication examples
        System.out.println("\nMultiplication:");
        System.out.println("6 * 7 = " + multiply(6, 7));
        System.out.println("-3 * 4 = " + multiply(-3, 4));
        
        // Division examples
        System.out.println("\nDivision:");
        System.out.println("15 / 3 = " + divide(15, 3));
        System.out.println("10 / 4 = " + divide(10, 4));
        System.out.println("7 / 0 = " + divide(7, 0));
        
        // Using static imports from Math class
        System.out.println("\nUsing Math class static methods:");
        System.out.println("Square root of 25: " + sqrt(25));
        System.out.println("Power of 2^3: " + pow(2, 3));
        System.out.println("Absolute value of -5: " + abs(-5));
    }
}