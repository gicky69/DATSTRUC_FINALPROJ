import javax.swing.*;

public class MenuPanel extends JPanel {

    Frame mainFrame;
    GamePanel gamePanel;
    ShopPanel shopPanel;
    SettingsPanel settingsPanel;
    HTPPanel htpPanel;
    JButton playButton, shopButton, settingsButton, htpButton, exitButton;

    public MenuPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        gamePanel = new GamePanel();

        playButton = new JButton("Play");
        playButton.setBounds(860, 300, 200, 50);
        this.add(playButton);

        shopButton = new JButton("Shop");
        shopButton.setBounds(860, 400, 200, 50);
        this.add(shopButton);

        settingsButton = new JButton("Settings");
        settingsButton.setBounds(860, 500, 200, 50);
        this.add(settingsButton);

        htpButton = new JButton("How to Play");
        htpButton.setBounds(860, 600, 200, 50);
        this.add(htpButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(860, 700, 200, 50);
        this.add(exitButton);

        playButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.add(gamePanel);
            gamePanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            gamePanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });

        shopButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            shopPanel = new ShopPanel(mainFrame);
            mainFrame.frame.add(shopPanel);
            shopPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            shopPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });

        settingsButton.addActionListener(e -> {
            mainFrame.frame.setTitle("GAME NAME: Settings");
            mainFrame.frame.getContentPane().removeAll();
            settingsPanel = new SettingsPanel(mainFrame);
            mainFrame.frame.add(settingsPanel);
            settingsPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            settingsPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });

        htpButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            htpPanel = new HTPPanel(mainFrame);
            mainFrame.frame.add(htpPanel);
            htpPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            htpPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

    }
}

class ShopPanel extends JPanel {
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
            mainFrame.frame.add(new MenuPanel(mainFrame));
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });
    }
}

class SettingsPanel extends JPanel {
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
            mainFrame.frame.add(new MenuPanel(mainFrame));
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });
    }
}

class HTPPanel extends JPanel {
    Frame mainFrame;
    JButton backButton;
    JLabel temp = new JLabel("How to Play Panel");
    public HTPPanel(Frame mainFrame) {
        this.mainFrame = mainFrame;
        this.add(temp);

        backButton = new JButton("Back");
        backButton.setBounds(860, 700, 200, 50);
        this.add(backButton);

        backButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.add(new MenuPanel(mainFrame));
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });
    }
}