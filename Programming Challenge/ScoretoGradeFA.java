import java.util.Scanner;

public class ScoretoGradeFA {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter student's score: ");
            int score = scanner.nextInt();
            
            char grade;
            
            if (score >= 90) {
                grade = 'A';
            } else if (score >= 80) {
                grade = 'B';
            } else if (score >= 70) {
                grade = 'C';
            } else if (score >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            
            System.out.println("The student's grade is: " + grade);
        }
    }
}