import javax.swing.*;

public class Main {
    JFrame frame;

    JButton StartButton;
    JButton ExitButton;

    GamePanel GamePanel;

    public Main() {
        frame = new JFrame("Final Test");
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        StartButton = new JButton("Start");
        StartButton.setBounds(860, 500, 100, 50);
        frame.add(StartButton);

        ExitButton = new JButton("Exit");
        ExitButton.setBounds(860, 600, 100, 50);
        frame.add(ExitButton);

        GamePanel = new GamePanel();

        frame.setVisible(true);

        StartButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            GamePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            frame.add(GamePanel);
            GamePanel.requestFocusInWindow();
            frame.revalidate();
            frame.repaint();
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}