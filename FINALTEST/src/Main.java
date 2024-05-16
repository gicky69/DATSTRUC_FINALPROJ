import javax.swing.*;

public class Main {
    Frame mainFrame;
    JButton startButton;
    JButton exitButton;
    GamePanel gamePanel;
    LoginPanel loginPanel;
    MenuPanel menuPanel;

    public Main() {
        mainFrame = new Frame();
        startButton = new JButton("Start");
        startButton.setBounds(860, 500, 100, 50);
        mainFrame.frame.add(startButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(860, 600, 100, 50);
        mainFrame.frame.add(exitButton);

        gamePanel = new GamePanel();
        loginPanel = new LoginPanel(mainFrame);
        menuPanel = new MenuPanel(mainFrame);

        mainFrame.frame.setVisible(true);

        startButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.add(loginPanel);
            loginPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();

        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}