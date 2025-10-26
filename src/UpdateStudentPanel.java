/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author monic
 */

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.DefaultListCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateStudentPanel extends JPanel {
    private JTextField txtId, txtName, txtAge, txtDepartment, txtGpa;
    private JComboBox<String> comboGender;
    private JButton btnUpdate;
    private final StudentManager manager;
    private boolean fieldsEditable = false;

    public UpdateStudentPanel(StudentManager manager) {
        this.manager = manager;
        setLayout(new GridBagLayout());
        setBackground(new Color(18, 18, 18));
        GridBagConstraints gbc = baseConstraints();

        // زرار الرجوع
        JButton backBtn = new JButton("⬅ Back to Home");
        styleBackButton(backBtn);
        backBtn.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame instanceof MainApp) {
                ((MainApp) frame).showPanel("Home");
            }
        });
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(backBtn, gbc);
        gbc.gridy++;

        addTitle("✏ Update Student", gbc);
        addRow("Student ID:", txtId = field(), gbc);

        txtName = field(); txtName.setEditable(false);
        txtAge = field(); txtAge.setEditable(false);
        txtDepartment = field(); txtDepartment.setEditable(false);
        txtGpa = field(); txtGpa.setEditable(false);
        comboGender = genderBox();

        addRow("Full Name:", txtName, gbc);
        addRow("Age:", txtAge, gbc);
        addRow("Gender:", comboGender, gbc);
        addRow("Department:", txtDepartment, gbc);
        addRow("GPA:", txtGpa, gbc);

        addButton("🔄 Update Student", new Color(255, 152, 0), gbc);

        txtId.addActionListener(this::loadStudent);
    }

    private void loadStudent(ActionEvent e) {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Missing ID", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Student s = manager.getStudentById(id);
            if (s != null) {
                enableFields(true);
                txtName.setText(s.getFullName());
                txtAge.setText(String.valueOf(s.getAge()));
                comboGender.setSelectedItem(s.getGender());
                txtDepartment.setText(s.getDepartment());
                txtGpa.setText(String.valueOf(s.getGpa()));
                JOptionPane.showMessageDialog(this, "Student loaded successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                enableFields(false);
                clearFields();
                JOptionPane.showMessageDialog(this, "No student found with ID: " + id, "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            enableFields(false);
            clearFields();
            JOptionPane.showMessageDialog(this, "Invalid ID format!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String name = txtName.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String gender = comboGender.getSelectedItem().toString();
            String dept = txtDepartment.getText().trim();
            double gpa = Double.parseDouble(txtGpa.getText().trim());

            if (name.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            if (manager.updateStudent(id, name, age, gender, dept, gpa)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                enableFields(false);
                clearFields();
                txtId.requestFocus();
            } else {
                JOptionPane.showMessageDialog(this, "Update failed. Student may not exist.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format in ID, Age, or GPA!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    private void enableFields(boolean editable) {
        fieldsEditable = editable;
        txtName.setEditable(editable);
        txtAge.setEditable(editable);
        txtDepartment.setEditable(editable);
        txtGpa.setEditable(editable);
        btnUpdate.setEnabled(editable);
        comboGender.repaint();
        comboGender.revalidate();
    }

    private void clearFields() {
        txtName.setText("");
        txtAge.setText("");
        txtDepartment.setText("");
        txtGpa.setText("");
        comboGender.setSelectedIndex(0);
    }

    private GridBagConstraints baseConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        return gbc;
    }

    private void addTitle(String text, GridBagConstraints gbc) {
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        add(title, gbc);
        gbc.gridwidth = 1;
    }

    private void addRow(String label, JComponent field, GridBagConstraints gbc) {
        gbc.gridy++; gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(label(label), gbc);
        gbc.gridx = 1; add(field, gbc);
    }

    private void addButton(String text, Color bg, GridBagConstraints gbc) {
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(50, 10, 10, 10);
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        btnUpdate = button(text, bg);
        btnUpdate.addActionListener(e -> updateStudent());
        btnUpdate.setEnabled(false);
        add(btnUpdate, gbc);
    }

    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return l;
    }

    private JTextField field() {
        JTextField f = new JTextField();
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        f.setForeground(Color.WHITE);
        f.setBackground(new Color(30, 30, 30));
        f.setCaretColor(Color.WHITE);
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 60)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return f;
    }

    private JComboBox<String> genderBox() {
        JComboBox<String> box = new JComboBox<>(new String[]{"Male", "Female"});
        box.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        box.setForeground(Color.WHITE);
        box.setBackground(new Color(30, 30, 30));
        box.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setOpaque(true);
                lbl.setBackground(isSelected ? new Color(60, 60, 60) : new Color(30, 30, 30));
                lbl.setForeground(Color.WHITE);
                lbl.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
                return lbl;
            }
        });

        box.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!fieldsEditable) {
                    e.consume();
                    SwingUtilities.invokeLater(() -> {
                        String idText = txtId.getText().trim();
                        try {
                            int id = Integer.parseInt(idText);
                            Student s = manager.getStudentById(id);
                            if (s != null) {
                                comboGender.setSelectedItem(s.getGender());
                            }
                        } catch (Exception ignored) {}
                    });
                }
            }
        });

        box.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = new JButton("▼");
                btn.setBackground(new Color(30, 30, 30));
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createEmptyBorder());
                btn.setFocusable(false);
                btn.setContentAreaFilled(false);
                return btn;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(new Color(30, 30, 30));
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });

        return box;
    }

    private JButton button(String text, Color bg) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b.setForeground(Color.WHITE);
        b.setBackground(bg);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    private void styleBackButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(50, 50, 50));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}























































































