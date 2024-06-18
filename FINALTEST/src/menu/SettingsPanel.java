package menu;

import display.SubPanels;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SettingsPanel extends JPanel {
    Frame mainFrame;
    JLabel backButton, settingsBGLabel;
    ImageIcon backButtonIMG, backButtonHighlight, settingsBGIMG;

    JLabel temp = new JLabel("Settings Panel");


    public SettingsPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.add(temp);

        backButton = new JLabel();
        backButtonHighlight = new ImageIcon("FINALTEST/images/buttons/backClicked-AllPanel.png");
        backButtonIMG = new ImageIcon("FINALTEST/images/buttons/backNotClicked-AllPanel.png");
        backButton.setIcon(backButtonIMG);
        backButton.setBounds(860, 700, 250, 150);
        this.add(backButton);

        settingsBGLabel = new JLabel();
        settingsBGIMG = new ImageIcon("FINALTEST/images/MainIBG/settingsBG-SettingsPanel.png");
        settingsBGLabel.setIcon(settingsBGIMG);
        settingsBGLabel.setBounds(0,0, 1920, 1080);
        this.add(settingsBGLabel);

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
