//Task 5 - STUDENT COURSE REGISTRATION SYSTEM

import java.util.*;

public class SimpleCourseRegistrationSystem 
{

    // Course class
    static class Course 
    {
        String code;
        String title;
        String description;
        int capacity;
        int enrolled;

        Course(String code, String title, String description, int capacity) 
        {
            this.code = code;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.enrolled = 0;
        }

        boolean isAvailable() 
        {
            return enrolled < capacity;
        }

        void enroll() 
        {
            if (isAvailable()) enrolled++;
        }

        void drop() 
        {
            if (enrolled > 0) enrolled--;
        }

        @Override
        public String toString() 
        {
            return String.format("%s: %s (%d/%d enrolled)", code, title, enrolled, capacity);
        }
    }

    // Student class
    static class Student 
    {
        String id;
        String name;
        List<Course> courses = new ArrayList<>();

        Student(String id, String name) 
        {
            this.id = id;
            this.name = name;
        }

        void registerCourse(Course course) 
        {
            if (course.isAvailable()) 
            {
                courses.add(course);
                course.enroll();
                System.out.println(name + " registered for " + course.title);
            } 
            else 
            {
                System.out.println(course.title + " is full.");
            }
        }

        void dropCourse(Course course) 
        {
            if (courses.remove(course)) 
            {
                course.drop();
                System.out.println(name + " dropped " + course.title);
            } 
            else 
            {
                System.out.println(name + " is not registered for " + course.title);
            }
        }

        @Override
        public String toString() 
        {
            return name + " (" + id + ")";
        }
    }

    // Main method
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Map<String, Course> courseMap = new HashMap<>();
        Map<String, Student> studentMap = new HashMap<>();

        // Create courses from user input
        System.out.println("Enter the number of courses:");
        int numCourses = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (int i = 0; i < numCourses; i++) 
        {
            System.out.println("Enter course code:");
            String code = scanner.nextLine();
            System.out.println("Enter course title:");
            String title = scanner.nextLine();
            System.out.println("Enter course description:");
            String description = scanner.nextLine();
            System.out.println("Enter course capacity:");
            int capacity = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            courseMap.put(code, new Course(code, title, description, capacity));
        }

        // Create students from user input
        System.out.println("Enter the number of students:");
        int numStudents = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student ID:");
            String id = scanner.nextLine();
            System.out.println("Enter student name:");
            String name = scanner.nextLine();

            studentMap.put(id, new Student(id, name));
        }

        // Register students for courses
        while (true) 
        {
            System.out.println("\nEnter student ID to register/drop course (or 'exit' to quit):");
            String studentId = scanner.nextLine();
            if (studentId.equalsIgnoreCase("exit")) break;

            Student student = studentMap.get(studentId);
            if (student == null) 
            {
                System.out.println("Student not found.");
                continue;
            }

            System.out.println("Enter course code to register/drop:");
            String courseCode = scanner.nextLine();
            Course course = courseMap.get(courseCode);
            if (course == null) 
            {
                System.out.println("Course not found.");
                continue;
            }

            System.out.println("Type 'register' to register or 'drop' to drop the course:");
            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("register")) 
            {
                student.registerCourse(course);
            } 
            else if (action.equalsIgnoreCase("drop")) 
            {
                student.dropCourse(course);
            } 
            else 
            {
                System.out.println("Invalid action.");
            }
        }

        // Display remaining courses
        System.out.println("\nFinal Courses Status:");
        for (Course course : courseMap.values()) 
        {
            System.out.println(course);
        }

        scanner.close();
    }
}
