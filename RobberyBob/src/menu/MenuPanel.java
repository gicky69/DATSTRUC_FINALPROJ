package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel {

    Frame mainFrame;
    RoundPanel roundPanel;
    AccessPanel accessPanel;
    ShopPanel shopPanel;
    SettingsPanel settingsPanel;
    HTPPanel htpPanel;
    //JButton playButton, shopButton, settingsButton, tutorialButton, exitButton;
    JLabel playLabel,  shopLabel, settingsLabel, htpLabel, exitLabel, menuBGLabel;
    ImageIcon playImagenc, playImagec, shopImagenc, shopImagec, settingImagenc, settingImagec, htpImagenc, htpImagec, exitImagenc, exitImagec, menuBGImg;
    SubPanels subPanels;
    String playerInUse;

    public MenuPanel(Frame mainFrame, SubPanels subPanels, AccessPanel accessPanel) {
        // screensize of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.accessPanel = accessPanel;
        this.setSize((int) screenWidth, (int) screenHeight);
        this.setLayout(null);
        int buttonWidth = (int) (screenWidth * 0.14);

        playLabel = new JLabel();
        playLabel.setBounds((int) (screenWidth/2)-(buttonWidth/2),(int) (screenHeight-250),250,150);
        playImagec = new ImageIcon("RobberyBob/resources/images/buttons/playClicked-MenuPanel.png");
        Image scaledPlayImagec = playImagec.getImage();
        Image finalScaledPlayImagec = scaledPlayImagec.getScaledInstance(buttonWidth, -1, Image.SCALE_REPLICATE);

        playImagenc = new ImageIcon("RobberyBob/resources/images/buttons/playNotClicked-MenuPanel.png");
        Image scaledPlayImagenc = playImagenc.getImage();
        Image finalScaledPlayImagenc = scaledPlayImagenc.getScaledInstance(buttonWidth, -1, Image.SCALE_REPLICATE);
        playLabel.setIcon(new ImageIcon(finalScaledPlayImagenc));

        playLabel.setVisible(true);
        this.add(playLabel);

        htpLabel = new JLabel("How to Play");
        htpLabel.setBounds((((int) screenWidth/2)-500)-(buttonWidth/2),(int) screenHeight-300,250,150);
        htpImagenc = new ImageIcon("RobberyBob/resources/images/buttons/rulesNotClicked-MenuPanel.png");
        htpImagec = new ImageIcon("RobberyBob/resources/images/buttons/rulesClicked-MenuPanel.png");
        htpLabel.setIcon(htpImagenc);
        htpLabel.setVisible(true);
        this.add(htpLabel);

        exitLabel = new JLabel("Exit");
        exitLabel.setBounds((((int) screenWidth/2)+500)-(buttonWidth/2),(int) screenHeight-300,250,150);
        exitImagenc = new ImageIcon("RobberyBob/resources/images/buttons/exitNotClicked-MenuPanel.png");
        exitImagec = new ImageIcon("RobberyBob/resources/images/buttons/exitClicked-MenuPanel.png");
        exitLabel.setIcon(exitImagenc);
        this.add(exitLabel);

        menuBGLabel = new JLabel();
        menuBGImg = new ImageIcon("RobberyBob/resources/images/MainIBG/mainMenuBG-MenuPanel.png");
        Image image = menuBGImg.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        menuBGLabel.setIcon(scaledImageIcon);
        menuBGLabel.setBounds(0, 0, (int) screenWidth, (int) screenHeight);
        this.add(menuBGLabel);


        playLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playLabel.setIcon(new ImageIcon(finalScaledPlayImagec));
                mainFrame.frame.getContentPane().removeAll();

                // view round panel
                roundPanel = new RoundPanel(mainFrame, subPanels, accessPanel);
                roundPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(roundPanel);
                mainFrame.frame.setTitle("Choose Difficulty & Round");
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                playLabel.setIcon(new ImageIcon(finalScaledPlayImagec));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playLabel.setIcon(new ImageIcon(finalScaledPlayImagenc));

            }
        });

        /*shopLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.setTitle("Shop");
                shopPanel = new ShopPanel(mainFrame, accessPanel);
                mainFrame.frame.add(shopPanel);
                shopPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                shopPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                shopLabel.setIcon(shopImagec);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                shopLabel.setIcon(shopImagenc);

            }
        });

        settingsLabel.addMouseListener(new MouseListener() {
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
                settingsLabel.setIcon(settingImagec);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsLabel.setIcon(settingImagenc);

            }
        });

        settingsLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.setTitle("Settings");
                mainFrame.frame.getContentPane().removeAll();
                settingsPanel = new SettingsPanel(mainFrame, accessPanel);
                mainFrame.frame.add(settingsPanel);
                settingsPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                settingsPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                settingsLabel.setIcon(settingImagec);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                settingsLabel.setIcon(settingImagenc);

            }
        });*/

        htpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.setTitle("Tutorial");
                htpPanel = new HTPPanel(mainFrame, accessPanel);
                mainFrame.frame.add(htpPanel);
                htpPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                htpPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                htpLabel.setIcon(htpImagec);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                htpLabel.setIcon(htpImagenc);

            }
        });

        exitLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitLabel.setIcon(exitImagec);


            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitLabel.setIcon(exitImagenc);

            }
        });

    }
}
