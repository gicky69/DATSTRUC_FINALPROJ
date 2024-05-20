import javax.swing.*;


public class Frame {
    JFrame frame;

    public Frame() {
        frame = new JFrame("Game Mainframe");
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
    }
}
