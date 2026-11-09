

package exam;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {

    private JTextField nameField;
    private JPasswordField passwordField;

    public static String displayName = "Student";
    public static String updatedPassword = "1234";

    public Profile() {

        setTitle("Student Profile");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 244, 248));

        JLabel heading = new JLabel("UPDATE PROFILE");
        heading.setFont(new Font("Arial", Font.BOLD, 23));
        heading.setBounds(105, 30, 250, 35);
        panel.add(heading);

        JLabel nameLabel = new JLabel("Display Name:");
        nameLabel.setBounds(50, 100, 110, 25);
        panel.add(nameLabel);

        nameField = new JTextField(displayName);
        nameField.setBounds(165, 100, 200, 30);
        panel.add(nameField);

        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setBounds(50, 155, 110, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(updatedPassword);
        passwordField.setBounds(165, 155, 200, 30);
        panel.add(passwordField);

        JButton saveButton = new JButton("Save Profile");
        saveButton.setBounds(145, 210, 140, 35);
        panel.add(saveButton);

        JButton startButton = new JButton("Start Exam");
        startButton.setBounds(145, 270, 140, 35);
        panel.add(startButton);

        saveButton.addActionListener(e -> updateProfile());

        startButton.addActionListener(e -> {

            if (updateProfile()) {
                new Exam();
                dispose();
            }

        });

        add(panel);
        setVisible(true);
    }

    private boolean updateProfile() {

        String name = nameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (name.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields!");

            return false;
        }

        displayName = name;
        updatedPassword = password;

        JOptionPane.showMessageDialog(this,
                "Profile updated successfully!");

        return true;
    }
}
