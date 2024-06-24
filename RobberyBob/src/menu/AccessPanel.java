package menu;

import display.SubPanels;
import org.w3c.dom.css.RGBColor;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.UUID;

public class AccessPanel extends JPanel {
    UserValidation userValidation;
    Frame mainFrame;
    MenuPanel menuPanel;
    SubPanels subPanels;
    JLabel loginButton, registerButton, resetButton, loginBG;
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

        int textFieldWidth = 630;
        int textFieldHeight = 55;
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds((((int) screenWidth/2))-(textFieldWidth/2),(int) screenHeight-800, textFieldWidth, textFieldHeight);
        Font userNameText = new Font("DePixel", Font.BOLD, 25);
        usernameLabel.setFont(userNameText);
        usernameLabel.setForeground(Color.WHITE);
        this.add(usernameLabel);

        usernameField = new JTextField("admin");
        usernameField.setBounds((((int) screenWidth/2))-(textFieldWidth/2),(int) screenHeight-730, textFieldWidth, textFieldHeight);
        usernameField.setFont(new Font("DePixel", Font.PLAIN, 22));
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        this.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds((((int) screenWidth/2))-(textFieldWidth/2),(int) screenHeight-620, textFieldWidth, textFieldHeight);
        Font passwordText = new Font("DePixel", Font.BOLD, 25);
        passwordLabel.setFont(passwordText);
        passwordLabel.setForeground(Color.WHITE);
        this.add(passwordLabel);

        passwordField = new JPasswordField("admin");
        passwordField.setBounds((((int) screenWidth/2))-(textFieldWidth/2),(int) screenHeight-560, textFieldWidth, textFieldHeight);
        passwordField.setFont(new Font("DePixel", Font.PLAIN, 22));
        passwordField.setHorizontalAlignment(JTextField.LEFT);
        this.add(passwordField);

        double buttonLabelWidth = screenWidth/7.5;
        double buttonLabelHeight = screenHeight/7.5;
        double y = screenHeight/1.9;

        loginButton = new JLabel();
        Image loginButtonNC = new ImageIcon("RobberyBob/resources/images/AccessPanel/loginNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image loginButtonC = new ImageIcon("RobberyBob/resources/images/AccessPanel/loginClicked.png"
        )   .getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        loginButton.setIcon(new ImageIcon(loginButtonNC));
        loginButton.setBounds((int) ((screenWidth-buttonLabelWidth)/2)-150, (int) y, (int) buttonLabelWidth, (int)buttonLabelHeight);

        resetButton = new JLabel();
        Image resetButtonNC = new ImageIcon("RobberyBob/resources/images/AccessPanel/resetNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image resetButtonC = new ImageIcon("RobberyBob/resources/images/AccessPanel/resetClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        resetButton.setIcon(new ImageIcon(resetButtonNC));
        resetButton.setBounds((int) ((screenWidth-buttonLabelWidth)/2)+150, (int) y, (int) buttonLabelWidth, (int)buttonLabelHeight);

        registerButton = new JLabel();
        Image registerButtonNC = new ImageIcon("RobberyBob/resources/images/AccessPanel/registerNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image registerButtonC = new ImageIcon("RobberyBob/resources/images/AccessPanel/registerClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        registerButton.setIcon(new ImageIcon(registerButtonNC));
        registerButton.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) (screenHeight/2) + 150, (int) buttonLabelWidth, (int)buttonLabelHeight);

        loginBG = new JLabel();
        loginBGImg = new ImageIcon("RobberyBob/resources/images/AccessPanel/menuPanelBG.png");
        Image image = loginBGImg.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        loginBG.setIcon(scaledImageIcon);
        loginBG.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
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
                loginButton.setIcon(new ImageIcon(loginButtonNC));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setIcon(new ImageIcon(loginButtonC));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setIcon(new ImageIcon(loginButtonNC));

            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText());
                if (isRegisterSuccessful) {
                    System.out.println("Registration Successful");
                    JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    System.out.println("Registration Failed");
                }

                registerButton.setIcon(new ImageIcon(registerButtonNC));

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setIcon(new ImageIcon(registerButtonC));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setIcon(new ImageIcon(registerButtonNC));

            }
        });


        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                resetButton.setIcon(new ImageIcon(resetButtonNC));
                usernameField.setText("");
                passwordField.setText("");

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setIcon(new ImageIcon(resetButtonC));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetButton.setIcon(new ImageIcon(resetButtonNC));

            }
        });

        this.add(loginButton);
        this.add(resetButton);
        this.add(registerButton);
        this.add(loginBG);

    }


    static class UserValidation {

        static final String DB_UserCredentials = "RobberyBob/resources/Database/users.txt";
        static final String DB_UserData = "RobberyBob/resources/Database/playerdata.txt";

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
//
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
