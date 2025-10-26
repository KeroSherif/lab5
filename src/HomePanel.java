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
        JButton searchBtn = new JButton("Search Student");
        JPanel btnPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton logoutBtn = new JButton("Logout");

        // التنقل الفعلي للصفحات المطلوبة
        addBtn.addActionListener(e -> mainApp.showPanel("AddStudent"));
        viewBtn.addActionListener(e -> mainApp.showPanel("View"));
        updateBtn.addActionListener(e -> mainApp.showPanel("UpdateStudent"));
        
        searchBtn.addActionListener(e -> mainApp.showPanel("SearchStudent"));

        // لو مش هتعمل Delete Panel، اخلي زر الحذف يوجه لـSearch (عشان يقدر يدور ويحذف من هناك)
        // أو اخليه يظهر رسالة إن الحذف متاح من لوحة البحث
        deleteBtn.addActionListener(e -> mainApp.shoPanel("DeleteStudent"));
        });

        logoutBtn.addActionListener(e -> mainApp.showPanel("Login"));

        btnPanel.add(addBtn);
        btnPanel.add(viewBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(logoutBtn);

        add(btnPanel, BorderLayout.CENTER);
    }
}
