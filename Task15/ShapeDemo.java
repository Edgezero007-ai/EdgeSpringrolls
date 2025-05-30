// Shape interface
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

// AbstractShape class implementing Shape
abstract class AbstractShape implements Shape {
    protected String color;
    protected double length;
    protected double width;

    public AbstractShape(String color, double length, double width) {
        this.color = color;
        this.length = length;
        this.width = width;
    }

    @Override
    public abstract double calculateArea();

    @Override
    public abstract double calculatePerimeter();
}

// Circle class extending AbstractShape
class Circle extends AbstractShape {
    private double radius;

    public Circle(String color, double radius) {
        super(color, radius, radius); // Using radius for both length and width in superclass
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle [color=" + color + ", radius=" + radius + "]";
    }
}

// Rectangle class extending AbstractShape
class Rectangle extends AbstractShape {
    public Rectangle(String color, double length, double width) {
        super(color, length, width);
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "Rectangle [color=" + color + ", length=" + length + ", width=" + width + "]";
    }
}

// Main class to test the implementation
public class ShapeDemo {
    public static void main(String[] args) {
        // Create instances of Circle and Rectangle
        Circle circle = new Circle("Red", 5.0);
        Rectangle rectangle = new Rectangle("Blue", 4.0, 6.0);

        // Display their areas and perimeters
        System.out.println(circle);
        System.out.println("Area: " + circle.calculateArea());
        System.out.println("Perimeter: " + circle.calculatePerimeter());
        
        System.out.println();
        
        System.out.println(rectangle);
        System.out.println("Area: " + rectangle.calculateArea());
        System.out.println("Perimeter: " + rectangle.calculatePerimeter());
    }
}