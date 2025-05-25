public class MultiplicationTable {
    public static void main(String[] args) {
        int size = 10; // 10x10 table
        
        // Print header row
        System.out.print("    "); // Space for row labels
        for (int i = 1; i <= size; i++) {
            System.out.printf("%4d", i); // Column headers
        }
        System.out.println("\n-----------------------------------------");
        
        // Generate table
        for (int i = 1; i <= size; i++) {
            System.out.printf("%2d |", i); // Row label
            for (int j = 1; j <= size; j++) {
                System.out.printf("%4d", i * j); // Table cells
            }
            System.out.println(); // New line after each row
        }
    }
}