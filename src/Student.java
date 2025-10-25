/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 /**
 *
 * @author Kirolos sherif
 */
/**
 * Represents a student in the system.
 * Inherits from Person to demonstrate OOP Inheritance.
 */
public class Student extends Person {
    private int id;
    private String department;
    private double gpa;

    // Constructor for user input (with validation via setters)
    public Student(int id, String fullName, int age, String gender, String department, double gpa) {
        super(fullName, age, gender); // Pass common data to Person
        setId(id);
        setDepartment(department);
        setGpa(gpa);
    }

    // Constructor for loading from file (trusted data, no validation)
    public Student(int id, String fullName, int age, String gender, String department, double gpa, boolean trusted) {
        super(fullName, age, gender); // Still required when inheriting
        this.id = id;
        this.department = department;
        this.gpa = gpa;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDepartment() {
        return department;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters with validation
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be a positive integer.");
        }
        this.id = id;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty.");
        }
        this.department = department.trim();
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
        }
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}





























