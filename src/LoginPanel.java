/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DANAH
 */


import javax.swing.*;
import java.awt.*;
import javax.swing.text.JTextComponent;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private MainApp mainApp;

    public LoginPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new GridBagLayout());
        setBackground(new Color(18, 18, 18));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(14, 14, 14, 14);

        JLabel title = new JLabel("Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);


 JLabel subtitle = new JLabel("Sign in to continue", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(176, 176, 176));
        gbc.gridy++;
        add(subtitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        add(userLabel, gbc);

        usernameField = new JTextField(18);
        styleTextField(usernameField);
        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridy++;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        add(passLabel, gbc);
        passwordField = new JPasswordField(18);
        styleTextField(passwordField);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn, new Color(33, 150, 243), Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginBtn.setFocusPainted(false);
        loginBtn.addActionListener(e -> handleLogin());
        gbc.gridx = 0; gbc.gridwidth = 2; gbc.gridy++;
        add(loginBtn, gbc);
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();


        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password!", "Missing Fields", JOptionPane.WARNING_MESSAGE);
        } else if ("admin".equals(username) && "1234".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainApp.showPanel("Home");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleTextField(JTextComponent field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.WHITE);
        field.setBackground(new Color(30, 30, 30));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 51, 51), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(140, 44));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}


  if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password!", "Missing Fields", JOptionPane.WARNING_MESSAGE);
        } else if ("admin".equals(username) && "1234".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            mainApp.showPanel("Home");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleTextField(JTextComponent field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.WHITE);
        field.setBackground(new Color(30, 30, 30));
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 51, 51), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
    }

 private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(140, 44));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}