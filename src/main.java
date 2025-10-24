/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Kirolos sherif
 */
/**
 * Test class for the Student Management System Backend.

 */
public class main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();

        System.out.println("=== Adding Students ===");
        try {
            manager.addStudent("Marina Sherif", 19, "Female", "CE", 3.8);
            manager.addStudent("Ahmed Ali", 20, "Male", "CS", 3.5);
            manager.addStudent("Layla Hassan", 18, "Female", "AI", 3.9);
            System.out.println(" Students added successfully.");
        } catch (Exception e) {
            System.err.println("❌ Error adding student: " + e.getMessage());
        }

        System.out.println("\n=== All Students ===");
        manager.getAllStudents().forEach(System.out::println);

        System.out.println("\n=== Searching for 'Ahmed' ===");
        var results = manager.searchStudentsByName("Ahmed");
        results.forEach(System.out::println);

        System.out.println("\n=== Updating Student ID=1 ===");
        if (manager.updateStudent(1, "Marina S.", 19, "Female", "Computer Engineering", 3.85)) {
            System.out.println(" Student updated.");
        } else {
            System.out.println("❌ Failed to update student.");
        }

        System.out.println("\n=== After Update ===");
        manager.getAllStudents().forEach(System.out::println);

        System.out.println("\n=== Deleting Student ID=2 ===");
        if (manager.deleteStudent(2)) {
            System.out.println(" Student deleted.");
        } else {
            System.out.println(" Student not found.");
        }

        System.out.println("\n=== Final List ===");
        manager.getAllStudents().forEach(System.out::println);

        System.out.println("\n?Data is automatically saved to 'students.csv'.");
        System.out.println("? Restart the program to verify file loading works!");
    }
}


