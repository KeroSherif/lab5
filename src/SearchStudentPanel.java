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
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
import javax.swing.table.JTableHeader;

public class SearchStudentPanel extends JPanel {
    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel model;
    private StudentManager manager;

    public SearchStudentPanel(StudentManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout(10, 15));
        setBackground(new Color(18, 18, 18)); // #121212

        // Panel for Back to Home button (في الأعلى)
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        topBar.setBackground(new Color(26, 26, 26)); // #1A1A1A
        topBar.setPreferredSize(new Dimension(0, 40)); // ارتفاع ثابت للشريط

        JButton backBtn = new JButton("⬅ Back to Home");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(26, 26, 26));
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setOpaque(true);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame instanceof MainApp) {
                ((MainApp) frame).showPanel("Home");
            }
        });
        topBar.add(backBtn);

        add(topBar, BorderLayout.NORTH); // شريط الرجوع في الأعلى

        // Panel للبحث (تحت الشريط)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setBackground(new Color(18, 18, 18));
        JLabel searchLabel = new JLabel("Search by Name or ID:");
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchPanel.add(searchLabel);

        txtSearch = createStyledTextField();
        searchPanel.add(txtSearch);

        JButton btnSearch = createStyledButton("🔍 Search", new Color(156, 39, 176)); // Purple
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchPanel.add(btnSearch);

        add(searchPanel, BorderLayout.CENTER); // بوكس البحث في المنتصف

        // إعداد الجدول (تحت بوكس البحث)
        model = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(18, 18, 18));
        scrollPane.getViewport().setBackground(new Color(30, 30, 30));
        add(scrollPane, BorderLayout.SOUTH); // الجدول في الأسفل

        // ربط زرار البحث
        btnSearch.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        model.setRowCount(0);
        String query = txtSearch.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Empty Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(query);
            Student s = manager.getStudentById(id);
            if (s != null) {
                model.addRow(new Object[]{s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()});
            } else {
                JOptionPane.showMessageDialog(this, "No student found with that ID.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            List<Student> list = manager.searchStudentsByName(query);
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students found matching your query.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Student s : list) {
                    model.addRow(new Object[]{s.getId(), s.getFullName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa()});
                }
            }
        }
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setForeground(Color.WHITE);
        field.setBackground(new Color(30, 30, 30)); // #1E1E1E
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 51, 51), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(120, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(30, 30, 30)); // #1E1E1E

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(26, 26, 26)); // #1A1A1A
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setOpaque(true);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(26, 26, 26));
                c.setForeground(Color.WHITE);
                ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        });

        table.setSelectionBackground(new Color(50, 50, 70)); // لون التحديد
        table.setGridColor(new Color(51, 51, 51)); // لون خطوط الجدول
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
    }
}





















