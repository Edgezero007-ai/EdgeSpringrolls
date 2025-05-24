package Task121;

public class MyDate {
    public static void main(String[] args) {
        // Create instances of DateTask with specified dates
        DateTask date1 = new DateTask(1978, 1, 1);
        DateTask date2 = new DateTask(1984, 9, 21);

        // Print dates
        System.out.println("Date 1: " + date1.toString());
        System.out.println("Date 2: " + date2.toString());

        // Display leap years
        System.out.println("Leap years:");
        DateTask.leapYears(); // Call static method using class name
    }
}
