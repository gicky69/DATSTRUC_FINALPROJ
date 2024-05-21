import javax.swing.*;


public class Frame {
    JFrame frame;

    public Frame(int width, int height) {
        frame = new JFrame("Final Test");
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
    }
}
