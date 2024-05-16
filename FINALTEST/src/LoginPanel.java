import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class LoginPanel extends JPanel {

    GamePanel gamePanel;
    UserValidation userValidation;
    Frame mainFrame;
    JButton resetButton, loginButton;
    JTextField usernameField, passwordField;
    Boolean isValid = true;

    public LoginPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.frame.setVisible(false);
        gamePanel = new GamePanel();
        userValidation = new UserValidation();

        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        usernameField = new JTextField("Username");
        usernameField.setBounds(860, 300, 200, 50);
        this.add(usernameField);

        passwordField = new JTextField("Password");
        passwordField.setBounds(860, 400, 200, 50);
        this.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(860, 600, 100, 50);
        this.add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(860, 500, 100, 50);
        this.add(resetButton);

        loginButton.addActionListener(e -> {
            // will store true/false
            boolean isLoginSuccessful = userValidation.login(usernameField.getText(), passwordField.getText());

            if (isLoginSuccessful) {
                mainFrame.frame.getContentPane().removeAll();
                gamePanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(gamePanel);
                gamePanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();
                System.out.println("Login Successful");
            } else {
                System.out.println("Login failed.");
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



    }
}
