package menu;

import display.SubPanels;

import javax.swing.*;

public class SettingsPanel extends JPanel {
    Frame mainFrame;
    JButton backButton;
    JLabel temp = new JLabel("Settings Panel");
    public SettingsPanel(Frame mainFrame) {
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
