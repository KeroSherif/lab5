
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DeleteStudent extends javax.swing.JPanel {
    private StudentManager manager;
    private DefaultTableModel tableModel;

    private static final Color DARK_BG = Color.BLACK;
    private static final Color GRID_COLOR = new Color(60, 60, 60);
    private static final Color ACCENT_COLOR = new Color(220, 38, 38);
    private static final Color ACCENT_HOVER = new Color(239, 68, 68);

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jButton1;

    public DeleteStudent(StudentManager manager) {
        this.manager = manager; // ✅ mainApp مش مطلوب
        initComponents();
        applyDarkTheme();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}
        ) {
            Class<?>[] types = new Class<?>[]{Integer.class, String.class, Integer.class, String.class, String.class, Double.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        jTable1.setModel(tableModel);
        jScrollPane1.setViewportView(jTable1);
        jButton1.setText("Delete");
        jButton1.addActionListener(evt -> deleteStudent());

        // زرار الرجوع
        JButton backBtn = new JButton("⬅ Back to Home");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(26, 26, 26));
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setOpaque(true);
        backBtn.setPreferredSize(new Dimension(120, 30));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame instanceof MainApp) {
                ((MainApp) frame).showPanel("Home");
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                refreshTable();
            }
        });
    }

    private void applyDarkTheme() {
        this.setBackground(DARK_BG);
        jTable1.setBackground(Color.BLACK);
        jTable1.setForeground(Color.WHITE);
        jTable1.setGridColor(GRID_COLOR);
        jTable1.setSelectionBackground(new Color(60, 60, 80));
        jTable1.setSelectionForeground(Color.WHITE);
        jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        jTable1.setRowHeight(25);
        jTable1.setShowGrid(true);
        jTable1.setIntercellSpacing(new Dimension(1, 1));

        JTableHeader header = jTable1.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Segoe UI", Font.BOLD, 12));
                label.setOpaque(true);
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, GRID_COLOR));
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        });
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        header.setReorderingAllowed(false);

        jScrollPane1.setBackground(Color.BLACK);
        jScrollPane1.getViewport().setBackground(Color.BLACK);
        jScrollPane1.setBorder(BorderFactory.createLineBorder(GRID_COLOR));

        jButton1.setBackground(ACCENT_COLOR);
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1.setBackground(ACCENT_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1.setBackground(ACCENT_COLOR);
            }
        });
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> students = manager.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                s.getId(),
                s.getFullName(),
                s.getAge(),
                s.getGender(),
                s.getDepartment(),
                s.getGpa()
            });
        }
    }

    private void deleteStudent() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        int choice = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete " + name + " (ID: " + id + ")?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (manager.deleteStudent(id)) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Student could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
       }
    }
}
