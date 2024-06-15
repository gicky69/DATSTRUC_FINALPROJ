package menu;

import display.SubPanels;

import javax.swing.*;

public class ShopPanel extends JPanel {
    Frame mainFrame;
    JButton backButton;
    JLabel temp = new JLabel("Shop Panel");
    public ShopPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.add(temp);

        backButton = new JButton("Back");
        backButton.setBounds(860, 700, 200, 50);
        this.add(backButton);

        backButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels()));
            mainFrame.frame.setTitle("Robbery Bob");
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });
    }
}
