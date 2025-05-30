package Task16;
public class Gorilla implements Animal {
    public boolean feed(boolean timeToEat) {
        // put gorilla food into cage
        if (timeToEat) {
            System.out.println("Feeding the gorilla...");
            return true;
        }
        return false;
    }

    public void groom() {
        // lather, rinse, repeat
        System.out.println("Grooming the gorilla...");
    }
    public void pet() {
        // pet at your own risk
        System.out.println("Attempting to pet the gorilla...");
    }
}