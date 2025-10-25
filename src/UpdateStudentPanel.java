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

public class UpdateStudentPanel extends JPanel {
    private JTextField txtId, txtName, txtAge, txtDepartment, txtGpa;
    private JComboBox<String> comboGender;
    private JButton btnLoad, btnUpdate;
    private StudentManager manager;

    public UpdateStudentPanel(StudentManager manager) {
        this.manager = manager;
        setLayout(new GridLayout(8, 2, 10, 10));

        add(new JLabel("Enter Student ID:"));
        txtId = new JTextField();
        add(txtId);

        btnLoad = new JButton("Load Student");
        add(btnLoad);
        add(new JLabel("")); // empty cell

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

        btnUpdate = new JButton("Update Student");
        add(btnUpdate);

        // Load student data
        btnLoad.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Student s = manager.getStudentById(id);
                if (s == null) {
                    JOptionPane.showMessageDialog(this, "Student not found!");
                    return;
                }
                txtName.setText(s.getFullName());
                txtAge.setText(String.valueOf(s.getAge()));
                comboGender.setSelectedItem(s.getGender());
                txtDepartment.setText(s.getDepartment());
                txtGpa.setText(String.valueOf(s.getGpa()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format!");
            }
        });

        // Update student info
        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String gender = comboGender.getSelectedItem().toString();
                String dept = txtDepartment.getText().trim();
                double gpa = Double.parseDouble(txtGpa.getText().trim());

                boolean success = manager.updateStudent(id, name, age, gender, dept, gpa);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Student updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed. Check inputs.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating student.");
            }
        });
    }
}


































































