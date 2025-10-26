import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private MainApp mainApp;

    public HomePanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to the Student Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(6, 1, 10, 10)); 

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton updateBtn = new JButton("Update Student");
        JButton searchBtn = new JButton("Search Student"); 
        JButton deleteBtn = new JButton("Delete Student");
        JButton logoutBtn = new JButton("Logout");

        addBtn.addActionListener(e -> mainApp.showPanel("AddStudent"));
        viewBtn.addActionListener(e -> mainApp.showPanel("View"));
        updateBtn.addActionListener(e -> mainApp.showPanel("UpdateStudent"));
        searchBtn.addActionListener(e -> mainApp.showPanel("SearchStudent"));
        deleteBtn.addActionListener(e -> mainApp.showPanel("DeleteStudent")); 
        logoutBtn.addActionListener(e -> mainApp.showPanel("Login"));

        btnPanel.add(addBtn);
        btnPanel.add(viewBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(searchBtn); 
        btnPanel.add(deleteBtn);
        btnPanel.add(logoutBtn);

        add(btnPanel, BorderLayout.CENTER);
    }
}
