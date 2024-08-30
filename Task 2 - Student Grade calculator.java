//Task 2 - Student Grade calculator

import java.util.Scanner;

public class StudentGrades 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        
        // Input number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        // Array to hold marks for each subject
        int[] marks = new int[numSubjects];
        int totalMarks = 0;
        
        // Input marks for each subject
        for (int i = 0; i < numSubjects; i++) 
        {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            
            // Validate input
            while (marks[i] < 0 || marks[i] > 100) 
            {
                System.out.println("Marks should be between 0 and 100. Please enter again.");
                System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
                marks[i] = scanner.nextInt();
            }
            
            totalMarks += marks[i];
        }
        
        // Calculate average percentage
        double averagePercentage = (totalMarks * 100.0) / (numSubjects * 100);
        
        // Determine grade
        String grade = determineGrade(averagePercentage);
        
        // Display results
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("Grade: " + grade);
        
        scanner.close();
    }

    // Method to determine grade based on average percentage
    private static String determineGrade(double percentage) 
    {
        if (percentage >= 90) 
        {
            return "A";
        } 
        else if (percentage >= 80) 
        {
            return "B";
        } 
        else if (percentage >= 70) 
        {
            return "C";
        } 
        else if (percentage >= 60) 
        {
            return "D";
        } 
        else 
        {
            return "F";
        }
    }
}
