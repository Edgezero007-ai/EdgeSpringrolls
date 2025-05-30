interface Animal {
    boolean feed(boolean timeToEat);
    void groom();
    void pet();
}

class Gorilla implements Animal {
    // Implements the feed method from Animal interface
    @Override
    public boolean feed(boolean timeToEat) {
        if (timeToEat) {
            System.out.println("put gorilla food into cage");
            return true; // Food was provided
        }
        return false; // Not time to eat
    }

    // Implements the groom method from Animal interface
    @Override
    public void groom() {
        System.out.println("lather, rinse, repeat");
    }

    // Implements the pet method from Animal interface
    @Override
    public void pet() {
        System.out.println("pet at your own risk");
    }
}

public class Task16 {
    public static void main(String[] args) {
        Gorilla gorilla = new Gorilla();
        
        // Test the methods
        gorilla.feed(true);  // Output: put gorilla food into cage
        gorilla.groom();     // Output: lather, rinse, repeat
        gorilla.pet();       // Output: pet at your own risk
    }
}