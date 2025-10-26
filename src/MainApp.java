/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.*;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StudentManager manager;

    public MainApp() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("System L&F not available. Using default.");
        }
        manager = new StudentManager();
        setTitle("Student Management System");
        setSize(880, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(18, 18, 18));
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(18, 18, 18));

        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new HomePanel(this), "Home");
        mainPanel.add(new AddStudentPanel(manager), "AddStudent");
        mainPanel.add(new ViewStudentPanel(manager), "View");
        mainPanel.add(new UpdateStudentPanel(manager), "UpdateStudent");
        mainPanel.add(new DeleteStudent(manager), "DeleteStudent"); // ⚠ لاحظ: في الكود الأصلي فيه خطأ هنا (هشرحه تحت)
        mainPanel.add(new SearchStudentPanel(manager), "SearchStudent");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}




