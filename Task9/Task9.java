package Task9;

import static java.lang.Math.*;

public class Task9 {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static float divide(int a, int b) {
        return (float) a / b;
    }

    public static void main(String[] args) {
        // Demonstrate addition
        int sum = add(10, 5);
        System.out.println("Addition: 10 + 5 = " + sum);

        // Demonstrate subtraction
        int difference = subtract(10, 5);
        System.out.println("Subtraction: 10 - 5 = " + difference);

        // Demonstrate multiplication
        int product = multiply(10, 5);
        System.out.println("Multiplication: 10 × 5 = " + product);

        // Demonstrate division
        float quotient = divide(10, 5);
        System.out.println("Division: 10 ÷ 5 = " + quotient);

        // Using Math class methods (though note these exact methods don't exist in standard Math)
        // This part is included to demonstrate static import concept
        System.out.println("\nUsing Math class methods:");
        System.out.println("Square root of 25: " + sqrt(25));
        System.out.println("Power of 2^3: " + pow(2, 3));
        System.out.println("Absolute value of -5: " + abs(-5));
    }
}