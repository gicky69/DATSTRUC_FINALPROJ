package menu;

import display.SubPanels;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.UUID;

public class AccessPanel extends JPanel {
    UserValidation userValidation;
    Frame mainFrame;
    MenuPanel menuPanel;
    SubPanels subPanels;
    JLabel loginButton, registerButton, resetButton, logInBGLabel, loginBGLabel;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField; JPasswordField passwordField;
    ImageIcon logInIMG, logInHighlight, loginBGImg, registerIMG, registerHighlight, resetIMG, resetHighlight, loginBGIMG;
    public String playerInUse;

    public AccessPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        mainFrame.frame.setVisible(true);
        userValidation = new UserValidation();
        menuPanel = new MenuPanel(mainFrame, subPanels, this);

        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        int buttonWidth = (int) (screenWidth * 0.14);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds((((int) screenWidth/2)-500)-(buttonWidth/2),(int) screenHeight-400, 200, 50);
        Font userNameText = new Font("Constantia", Font.BOLD, 30);
        usernameLabel.setFont(userNameText);
        usernameLabel.setForeground(Color.WHITE);
        this.add(usernameLabel);

        usernameField = new JTextField("admin");
        usernameField.setBounds((((int) screenWidth/2)-300)-(buttonWidth/2),(int) screenHeight-400, 400, 50);
        Font userNameFont = new Font("Cascadia Code", Font.BOLD, 30);
        usernameField.setFont(userNameFont);
        usernameField.setForeground(Color.blue);
        usernameField.setBackground(Color.white);
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        this.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds((((int) screenWidth/2)-500)-(buttonWidth/2),(int) screenHeight-270, 200, 50);
        Font passwordText = new Font("Constantia", Font.BOLD, 30);
        passwordLabel.setFont(passwordText);
        passwordLabel.setForeground(Color.WHITE);
        this.add(passwordLabel);

        passwordField = new JPasswordField("admin");
        passwordField.setBounds((((int) screenWidth/2)-300)-(buttonWidth/2),(int) screenHeight-270, 400, 50);
        Font passwordFont = new Font("Cascadia Code", Font.BOLD, 30);
        passwordField.setFont(passwordFont);
        passwordField.setForeground(Color.blue);
        passwordField.setBackground(Color.white);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        this.add(passwordField);

        loginButton = new JLabel("Login");
        loginButton.setBounds((((int) screenWidth/2)+550)-(buttonWidth/2), (int) screenHeight-750, 250, 150);
        logInIMG = new ImageIcon("FINALTEST/images/Buttons/loginNotClicked-AccessPanel.png");
        logInHighlight = new ImageIcon("FINALTEST/images/Buttons/loginClicked-AccessPanel.png");
        loginButton.setIcon(logInIMG);
        this.add(loginButton);

        registerButton = new JLabel("Register");
        registerButton.setBounds((((int) screenWidth/2)+550)-(buttonWidth/2), (int) screenHeight-550, 250, 150);
        registerIMG = new ImageIcon("FINALTEST/images/Buttons/registerNotClicked-AccessPanel.png");
        registerHighlight = new ImageIcon("FINALTEST/images/Buttons/registerClicked-AccessPanel.png");
        registerButton.setIcon(registerIMG);
        this.add(registerButton);

        resetButton = new JLabel("Reset");
        resetButton.setBounds((((int) screenWidth/2)+550)-(buttonWidth/2), (int) screenHeight-350, 250, 150);
        resetIMG = new ImageIcon("FINALTEST/images/Buttons/resetNotClicked-AccessPanel.png");
        resetHighlight = new ImageIcon("FINALTEST/images/Buttons/resetClicked-AccessPanel.png");
        resetButton.setIcon(resetIMG);
        this.add(resetButton);

        logInBGLabel = new JLabel();
        logInBGLabel.setBounds(0,0, (int) screenWidth,(int)screenHeight);
        loginBGIMG = new ImageIcon("FINALTEST/images/MainIBG/accessBG-AccessPanel.png");
        Image loginBGImage = loginBGIMG.getImage();
        Image scaledLoginBGImage = loginBGImage.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_SMOOTH);
        ImageIcon finalloginBGIMG = new ImageIcon(scaledLoginBGImage);
        logInBGLabel.setIcon(finalloginBGIMG);
        this.add(logInBGLabel);


        loginButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // will store true/false
                playerInUse = userValidation.login(usernameField.getText(), passwordField.getText());

                if (playerInUse != null) {
                    System.out.println("FROM ACCESS: " + playerInUse);
                    mainFrame.frame.getContentPane().removeAll();
                    menuPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                    mainFrame.frame.add(menuPanel);
                    menuPanel.requestFocusInWindow();
                    mainFrame.update();
                    System.out.println("User Access Successful");
                } else {
                    System.out.println("User Access Failed.");
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

        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText());
                if (isRegisterSuccessful) {
                    System.out.println("Registration Successful");
                    JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    System.out.println("Registration Failed");
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
            else {
                System.out.println("Registration Failed");
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
        static final String DB_UserData = "FINALTEST/Database/playerdata.txt";

        public String login(String username, String password) {

            try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] user = line.split(":");
                    if (user[1].equals(username) && user[2].equals(password)) {
                        return user[0];
                    } else{
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        public boolean register(String username, String password) {

            UUID userID = UUID.randomUUID();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;

            } else if (username.contains(",") || username.contains(":")) {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            else {
//                try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        String[] user = line.split(":");
//                        if (user[1].equals(username)) {
//                            JOptionPane.showMessageDialog(null, "Username already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
//                            return false;
//                        }
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_UserCredentials, true))) {
                    writer.write(userID + ":" + username + ":" + password); //add credentials to db
                    writer.newLine();

                    //add player data to txt
                    try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(DB_UserData, true))) {
                        writer2.write(userID + ":" + "1,1,1"); //add player data to db (not finished)
                        writer2.newLine();
                        writer2.flush();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("NOT WORKING");
                }

                return false;
            }

        }



    }
}
