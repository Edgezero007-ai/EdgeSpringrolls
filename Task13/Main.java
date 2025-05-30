package Task13;

// Base class Vehicle
class Vehicle {
    String make;
    String model;
    int year;
}

// Derived class Car extending Vehicle
class Car extends Vehicle {
    int numberOfDoors;

    // Method to display car details
    public void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create an instance of Car
        Car myCar = new Car();

        // Set values for attributes
        myCar.make = "Toyota";
        myCar.model = "Camry";
        myCar.year = 2022;
        myCar.numberOfDoors = 4;

        // Display the car details
        myCar.displayDetails();
    }
}