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
import java.awt.event.ActionEvent;

public class AddStudentPanel extends JPanel {
    private JTextField txtName, txtAge, txtDepartment, txtGpa;
    private JComboBox<String> comboGender;
    private final StudentManager manager;

    public AddStudentPanel(StudentManager manager) {
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

        addTitle("➕ Add New Student", gbc);
        addRow("Full Name:", txtName = field(), gbc);
        addRow("Age:", txtAge = field(), gbc);
        addRow("Gender:", comboGender = genderBox(), gbc);
        addRow("Department:", txtDepartment = field(), gbc);
        addRow("GPA:", txtGpa = field(), gbc);
        addButton("✅ Add Student", new Color(0, 150, 136), gbc);
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
        JButton btn = button(text, bg);
        btn.addActionListener(e -> addStudent());
        add(btn, gbc);
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
                lbl.setBackground(isSelected ? new Color(60, 60, 60) : new Color(30, 30, 30));
                lbl.setForeground(Color.WHITE);
                lbl.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
                lbl.setOpaque(true);
                return lbl;
            }
        });
        box.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = super.createArrowButton();
                btn.setBackground(new Color(30, 30, 30));
                btn.setBorder(BorderFactory.createEmptyBorder());
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

    private void addStudent() {
        try {
            String name = txtName.getText().trim();
            int age = Integer.parseInt(txtAge.getText().trim());
            String gender = comboGender.getSelectedItem().toString();
            String dept = txtDepartment.getText().trim();
            double gpa = Double.parseDouble(txtGpa.getText().trim());
            if (name.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }
            manager.addStudent(name, age, gender, dept, gpa);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            txtName.setText(""); txtAge.setText("");
            txtDepartment.setText(""); txtGpa.setText("");
            comboGender.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
}



















































































