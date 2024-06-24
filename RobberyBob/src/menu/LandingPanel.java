package menu;

import display.SubPanels;
import Sound.*;

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
    Sound sound;

    public LandingPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        subPanels = new SubPanels();
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        JLabel pressToStart = new JLabel("Click Anywhere to Start");
        int pressToStartWidth = 400;
        pressToStart.setBounds((screenWidth/2)-(pressToStartWidth/2), screenHeight-300,pressToStartWidth,100);
        pressToStart.setFont(new Font("DePixel", Font.BOLD, 20));
        pressToStart.setHorizontalAlignment(SwingConstants.CENTER);
        pressToStart.setForeground(Color.WHITE);

        // Create a Timer with a delay of 500 ms
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of the JLabel
                pressToStart.setVisible(!pressToStart.isVisible());
            }
        });

        timer.start();

        JLabel background = new JLabel();
        Image img = new ImageIcon("RobberyBob/resources/images/MenuPanel/landingPageBG.png").getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_REPLICATE);
        background.setIcon(new ImageIcon(img));
        background.setBounds(0,0, screenWidth, screenHeight);


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Clicked");
                mainFrame.frame.getContentPane().removeAll();
                accessPanel = new AccessPanel(mainFrame, subPanels);
                mainFrame.frame.add(accessPanel);
                accessPanel.requestFocusInWindow();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();
            }
        });

        this.add(pressToStart);
        this.add(background);
        mainFrame.frame.add(this);

    }
}
