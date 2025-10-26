/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kirolos sherif
 */
/**
 /**
 * Base class representing a person with common attributes.
 * Used to demonstrate Inheritance and Abstraction in OOP.
 */
public class Person {
    protected String fullName;
    protected int age;
    protected String gender;

    public Person(String fullName, int age, String gender) {
        setFullName(fullName);
        setAge(age);
        setGender(gender);
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
}