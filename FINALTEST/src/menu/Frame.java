package menu;

import javax.swing.*;

public class Frame {
    public JFrame frame;

    public Frame(int width, int height) {
        frame = new JFrame("Robbery Bob: Kanto Edition");
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setFocusable(true);
    }

    public void update() {
        frame.revalidate();
        frame.repaint();
    }
}