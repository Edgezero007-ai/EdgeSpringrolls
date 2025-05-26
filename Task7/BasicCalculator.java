package Task7;

import java.util.Scanner;

public class BasicCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Basic Arithmetic Calculator");
        System.out.println("--------------------------");
        
        // Get user input
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
        
        // Perform calculations
        double sum = add(num1, num2);
        double difference = subtract(num1, num2);
        double product = multiply(num1, num2);
        double quotient = divide(num1, num2);
        
        // Display results
        System.out.println("\nResults:");
        System.out.println(num1 + " + " + num2 + " = " + sum);
        System.out.println(num1 + " - " + num2 + " = " + difference);
        System.out.println(num1 + " * " + num2 + " = " + product);
        
        // Handle division by zero case
        if (Double.isInfinite(quotient) || Double.isNaN(quotient)) {
            System.out.println(num1 + " / " + num2 + " = Cannot divide by zero");
        } else {
            System.out.println(num1 + " / " + num2 + " = " + quotient);
        }
        
        scanner.close();
    }
    
    // Method to add two numbers
    public static double add(double a, double b) {
        return a + b;
    }
    
    // Method to subtract two numbers
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    // Method to multiply two numbers
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    // Method to divide two numbers
    public static double divide(double a, double b) {
        if (b == 0) {
            return Double.POSITIVE_INFINITY; // Represents division by zero
        }
        return a / b;
    }
}