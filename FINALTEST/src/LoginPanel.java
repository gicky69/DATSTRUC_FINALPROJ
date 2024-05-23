import javax.swing.*;
import java.io.*;

public class LoginPanel extends JPanel {
    UserValidation userValidation;
    Frame mainFrame;
    MenuPanel menuPanel;
    JButton resetButton, loginButton, registerButton;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField; JPasswordField passwordField;

    public LoginPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.frame.setVisible(true);
        userValidation = new UserValidation();
        menuPanel = new MenuPanel(mainFrame);

        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(860, 250, 200, 50);
        this.add(usernameLabel);
        usernameField = new JTextField("admin");
        usernameField.setBounds(860, 300, 200, 50);
        this.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(860, 350, 200, 50);
        this.add(passwordLabel);
        passwordField = new JPasswordField("admin");
        passwordField.setBounds(860, 400, 200, 50);
        this.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(860, 500, 100, 50);
        this.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(860, 600, 100, 50);
        this.add(registerButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(860, 700, 100, 50);
        this.add(resetButton);

        loginButton.addActionListener(e -> {
            // will store true/false
            boolean isLoginSuccessful = userValidation.login(usernameField.getText(), passwordField.getText());

            if (isLoginSuccessful) {

                // comment if diretso na sa game mismo
                mainFrame.frame.getContentPane().removeAll();
                menuPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(menuPanel);
                menuPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

//                // for debugging purposes only, will be removed after.
//                mainFrame.frame.getContentPane().removeAll();
//                gamePanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
//                mainFrame.frame.add(gamePanel);
//                gamePanel.requestFocusInWindow();
//                mainFrame.frame.revalidate();
//                mainFrame.frame.repaint();
                System.out.println("Login Successful");
            } else {
                System.out.println("Login failed.");
            }
        });

        registerButton.addActionListener(e -> {
            boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText());
            if (isRegisterSuccessful) {
                System.out.println("Registration Successful");
                JOptionPane.showMessageDialog(null, "Registration Successful.");
            } else {
                System.out.println("Registration failed.");
            }
        });
    }

    static class UserValidation {

        static final String DB_UserCredentials = "FINALTEST/Database/users.txt";

        public boolean login(String username, String password) {

            try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Print");
                    String[] user = line.split(",");
                    if (user[0].equals(username) && user[1].equals(password)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        public boolean register(String username, String password) {

            if (username.isEmpty() || password.isEmpty()) {
                return false;

            } else if (username.contains(",") || password.contains(",")) {
                return false;
            }
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] user = line.split(",");
                        if (user[0].equals(username)) {
                            System.out.println("Username already exists.");
                            JOptionPane.showMessageDialog(null, "Username already exists.");
                            return false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_UserCredentials, true))) {
                    writer.write(username + "," + password); //add credentials to db
                    writer.newLine(); //new line
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;

            }

        }



    }
}
