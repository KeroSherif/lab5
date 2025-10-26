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



public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StudentManager manager;

    public MainApp() {
        manager = new StudentManager();

        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ✅ إضافة شريط القوائم
        setupMenuBar();

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new HomePanel(this), "Home");
        mainPanel.add(new AddStudentPanel(manager), "AddStudent");
        mainPanel.add(new ViewStudentPanel(manager), "View");
        mainPanel.add(new UpdateStudentPanel(manager), "UpdateStudent");
        mainPanel.add(new DeleteStudent(manager), "DeleteStudent");
        mainPanel.add(new SearchStudentPanel(manager), "SearchStudent");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }

    // ✅ دالة لإنشاء شريط القوائم
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Navigation");
        navigationMenu.setMnemonic('N'); // اختصار Alt+N

        JMenuItem homeItem = new JMenuItem("Home");
        homeItem.setMnemonic('H'); // Alt+H
        homeItem.addActionListener(e -> showPanel("Home"));

        navigationMenu.add(homeItem);
        menuBar.add(navigationMenu);
        setJMenuBar(menuBar);
    }

    public void showPanel(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
