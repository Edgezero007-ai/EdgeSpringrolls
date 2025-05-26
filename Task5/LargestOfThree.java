import java.util.Scanner;

public class LargestOfThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter three numbers:");
        System.out.print("First number: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Second number: ");
        double num2 = scanner.nextDouble();
        
        System.out.print("Third number: ");
        double num3 = scanner.nextDouble();
        
        if (num1 == num2 && num2 == num3) {
            System.out.println("All numbers are equal");
        } else {
            double largest = num1;
            if (num2 > largest) {
                largest = num2;
            }
            if (num3 > largest) {
                largest = num3;
            }
            System.out.println("The largest number is: " + largest);
        }
        
        scanner.close();
    }
}