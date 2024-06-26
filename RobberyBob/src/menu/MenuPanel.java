package menu;

import display.SubPanels;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {
    SoundManager soundManager;
    Frame mainFrame;
    RoundPanel roundPanel;
    AccessPanel accessPanel;
    HTPPanel htpPanel;
    JLabel playLabel, menuTitleLabel,  shopLabel, settingsLabel, rulesLabel, exitLabel, menuBGLabel;
    ImageIcon menuBGImg;
    SubPanels subPanels;

    public MenuPanel(Frame mainFrame, SubPanels subPanels, AccessPanel accessPanel, SoundManager soundManager) {
        // screen size of user
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.accessPanel = accessPanel;
        this.setSize((int) screenWidth, (int) screenHeight);
        this.setLayout(null);

        // buttons' width and height
        double buttonLabelWidth = screenWidth/5;
        double buttonLabelHeight = screenHeight/11.5;

        playLabel = new JLabel();
        Image playImageNC = new ImageIcon("RobberyBob/resources/images/MenuPanel/playNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image playImageC = new ImageIcon("RobberyBob/resources/images/MenuPanel/playClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        playLabel.setIcon(new ImageIcon(playImageNC));
        playLabel.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) screenHeight/2, (int) buttonLabelWidth, (int)buttonLabelHeight);
        playLabel.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        playLabel.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));

        rulesLabel = new JLabel();
        Image rulesImageNC = new ImageIcon("RobberyBob/resources/images/MenuPanel/rulesNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image rulesImageC = new ImageIcon("RobberyBob/resources/images/MenuPanel/rulesClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        rulesLabel.setIcon(new ImageIcon(rulesImageNC));
        rulesLabel.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) (screenHeight/2) + 110, (int) buttonLabelWidth, (int)buttonLabelHeight);
        rulesLabel.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        rulesLabel.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));

        exitLabel = new JLabel();
        Image exitImageNC = new ImageIcon("RobberyBob/resources/images/MenuPanel/exitNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image exitImageC = new ImageIcon("RobberyBob/resources/images/MenuPanel/exitClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        exitLabel.setIcon(new ImageIcon(exitImageNC));
        exitLabel.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) (screenHeight/2) + 220, (int) buttonLabelWidth, (int)buttonLabelHeight);
        exitLabel.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        exitLabel.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));


        menuTitleLabel = new JLabel();
        double menuTitleWidth = screenWidth/1.5;
        double menuTitleHeight = screenHeight/3;
        Image menuTitleImgScaled = new ImageIcon("RobberyBob/resources/images/MenuPanel/menuPanelTitle.png"
            ).getImage().getScaledInstance((int) menuTitleWidth, (int) menuTitleHeight, Image.SCALE_REPLICATE);
        menuTitleLabel.setIcon(new ImageIcon(menuTitleImgScaled));
        menuTitleLabel.setBounds((int) (screenWidth/2)-(int) (screenWidth/3), 50, (int) menuTitleWidth, (int)menuTitleHeight);
        menuTitleLabel.setMinimumSize(new Dimension((int) menuTitleWidth, (int) menuTitleHeight));
        menuTitleLabel.setMaximumSize(new Dimension((int) menuTitleWidth, (int) menuTitleHeight));

        menuBGLabel = new JLabel();
        menuBGImg = new ImageIcon("RobberyBob/resources/images/MenuPanel/menuPanelBG.png");
        Image image = menuBGImg.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        menuBGLabel.setIcon(scaledImageIcon);
        menuBGLabel.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);

        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playPressed();
                playLabel.setIcon(new ImageIcon(playImageNC));
                mainFrame.frame.getContentPane().removeAll();

                // view round panel
                roundPanel = new RoundPanel(mainFrame, subPanels, accessPanel, soundManager);
                roundPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(roundPanel);
                mainFrame.frame.setTitle("Choose Difficulty & Round");
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }


            @Override
            public void mouseEntered(MouseEvent e) {
                playLabel.setIcon(new ImageIcon(playImageC));
                soundManager.playHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playLabel.setIcon(new ImageIcon(playImageNC));

            }
        });

        rulesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playPressed();
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.setTitle("Tutorial");
                htpPanel = new HTPPanel(mainFrame, accessPanel, soundManager);
                mainFrame.frame.add(htpPanel);
                htpPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                htpPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rulesLabel.setIcon(new ImageIcon(rulesImageC));
                soundManager.playHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rulesLabel.setIcon(new ImageIcon(rulesImageNC));

            }
        });

        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playPressed();
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitLabel.setIcon(new ImageIcon(exitImageC));
                soundManager.playHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitLabel.setIcon(new ImageIcon(exitImageNC));

            }
        });

        // add components to the panel
        this.add(playLabel);
        this.add(rulesLabel);
        this.add(exitLabel);
        this.add(menuTitleLabel);
        this.add(menuBGLabel);

    }
}

