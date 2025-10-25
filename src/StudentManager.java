/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kirolos sherif
 */
/**
 */
import java.io.*;
import java.util.*;

public class StudentManager{
    private List<Student> students;
    private String fileName = "students.csv";
    private int nextId = 1; // لتوليد ID تلقائي

    // Constructor: يحمل البيانات من الملف لو موجود
    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
        generateNextId(); // يحدد أول ID متاح بعد التحميل
    }

    // 1. Add Student (بدون ID من اليوزر — هيُولّد تلقائي)
    public void addStudent(String fullName, int age, String gender, String department, double gpa) {
        Student newStudent = new Student(nextId, fullName, age, gender, department, gpa);
        students.add(newStudent);
        nextId++;
        saveToFile();
    }

    // 2. Get all students (عشان الـView Panel يعرضهم في JTable)
    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // return copy for safety
    }

    // 3. Get student by ID
    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    // 4. Search by name (case-insensitive partial match)
    public List<Student> searchStudentsByName(String query) {
        List<Student> result = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Student s : students) {
            if (s.getFullName().toLowerCase().contains(lowerQuery)) {
                result.add(s);
            }
        }
        return result;
    }

    // 5. Update student
    public boolean updateStudent(int id, String fullName, int age, String gender, String department, double gpa) {
        Student s = getStudentById(id);
        if (s == null) return false;
        try {
            s.setFullName(fullName);
            s.setAge(age);
            s.setGender(gender);
            s.setDepartment(department);
            s.setGpa(gpa);
            saveToFile();
            return true;
        } catch (IllegalArgumentException e) {
            return false; // validation failed
        }
    }

    // 6. Delete student
    public boolean deleteStudent(int id) {
        Student s = getStudentById(id);
        if (s == null) return false;
        students.remove(s);
        saveToFile();
        return true;
    }

    // ============ File I/O ============

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                pw.printf("%d,%s,%d,%s,%s,%.2f%n",
                    s.getId(), s.getFullName(), s.getAge(),
                    s.getGender(), s.getDepartment(), s.getGpa());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        students.clear();
        File file = new File(fileName);
        if (!file.exists()) return; // لو مش موجود، يبدأ فارغ

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String gender = parts[3];
                        String dept = parts[4];
                        double gpa = Double.parseDouble(parts[5]);

                        // ✅ التعديل الوحيد: استخدام الـconstructor الآمن للتحميل
                        Student s = new Student(id, name, age, gender, dept, gpa, true);
                        students.add(s);
                    } catch (Exception e) {
                        // تجاهل السطر لو فيه بيانات فاسدة
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // يحدد أعلى ID + 1 علشان نبدأ منه في التوليد
    private void generateNextId() {
        int maxId = 0;
        for (Student s : students) {
            if (s.getId() > maxId) maxId = s.getId();
        }
        nextId = maxId + 1;
    }
}




































