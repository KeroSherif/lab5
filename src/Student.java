/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 /**
 *
 * @author marina sherif
 */
public class Student {
    private int id;
    private String fullName;
    private int age;
    private String gender; // "Male" or "Female"
    private String department;
    private double gpa;

    // Constructor
    public Student(int id, String fullName, int age, String gender, String department, double gpa) {
        setId(id);
        setFullName(fullName);
        setAge(age);
        setGender(gender);
        setDepartment(department);
        setGpa(gpa);
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
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

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }
        this.fullName = fullName.trim();
    }

    public void setAge(int age) {
        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Age must be between 16 and 100.");
        }
        this.age = age;
    }

    public void setGender(String gender) {
        if (!"Male".equalsIgnoreCase(gender) && !"Female".equalsIgnoreCase(gender)) {
            throw new IllegalArgumentException("Gender must be 'Male' or 'Female'.");
        }
        this.gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
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

    // Optional: toString for debugging or display
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