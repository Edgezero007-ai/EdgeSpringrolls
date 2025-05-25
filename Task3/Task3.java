package Task3;

public class Task3 {
    public static void main(String[] args) {
        String a = "Wow";
        String b = a;
        String c = b;
        String d = c;
        String lc = a.toLowerCase();  // Convert to lowercase
        
        boolean b1 = a == b;
        boolean b2 = d.equals(b + "");
        boolean b3 = lc.equals(a.toLowerCase());
        
        if (b1 && b2 && b3) {
            System.out.println("Success!");
        }
    }
}