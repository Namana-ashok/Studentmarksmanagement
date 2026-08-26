import java.util.Scanner;

public class Studentmarksmanagement {

    static Scanner sc = new Scanner(System.in);

    static String[] names = new String[50];
    static int[] ages = new int[50];
    static int[][] marks = new int[50][5];

    static int studentCount = 0;

    // Add student
    static void addStudent() {

        if (studentCount >= 50) {
            System.out.println("Student limit reached!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        while (name.trim().isEmpty()) {
            System.out.print("Name cannot be empty. Enter again: ");
            name = sc.nextLine();
        }

        names[studentCount] = name;

        ages[studentCount] = getValidAge();

        System.out.println("\nEnter marks of 5 subjects:");

        for (int i = 0; i < 5; i++) {
            marks[studentCount][i] = getValidMark(i + 1);
        }

        studentCount++;

        System.out.println("\nStudent added successfully!");
    }

    // Validate age
    static int getValidAge() {

        while (true) {

            System.out.print("Enter Student Age: ");

            if (sc.hasNextInt()) {

                int age = sc.nextInt();

                if (age > 0 && age <= 100) {
                    return age;
                }
            } else {
                sc.next();
            }

            System.out.println("Invalid age! Please enter age between 1 and 100.");
        }
    }

    // Validate marks
    static int getValidMark(int subjectNumber) {

        while (true) {

            System.out.print("Subject " + subjectNumber + ": ");

            if (sc.hasNextInt()) {

                int mark = sc.nextInt();

                if (mark >= 0 && mark <= 100) {
                    return mark;
                }
            } else {
                sc.next();
            }

            System.out.println("Invalid mark! Please enter marks between 0 and 100.");
        }
    }

    // Calculate total
    static int calculateTotal(int student) {

        int total = 0;

        for (int i = 0; i < 5; i++) {
            total += marks[student][i];
        }

        return total;
    }

    // Calculate average
    static double calculateAverage(int student) {

        int total = calculateTotal(student);

        return total / 5.0;
    }

    // Calculate grade
    static char calculateGrade(double average) {

        if (average >= 90)
            return 'A';
        else if (average >= 75)
            return 'B';
        else if (average >= 60)
            return 'C';
        else if (average >= 50)
            return 'D';
        else
            return 'F';
    }

    // Find highest mark
    static int findHighest(int student) {

        int highest = marks[student][0];

        for (int i = 1; i < 5; i++) {

            if (marks[student][i] > highest) {
                highest = marks[student][i];
            }
        }

        return highest;
    }

    // Find lowest mark
    static int findLowest(int student) {

        int lowest = marks[student][0];

        for (int i = 1; i < 5; i++) {

            if (marks[student][i] < lowest) {
                lowest = marks[student][i];
            }
        }

        return lowest;
    }

    // Display all students
    static void displayStudents() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n========== ALL STUDENTS ==========");

        for (int i = 0; i < studentCount; i++) {

            displayStudent(i);
        }
    }

    // Display one student
    static void displayStudent(int student) {

        System.out.println("\nName     : " + names[student]);
        System.out.println("Age      : " + ages[student]);

        System.out.println("Marks:");

        for (int i = 0; i < 5; i++) {
            System.out.println("Subject " + (i + 1) + " : " + marks[student][i]);
        }

        int total = calculateTotal(student);
        double average = calculateAverage(student);
        char grade = calculateGrade(average);
        int highest = findHighest(student);
        int lowest = findLowest(student);

        System.out.println("Total    : " + total);
        System.out.println("Average  : " + average);
        System.out.println("Grade    : " + grade);
        System.out.println("Highest  : " + highest);
        System.out.println("Lowest   : " + lowest);

        System.out.println("----------------------------------");
    }

    // Search student by name
    static void searchStudent() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter student name to search: ");
        String searchName = sc.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < studentCount; i++) {

            if (names[i].equalsIgnoreCase(searchName)) {

                System.out.println("\nStudent Found!");
                displayStudent(i);

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Display top scorer
    static void displayTopScorer() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        int topStudent = 0;

        for (int i = 1; i < studentCount; i++) {

            if (calculateTotal(i) > calculateTotal(topStudent)) {
                topStudent = i;
            }
        }

        System.out.println("\n========== TOP SCORER ==========");

        System.out.println("Name    : " + names[topStudent]);
        System.out.println("Total   : " + calculateTotal(topStudent));
        System.out.println("Average : " + calculateAverage(topStudent));
        System.out.println("Grade   : " +
                calculateGrade(calculateAverage(topStudent)));
    }

    // Display class average
    static void displayClassAverage() {

        if (studentCount == 0) {
            System.out.println("\nNo students available.");
            return;
        }

        double totalAverage = 0;

        for (int i = 0; i < studentCount; i++) {
            totalAverage += calculateAverage(i);
        }

        double classAverage = totalAverage / studentCount;

        System.out.println("\n========== CLASS AVERAGE ==========");
        System.out.println("Class Average: " + classAverage);
    }

    // Menu
    static void menu() {

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     STUDENT MARKS MANAGEMENT");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Name");
            System.out.println("4. Display Top Scorer");
            System.out.println("5. Display Class Average");
            System.out.println("6. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    displayTopScorer();
                    break;

                case 5:
                    displayClassAverage();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice! Enter 1-6.");
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        menu();

        sc.close();
    }
}