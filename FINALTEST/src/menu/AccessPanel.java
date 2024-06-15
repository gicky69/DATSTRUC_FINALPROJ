package menu;

import display.SubPanels;
import tile.TileManager;

import javax.swing.*;
import java.io.*;

public class AccessPanel extends JPanel {
    UserValidation userValidation;
    Frame mainFrame;
    MenuPanel menuPanel;
    SubPanels subPanels;
    JButton resetButton, loginButton, registerButton;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField; JPasswordField passwordField;

    public AccessPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;

        mainFrame.frame.setVisible(true);
        userValidation = new UserValidation();
        menuPanel = new MenuPanel(mainFrame, subPanels);

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
                mainFrame.update();
                System.out.println("Login Successful");
            } else {
                System.out.println("Login failed.");
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText());
            if (isRegisterSuccessful) {
                System.out.println("Registration Successful");
                JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    static class UserValidation {

        static final String DB_UserCredentials = "FINALTEST/Database/users.txt";

        public boolean login(String username, String password) {

            try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                String line;
                while ((line = reader.readLine()) != null) {
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
                JOptionPane.showMessageDialog(null, "Field is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;

            } else if (username.contains(",")) {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            else {
                try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] user = line.split(",");
                        if (user[0].equals(username)) {
                            JOptionPane.showMessageDialog(null, "Username already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
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
