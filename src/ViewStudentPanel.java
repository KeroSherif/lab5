
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class ViewStudentPanel extends JPanel {
    private StudentManager manager;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    private static final Color DARK_BG = Color.BLACK;
    private static final Color GRID_COLOR = new Color(60, 60, 60);

    public ViewStudentPanel(StudentManager manager) {
        this.manager = manager;
        initComponents();
        applyDarkTheme();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

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
        add(backBtn, BorderLayout.NORTH);

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
        add(scrollPane, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadStudents();
            }
        });
    }

    private void applyDarkTheme() {
        this.setBackground(DARK_BG);
        studentTable.setBackground(Color.BLACK);
        studentTable.setForeground(Color.WHITE);
        studentTable.setGridColor(GRID_COLOR);
        studentTable.setSelectionBackground(new Color(60, 60, 80));
        studentTable.setSelectionForeground(Color.WHITE);
        studentTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        studentTable.setRowHeight(25);
        studentTable.setShowGrid(true);
        studentTable.setIntercellSpacing(new Dimension(1, 1));

        JTableHeader header = studentTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
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

        JScrollPane scrollPane = (JScrollPane) getComponent(1);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(GRID_COLOR));
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
