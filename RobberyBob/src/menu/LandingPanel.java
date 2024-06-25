package menu;

import display.SubPanels;
import sound.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LandingPanel extends JPanel {
    Frame mainFrame;
    AccessPanel accessPanel;
    SubPanels subPanels;
    SoundManager soundManager;

    public LandingPanel(Frame mainFrame, SoundManager soundManager) {
        this.mainFrame = mainFrame;
        this.soundManager = soundManager;
        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        subPanels = new SubPanels();
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        JLabel pressToStart = new JLabel("CLICK TO START");
        int pressToStartWidth = 400;
        pressToStart.setBounds((int) (screenWidth/2)-(pressToStartWidth/2), (int)screenHeight-300,pressToStartWidth,100);
        pressToStart.setFont(new Font("DePixel", Font.BOLD, 24));
        pressToStart.setHorizontalAlignment(SwingConstants.CENTER);
        pressToStart.setForeground(Color.CYAN);

        // Create a Timer with a delay of 500 ms
        Timer timer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of the JLabel
                pressToStart.setVisible(!pressToStart.isVisible());
            }
        });

        timer.start();

        JLabel menuTitleLabel = new JLabel();
        double menuTitleWidth = screenWidth/1.45;
        double menuTitleHeight = screenHeight/3.2;
        Image menuTitleImgScaled = new ImageIcon("RobberyBob/resources/images/MenuPanel/menuPanelTitle.png"
            ).getImage().getScaledInstance((int) menuTitleWidth, (int) menuTitleHeight, Image.SCALE_REPLICATE);
        menuTitleLabel.setIcon(new ImageIcon(menuTitleImgScaled));
        menuTitleLabel.setBounds((int) (screenWidth-menuTitleWidth)/2, (int) (screenHeight/4.5), (int) menuTitleWidth, (int) menuTitleHeight);
        System.out.println(screenHeight);
        System.out.println(screenHeight/2);

        JLabel menuBGLabel = new JLabel();
        ImageIcon menuBGImg = new ImageIcon("RobberyBob/resources/images/MenuPanel/menuPanelBG.png");
        Image image = menuBGImg.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        menuBGLabel.setIcon(scaledImageIcon);
        menuBGLabel.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playScreenTransitionSound();
                System.out.println("Clicked");
                mainFrame.frame.getContentPane().removeAll();
                accessPanel = new AccessPanel(mainFrame, subPanels, soundManager);
                mainFrame.frame.add(accessPanel);
                accessPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();
                soundManager.setBGMVolume(-14.0f);
            }
        });

        this.add(pressToStart);
        this.add(menuTitleLabel);
        this.add(menuBGLabel);
        mainFrame.frame.add(this);

    }
}
