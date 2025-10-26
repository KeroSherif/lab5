import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class ViewStudentPanel extends JPanel {
    private StudentManager manager;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public ViewStudentPanel(StudentManager manager) {
        this.manager = manager;
        initComponents();
    }

    private void initComponents() {
        setLayout(new java.awt.BorderLayout());

        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, java.awt.BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadStudents();
            }
        });
    }

    private void loadStudents() {
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
}
