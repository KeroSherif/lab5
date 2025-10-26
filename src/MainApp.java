import javax.swing.*;

public class MainApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StudentManager manager;

    public MainApp() {
        manager = new StudentManager();

        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new HomePanel(this), "Home");
        mainPanel.add(new AddStudentPanel(this, manager), "AddStudent");
        mainPanel.add(new ViewStudentPanel(manager), "View");
        mainPanel.add(new UpdateStudentPanel(this, manager), "UpdateStudent");
        mainPanel.add(new DeleteStudent(this, manager), "DeleteStudent"); 
        mainPanel.add(new SearchStudentPanel(this, manager), "SearchStudent");

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
