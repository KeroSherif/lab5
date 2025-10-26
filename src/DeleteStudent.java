import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DeleteStudent extends javax.swing.JPanel {
    private StudentManager manager;
    private MainApp mainApp;
    private DefaultTableModel tableModel;

    public DeleteStudent(StudentManager manager) {
        this.mainApp = mainApp;
        this.manager = manager;
        initComponents();
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
            Class<?>[] types = new Class<?>[]{
                Integer.class, String.class, Integer.class, String.class, String.class, Double.class
            };
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                refreshTable();
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

    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
