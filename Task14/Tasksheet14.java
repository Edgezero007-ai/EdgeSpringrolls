abstract class abstractClass {
    abstract void abstractMethod();
    
    void concreteMethod() { // concrete methods are still allowed in abstract classes
        System.out.println("This is a concrete method.");
    }
}

class B extends abstractClass {
    void abstractMethod() {
        System.out.println("B's implementation of abstractMethod.");
    }
}

class C extends abstractClass {
    // Implementation of the abstract method
    void abstractMethod() {
        System.out.println("C's implementation of abstractMethod.");
    }
}

public class Tasksheet14{
    public static void main(String args[]) {
        B b = new B();
        b.abstractMethod();    // Output: B's implementation of abstractMethod.
        b.concreteMethod();    // Output: This is a concrete method.
        
        C c = new C();
        c.abstractMethod();    // Output: C's implementation of abstractMethod.
        c.concreteMethod();    // Output: This is a concrete method.
    }
}