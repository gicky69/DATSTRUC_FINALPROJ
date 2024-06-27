package menu;

import display.SubPanels;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HTPPanel extends JPanel {
    Frame mainFrame;
    AccessPanel accessPanel;
    SoundManager soundManager;
    public HTPPanel(Frame mainFrame, AccessPanel accessPanel, SoundManager soundManager) {
        this.mainFrame = mainFrame;
        this.accessPanel = accessPanel;
        this.soundManager = soundManager;
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();


        JLabel backButton = new JLabel();
        double backButtonWidth = screenWidth/6;
        double backButtonHeight = screenHeight/13.5;
        Image backButtonNC = new ImageIcon("RobberyBob/resources/images/HTPPanel/backButtonNotClicked.png"
            ).getImage().getScaledInstance((int) backButtonWidth, (int) backButtonHeight, Image.SCALE_REPLICATE);
        Image backButtonC = new ImageIcon("RobberyBob/resources/images/HTPPanel/backButtonClicked.png"
            ).getImage().getScaledInstance((int) backButtonWidth, (int) backButtonHeight, Image.SCALE_REPLICATE);
        backButton.setBounds(50, 50,  (int) backButtonWidth, (int) backButtonHeight);
        backButton.setIcon(new ImageIcon(backButtonNC));
        this.add(backButton);

        JLabel htpBG = new JLabel();
        ImageIcon htpBGImage = new ImageIcon("RobberyBob/resources/images/HTPPanel/rulesBG.png");
        Image image = htpBGImage.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth+100, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        htpBG.setIcon(scaledImageIcon);
        htpBG.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);
        this.add(htpBG);

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                soundManager.playPressed();
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels(), accessPanel, soundManager));
                mainFrame.frame.setTitle("Robbery Bob");
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
                soundManager.playHover();
                backButton.setIcon(new ImageIcon(backButtonC));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(new ImageIcon(backButtonNC));

            }
        });

    }
}
