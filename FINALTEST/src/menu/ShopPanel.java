package menu;

import display.SubPanels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopPanel extends JPanel {
    Frame mainFrame;
    JLabel backButton, shopBGLabel;
    ImageIcon backButtonIMG, backButtonHighlight, shopBGIMG;
    JLabel temp = new JLabel("Shop Panel");
    public ShopPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.add(temp);

        backButton = new JLabel();
        //backButton.setLayout(null);
        backButtonHighlight = new ImageIcon("FINALTEST/images/buttons/back_-_clicked.png");
        backButtonIMG = new ImageIcon("FINALTEST/images/buttons/back_-_not_clicked.png");
        backButton.setIcon(backButtonIMG);
        backButton.setBounds(860, 700, 250, 150);
        this.add(backButton);

        shopBGLabel = new JLabel();
        shopBGIMG = new ImageIcon("FINALTEST/images/MainIBG/SHOP PANEL (1).png");
        shopBGLabel.setIcon(shopBGIMG);
        shopBGLabel.setBounds(0,0, 1920, 1080);
        this.add(shopBGLabel);

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels()));
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
