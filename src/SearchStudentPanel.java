/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author monic
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SearchStudentPanel extends JPanel {
    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel model;
    private StudentManager manager;

    public SearchStudentPanel(StudentManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        txtSearch = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        topPanel.add(new JLabel("Search by Name or ID:"));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnSearch.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        model.setRowCount(0);
        String query = txtSearch.getText().trim();

        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.");
            return;
        }

        try {
            
            int id = Integer.parseInt(query);
            Student s = manager.getStudentById(id);
            if (s != null) {
                model.addRow(new Object[]{s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()});
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that ID.");
            }
        } catch (NumberFormatException e) {
            List<Student> list = manager.searchStudentsByName(query);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students found.");
            } else {
                for (Student s : list) {
                    model.addRow(new Object[]{s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()});
                }
            }
        }
    }
}

