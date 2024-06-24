package menu;

import display.SubPanels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShopPanel extends JPanel {
    Frame mainFrame;
    AccessPanel accessPanel;
    JLabel backButton, shopBGLabel;
    ImageIcon backButtonIMG, backButtonHighlight, shopBGIMG;
    String playerInUse;
    JLabel temp = new JLabel("Shop Panel");

    public ShopPanel(Frame mainFrame, AccessPanel accessPanel) {
        this.mainFrame = mainFrame;
        this.accessPanel = accessPanel;
        this.add(temp);
        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        backButton = new JLabel();
        //backButton.setLayout(null);
        backButtonHighlight = new ImageIcon("FINALTEST/images/buttons/backClicked-AllPanel.png");
        backButtonIMG = new ImageIcon("FINALTEST/images/buttons/backNotClicked-AllPanel.png");
        backButton.setIcon(backButtonIMG);
        backButton.setBounds(1500, 0, 250, 150);
        this.add(backButton);

        shopBGLabel = new JLabel();
        shopBGIMG = new ImageIcon("FINALTEST/images/MainIBG/shopBG-ShopPanel.png");
        shopBGLabel.setIcon(shopBGIMG);
        shopBGLabel.setBounds(0,0, 1920, 1080);
        this.add(shopBGLabel);

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
