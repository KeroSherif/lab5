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
        setBackground(new Color(18, 18, 18));

        JLabel title = new JLabel("Welcome to the Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(26, 26, 26));
        title.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        add(title, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(6, 1, 20, 20));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(40, 70, 40, 70));
        btnPanel.setBackground(new Color(18, 18, 18));
        
 JButton addBtn = createStyledButton(" Add Student", new Color(76, 175, 80));
        JButton viewBtn = createStyledButton(" View Students", new Color(33, 150, 243));
        JButton updateBtn = createStyledButton(" Update Student", new Color(255, 152, 0));
        JButton searchBtn = createStyledButton(" Search Student", new Color(156, 39, 176));
        JButton deleteBtn = createStyledButton(" Delete Student", new Color(244, 67, 54));
        JButton logoutBtn = createStyledButton(" Logout", new Color(120, 120, 120));

        addBtn.addActionListener(e -> mainApp.showPanel("AddStudent"));
        viewBtn.addActionListener(e -> mainApp.showPanel("View"));
        updateBtn.addActionListener(e -> mainApp.showPanel("UpdateStudent"));
        searchBtn.addActionListener(e -> mainApp.showPanel("SearchStudent"));
        deleteBtn.addActionListener(e -> mainApp.showPanel("DeleteStudent"));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                mainApp.showPanel("Login");
            }
        });

btnPanel.add(addBtn);
        btnPanel.add(viewBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(logoutBtn);

        add(btnPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(220, 58));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}

