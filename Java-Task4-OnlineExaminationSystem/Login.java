
package exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {

        setTitle("Online Examination System");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 244, 248));

        JLabel heading = new JLabel("STUDENT LOGIN");
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(110, 30, 250, 35);
        panel.add(heading);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(60, 100, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(160, 100, 200, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 150, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 150, 200, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(160, 210, 120, 35);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginStudent();
            }
        });

        add(panel);
        setVisible(true);
    }

    private void loginStudent() {

        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("student") && password.equals("1234")) {

            JOptionPane.showMessageDialog(this,
                    "Login Successful!");

            new Profile();
            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password!");
        }
    }
}
