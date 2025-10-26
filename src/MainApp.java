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

public MainApp() {
        setTitle("Student Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        LoginPanel loginPanel = new LoginPanel(this);
        HomePanel homePanel = new HomePanel(this);
        
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(homePanel, "Home");

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


































