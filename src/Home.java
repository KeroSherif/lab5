/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author DANAH
 */
public class HomePanel extends JPanel {
    private MainApp mainApp;

    public HomePanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());

       JLabel title = new JLabel("Welcome to the Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton logoutBtn = new JButton("Logout");
        
    addBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Go to Add Student Page"));
        viewBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Go to View Students Page"));
        updateBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Go to Update Student Page"));
        deleteBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Go to Delete Student Page"));
        logoutBtn.addActionListener(e -> mainApp.showPanel("Login"));