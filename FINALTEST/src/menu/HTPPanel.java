package menu;

import display.SubPanels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HTPPanel extends JPanel {
    Frame mainFrame;
    JLabel backButton;
    ImageIcon backButtonIMG, backButtonHighlight;
    JLabel temp = new JLabel("How to Play Panel");
    public HTPPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.add(temp);

        backButton = new JLabel();
        backButton.setLayout(null);
        backButton.setBounds(860, 700, 250, 150);
        backButtonHighlight = new ImageIcon("FINALTEST/images/buttons/back_-_clicked.png");
        backButtonIMG = new ImageIcon("FINALTEST/images/buttons/back_-_not_clicked.png");
        backButton.setIcon(backButtonIMG);
        this.add(backButton);


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
