import javax.swing.*;

public class Main {
    Frame mainFrame;
    JButton startButton;
    JButton exitButton;
    GamePanel gamePanel;
    LoginPanel loginPanel;
    MenuPanel menuPanel;
    ShopPanel shopPanel;
    SettingsPanel settingsPanel;
    HTPPanel htpPanel;


    public Main() {
        mainFrame = new Frame();
        gamePanel = new GamePanel();
        loginPanel = new LoginPanel(mainFrame);
        menuPanel = new MenuPanel(mainFrame);
        shopPanel = new ShopPanel(mainFrame);
        settingsPanel = new SettingsPanel(mainFrame);
        htpPanel = new HTPPanel(mainFrame);

        mainFrame.frame.add(loginPanel);
        loginPanel.requestFocusInWindow();
        mainFrame.frame.revalidate();
        mainFrame.frame.repaint();
        mainFrame.frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}