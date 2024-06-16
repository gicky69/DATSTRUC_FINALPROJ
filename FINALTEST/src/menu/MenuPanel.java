package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel {

    Frame mainFrame;
    RoundPanel roundPanel;
    ShopPanel shopPanel;
    SettingsPanel settingsPanel;
    HTPPanel htpPanel;
    //JButton playButton, shopButton, settingsButton, tutorialButton, exitButton;
    JLabel playLabel,  shopLabel, settingsLabel, htpLabel, exitLabel, menuBGLabel;
    ImageIcon playImagenc, playImagec, shopImagenc, shopImagec, settingImagenc, settingImagec, htpImagenc, htpImagec, exitImagenc, exitImagec, menuBGImg;
    SubPanels subPanels;

    public MenuPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        playLabel = new JLabel("Play");
        playLabel.setBounds(840,770,250,150);
        playImagec = new ImageIcon("FINALTEST/images/buttons/PLAY -CLICKED.png");
        playImagenc = new ImageIcon("FINALTEST/images/buttons/PLAY -NOT CLICKED.png");
        playLabel.setIcon(playImagenc);
        playLabel.setVisible(true);
        this.add(playLabel);

        shopLabel = new JLabel("Shop");
        shopLabel.setBounds(1650,100,250,150);
        shopImagenc = new ImageIcon("FINALTEST/images/buttons/SHOP_-_NOT_CLICKED.png");
        shopImagec = new ImageIcon("FINALTEST/images/buttons/SHOP_-_CLICKED.png");
        shopLabel.setIcon(shopImagenc);
        this.add(shopLabel);

        settingsLabel = new JLabel("Settings");
        settingsLabel.setBounds(1650,0,250,150);
        settingImagenc = new ImageIcon("FINALTEST/images/buttons/SETTINGS - NOT CLICKED.png");
        settingImagec = new ImageIcon("FINALTEST/images/buttons/SETTINGS - CLICKED.png");
        settingsLabel.setIcon(settingImagenc);
        this.add(settingsLabel);

        htpLabel = new JLabel("How to Play");
        htpLabel.setBounds(255,750,250,150);
        htpImagenc = new ImageIcon("FINALTEST/images/buttons/HOW_TO_PLAY_-_NOT_CLICKED.png");
        htpImagec = new ImageIcon("FINALTEST/images/buttons/HOW_TO_PLAY_-_CLICKED.png");
        htpLabel.setIcon(htpImagenc);
        htpLabel.setVisible(true);
        this.add(htpLabel);

        exitLabel = new JLabel("Exit");
        exitLabel.setBounds(1430,750,250,150);
        exitImagenc = new ImageIcon("FINALTEST/images/buttons/EXIT_-_NOT_CLICKED.png");
        exitImagec = new ImageIcon("FINALTEST/images/buttons/EXIT_-_CLICKED.png");
        exitLabel.setIcon(exitImagenc);
        this.add(exitLabel);

        menuBGLabel = new JLabel();
        menuBGLabel.setBounds(0,0, 1920,1080);
        menuBGImg = new ImageIcon("FINALTEST/images/MainIBG/main menu background.png");
        menuBGLabel.setIcon(menuBGImg);
        this.add(menuBGLabel);

        playLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();

                // view round panel
                roundPanel = new RoundPanel(mainFrame, subPanels);
                roundPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(roundPanel);
                mainFrame.frame.setTitle("Choose Difficulty & Round");
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                playLabel.removeAll();
                playLabel.setIcon(playImagec);
                playLabel.revalidate();
                playLabel.repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                playLabel.removeAll();
                playLabel.setIcon(playImagenc);
                playLabel.revalidate();
                playLabel.repaint();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                playLabel.removeAll();
                playLabel.setIcon(playImagec);
                playLabel.revalidate();
                playLabel.repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                playLabel.setIcon(playImagenc);

            }
        });

        /*playButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();

            // view round panel
            roundPanel = new RoundPanel(mainFrame, subPanels);
            roundPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            mainFrame.frame.add(roundPanel);
            mainFrame.frame.setTitle("Choose Difficulty & Round");
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();

        });*/

        shopLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.setTitle("Shop");
                shopPanel = new ShopPanel(mainFrame);
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

        /*shopButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.setTitle("Shop");
            shopPanel = new ShopPanel(mainFrame);
            mainFrame.frame.add(shopPanel);
            shopPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            shopPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });*/

        settingsLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.setTitle("Settings");
                mainFrame.frame.getContentPane().removeAll();
                settingsPanel = new SettingsPanel(mainFrame);
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
        });

        /*settingsButton.addActionListener(e -> {
            mainFrame.frame.setTitle("Settings");
            mainFrame.frame.getContentPane().removeAll();
            settingsPanel = new SettingsPanel(mainFrame);
            mainFrame.frame.add(settingsPanel);
            settingsPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            settingsPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });*/

        htpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.setTitle("Tutorial");
                htpPanel = new HTPPanel(mainFrame);
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

        /*tutorialButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.setTitle("Tutorial");
            htpPanel = new HTPPanel(mainFrame);
            mainFrame.frame.add(htpPanel);
            htpPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            htpPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });*/

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
        /*exitButton.addActionListener(e -> {

        });*/

    }
}

