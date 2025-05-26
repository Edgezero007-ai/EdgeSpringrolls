package Task8;

public class CumulativeSumCalculator {
    public static void main(String[] args) {
        // Example usage
        System.out.println("Calculating for inputs (4, 5, 10):");
        processNumbers(4, 5, 10);
        
        System.out.println("\nCalculating for inputs (1, 2, 3, 4):");
        processNumbers(1, 2, 3, 4);
        
        System.out.println("\nCalculating for inputs (7):");
        processNumbers(7);
    }
    
    /**
     * Method that takes variable integer arguments and:
     * 1. Calculates the sum of all parameters
     * 2. For each parameter, calculates the cumulative sum up to that number
     *    (e.g., for 4: 1+2+3+4 = 10)
     */
    public static void processNumbers(int... numbers) {
        int totalSum = 0;
        
        System.out.println("Individual cumulative sums:");
        for (int num : numbers) {
            int cumulativeSum = calculateCumulativeSum(num);
            System.out.printf("For %d: %d (1+2+...+%d)%n", num, cumulativeSum, num);
            totalSum += num;
        }
        
        System.out.println("Sum of all parameters: " + totalSum);
    }
    
    /**
     * Helper method to calculate the cumulative sum up to a number
     * (Triangular number calculation)
     */
    private static int calculateCumulativeSum(int n) {
        return n * (n + 1) / 2; // Formula for sum of first n natural numbers
    }
}