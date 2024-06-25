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
    JLabel backButton;
    ImageIcon backButtonIMG, backButtonHighlight;
    String playerInUse;
    ImageIcon htpBGImage, htpBoardImage, nextIMG, nextIMGClicked, backIMG, backIMGClicked;
    JLabel htpBG, htpBoard, nextLabel, backLabel;
    JLabel temp = new JLabel("How to Play Panel");
    public HTPPanel(Frame mainFrame, AccessPanel accessPanel, SoundManager soundManager) {
        this.mainFrame = mainFrame;
        this.accessPanel = accessPanel;
        this.soundManager = soundManager;
        this.add(temp);
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        backButton = new JLabel();
        backButton.setLayout(null);
        backButton.setBounds(50, 25, 250, 150);
        backButtonHighlight = new ImageIcon("RobberyBob/resources/images/buttons/backClicked-AllPanel.png");
        backButtonIMG = new ImageIcon("RobberyBob/resources/images/buttons/backNotClicked-AllPanel.png");
        backButton.setIcon(backButtonIMG);
        this.add(backButton);

        JLabel htpTitle = new JLabel("HOW TO PLAY");
        int htpTitleWidth = 480;
        int htpTitleHeight = 110;
        htpTitle.setFont(new Font("DePixel", Font.BOLD, 48));
        htpTitle.setForeground(Color.WHITE);
        htpTitle.setBounds((int) ((screenWidth/2)-htpTitleWidth/2), htpTitleHeight, htpTitleWidth, htpTitleHeight);
        this.add(htpTitle);

        JLabel instruction1 = new JLabel("Press to move up");
        instruction1.setFont(new Font("DePixel", Font.BOLD, 30));
        instruction1.setForeground(Color.WHITE);
        instruction1.setBounds((int) ((screenWidth/2)-htpTitleWidth/2)-150, (int) (screenHeight/2)-255, htpTitleWidth, htpTitleHeight);
        this.add(instruction1);

        JLabel instruction2 = new JLabel("Press to move left");
        instruction2.setFont(new Font("DePixel", Font.BOLD, 30));
        instruction2.setForeground(Color.WHITE);
        instruction2.setBounds((int) ((screenWidth/2)-htpTitleWidth/2)-150, (int) (screenHeight/2)-100, htpTitleWidth, htpTitleHeight);
        this.add(instruction2);

        JLabel instruction3 = new JLabel("Press to move right");
        instruction3.setFont(new Font("DePixel", Font.BOLD, 30));
        instruction3.setForeground(Color.WHITE);
        instruction3.setBounds((int) ((screenWidth/2)-htpTitleWidth/2)-150, (int) (screenHeight/2)+70, htpTitleWidth, htpTitleHeight);
        this.add(instruction3);

        JLabel instruction4 = new JLabel("Press to move down");
        instruction4.setFont(new Font("DePixel", Font.BOLD, 30));
        instruction4.setForeground(Color.WHITE);
        instruction4.setBounds((int) ((screenWidth/2)-htpTitleWidth/2)-150, (int) (screenHeight/2)+255, htpTitleWidth, htpTitleHeight);
        this.add(instruction4);

        htpBoard = new JLabel();
        htpBoardImage = new ImageIcon("RobberyBob/resources/images/HTPPanel/rulesBoard.png");
        Image imageBoard = htpBoardImage.getImage();
        Image scaledImageBoard = imageBoard.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIconBoard = new ImageIcon(scaledImageBoard);
        htpBoard.setIcon(scaledImageIconBoard);
        htpBoard.setBounds((int) ((screenWidth/2)-(screenWidth/2)), 0,  (int) screenWidth, (int) screenHeight);
        this.add(htpBoard);

        htpBG = new JLabel();
        htpBGImage = new ImageIcon("RobberyBob/resources/images/HTPPanel/menuPanelBG.png");
        Image image = htpBGImage.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        htpBG.setIcon(scaledImageIcon);
        htpBG.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);
        this.add(htpBG);

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                backButton.setIcon(backButtonHighlight);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonIMG);

            }
        });

    }
}
