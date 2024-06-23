package menu;

import display.SubPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HTPPanel extends JPanel {
    Frame mainFrame;
    AccessPanel accessPanel;
    JLabel backButton;
    ImageIcon backButtonIMG, backButtonHighlight;
    String playerInUse;
    ImageIcon htp1BG, htp2BG, nextIMG, nextIMGClicked, backIMG, backIMGClicked;
    JLabel htp1bgLabel, htp2bgLabel, nextLabel, backLabel;
    JLabel temp = new JLabel("How to Play Panel");
    public HTPPanel(Frame mainFrame, AccessPanel accessPanel) {
        this.mainFrame = mainFrame;
        this.accessPanel = accessPanel;
        this.add(temp);
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        backButton = new JLabel();
        backButton.setLayout(null);
        backButton.setBounds(50, 25, 250, 150);
        backButtonHighlight = new ImageIcon("FINALTEST/images/buttons/backClicked-AllPanel.png");
        backButtonIMG = new ImageIcon("FINALTEST/images/buttons/backNotClicked-AllPanel.png");
        backButton.setIcon(backButtonIMG);
        this.add(backButton);

        nextLabel = new JLabel();
        nextLabel.setLayout(null);
        nextIMG = new ImageIcon("FINALTEST/images/buttons/nextButton-HTPPanel.png");
        nextLabel.setIcon(nextIMG);
        nextLabel.setBounds(1750,500, 150,150);
        this.add(nextLabel);

        backLabel = new JLabel();
        backLabel.setLayout(null);
        backIMG = new ImageIcon("FINALTEST/images/buttons/backButton-HTPPanel.png");
        backLabel.setIcon(backIMG);
        backLabel.setBounds(25,500, 150, 150);
        //this.add(backLabel);

        htp1bgLabel = new JLabel();
        htp1BG = new ImageIcon("FINALTEST/images/MainIBG/htp1-HTPPanel.png");
        Image image = htp1BG.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        htp1bgLabel.setBounds(0,0,1920,1080);
        htp1bgLabel.setIcon(scaledImageIcon);
        this.add(htp1bgLabel);

        htp2bgLabel = new JLabel();
        htp2bgLabel.setLayout(null);
        htp2BG = new ImageIcon("FINALTEST/images/MainIBG/htp2-HTPPanel.png");
        htp2bgLabel.setIcon(htp2BG);
        htp2bgLabel.setBounds(0,0,1920,1080);


        nextLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.add(backLabel);
                mainFrame.frame.add(backButton);
                mainFrame.frame.add(htp2bgLabel);
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

                backLabel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        mainFrame.frame.getContentPane().removeAll();
                        mainFrame.frame.add(nextLabel);
                        mainFrame.frame.add(backButton);
                        mainFrame.frame.add(htp1bgLabel);
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
                        backIMGClicked = new ImageIcon("FINALTEST/images/buttons/backButtonClicked-HTPPanel.png");
                        backLabel.setIcon(backIMGClicked);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        backLabel.setIcon(backIMG);
                    }
                });



            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nextIMGClicked = new ImageIcon("FINALTEST/images/buttons/nextButtonClicked-HTPPanel.png");
                nextLabel.setIcon(nextIMGClicked);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextLabel.setIcon(nextIMG);

            }
        });



        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels(), accessPanel));
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
