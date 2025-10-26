/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author monic
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddStudentPanel extends JPanel {
    private JTextField txtName, txtAge, txtDepartment, txtGpa;
    private JComboBox<String> comboGender;
    private JButton btnAdd;
    private StudentManager manager;

    public AddStudentPanel(StudentManager manager) {
        this.manager = manager;

        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Full Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Age:"));
        txtAge = new JTextField();
        add(txtAge);

        add(new JLabel("Gender:"));
        comboGender = new JComboBox<>(new String[]{"Male", "Female"});
        add(comboGender);

        add(new JLabel("Department:"));
        txtDepartment = new JTextField();
        add(txtDepartment);

        add(new JLabel("GPA:"));
        txtGpa = new JTextField();
        add(txtGpa);

        btnAdd = new JButton("Add Student");
        add(btnAdd);

        JButton btnClear = new JButton("Clear");
        add(btnClear);

        
        btnAdd.addActionListener(e -> {
            try {
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String gender = comboGender.getSelectedItem().toString();
                String dept = txtDepartment.getText().trim();
                double gpa = Double.parseDouble(txtGpa.getText().trim());

                if (name.isEmpty() || dept.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                manager.addStudent(name, age, gender, dept, gpa);
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please check your entries.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnClear.addActionListener(e -> clearFields());
    }

    private void clearFields() {
        txtName.setText("");
        txtAge.setText("");
        txtDepartment.setText("");
        txtGpa.setText("");
        comboGender.setSelectedIndex(0);
    }
}
































































































































































