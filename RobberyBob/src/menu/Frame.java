package menu;

import javax.swing.*;
import java.net.URL;

public class Frame {
    public JFrame frame;

    public Frame(int width, int height) {
        frame = new JFrame("Robbery Bob: Kanto Edition");
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setUndecorated(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setFocusable(true);

        URL iconURL = getClass().getResource("/images/icon.png");
        // iconURL is null when not found
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());
    }

    public void update() {
        frame.revalidate();
        frame.repaint();
    }
}