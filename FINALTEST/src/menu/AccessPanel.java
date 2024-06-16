package menu;

import display.SubPanels;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class AccessPanel extends JPanel {
    UserValidation userValidation;
    Frame mainFrame;
    MenuPanel menuPanel;
    SubPanels subPanels;
    //JButton resetButton, loginButton, registerButton;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField; JPasswordField passwordField;
    JLabel loginButton, registerButton, resetButton, logInBGLabel, loginBGLabel;
    ImageIcon logInIMG, logInHighlight, loginBGImg, registerIMG, registerHighlight, resetIMG, resetHighlight, loginBGIMG;


    public AccessPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;

        mainFrame.frame.setVisible(true);
        userValidation = new UserValidation();
        menuPanel = new MenuPanel(mainFrame, subPanels);

        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(260, 640, 200, 50);
        Font userNameText = new Font("Constantia", Font.BOLD, 30);
        usernameLabel.setFont(userNameText);
        usernameLabel.setForeground(Color.WHITE);
        this.add(usernameLabel);

        usernameField = new JTextField("admin");
        usernameField.setBounds(460, 645, 400, 50);
        Font userNameFont = new Font("Cascadia Code", Font.BOLD, 30);
        usernameField.setFont(userNameFont);
        usernameField.setForeground(Color.blue);
        usernameField.setBackground(Color.white);
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        this.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(260, 765, 200, 50);
        Font passwordText = new Font("Constantia", Font.BOLD, 30);
        passwordLabel.setFont(passwordText);
        passwordLabel.setForeground(Color.WHITE);
        this.add(passwordLabel);

        passwordField = new JPasswordField("admin");
        passwordField.setBounds(460, 770, 400, 50);
        Font passwordFont = new Font("Cascadia Code", Font.BOLD, 30);
        passwordField.setFont(passwordFont);
        passwordField.setForeground(Color.blue);
        passwordField.setBackground(Color.white);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        this.add(passwordField);

        loginButton = new JLabel("Login");
        loginButton.setBounds(1300, 250, 250, 150);
        logInIMG = new ImageIcon("FINALTEST/images/Buttons/LOG IN - NOT CLICKED 1.png");
        logInHighlight = new ImageIcon("FINALTEST/images/Buttons/LOG IN - CLICKED 1.png");
        loginButton.setIcon(logInIMG);
        this.add(loginButton);


        registerButton = new JLabel("Register");
        registerButton.setBounds(1300, 450, 250, 150);
        registerIMG = new ImageIcon("FINALTEST/images/Buttons/REGISTER-NOT CLICKED 1.png");
        registerHighlight = new ImageIcon("FINALTEST/images/Buttons/REGISTER - CLICKED 1.png");
        registerButton.setIcon(registerIMG);
        this.add(registerButton);

        resetButton = new JLabel("Reset");
        resetButton.setBounds(1300, 650, 250, 150);
        resetIMG = new ImageIcon("FINALTEST/images/Buttons/RESET - NOT CLICKED 1.png");
        resetHighlight = new ImageIcon("FINALTEST/images/Buttons/RESET - CLICKED 1.png");
        resetButton.setIcon(resetIMG);
        this.add(resetButton);

        logInBGLabel = new JLabel();
        logInBGLabel.setBounds(0,0, 1920,1080);
        loginBGIMG = new ImageIcon("FINALTEST/images/MainIBG/log in background .png");
        logInBGLabel.setIcon(loginBGIMG);
        this.add(logInBGLabel);



        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setIcon(logInHighlight);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setIcon(logInIMG);

            }
        });

        /*loginButton.addActionListener(e -> {
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
        });*/

        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText());
                if (isRegisterSuccessful) {
                    System.out.println("Registration Successful");
                    JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setIcon(registerHighlight);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setIcon(registerIMG);

            }
        });

        /*registerButton.addActionListener(e -> {
            boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText());
            if (isRegisterSuccessful) {
                System.out.println("Registration Successful");
                JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });*/
        resetButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setIcon(resetHighlight);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetButton.setIcon(resetIMG);
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
