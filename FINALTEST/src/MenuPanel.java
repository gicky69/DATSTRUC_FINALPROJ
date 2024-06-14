import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;

public class MenuPanel extends JPanel {

    Frame mainFrame;
    RoundPanel roundPanel;
    ShopPanel shopPanel;
    SettingsPanel settingsPanel;
    HTPPanel htpPanel;
    JButton playButton, shopButton, settingsButton, htpButton, exitButton;
    SubPanels subPanels;

    public MenuPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

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
            // Close mainFrame

            // view round panel
            roundPanel = new RoundPanel(mainFrame, subPanels);
            roundPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            mainFrame.frame.add(roundPanel);
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

class RoundPanel extends  JPanel {
    Frame mainFrame;
    SubPanels subPanels;
    JLabel temp = new JLabel("Round Panel");
    public int roundDetail = 11;
    public RoundPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.add(temp);

        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            System.out.println("Round " + roundNum);
            roundButton.setBounds(860, 300 + (roundNum * 100), 200, 50);
            this.add(roundButton);

            switch (roundNum) {
                case 1:
                    roundDetail = 11;
                    break;
                case 2:
                    roundDetail = 12;
                    break;
                case 3:
                    roundDetail = 13;
                    break;
                case 4:
                    roundDetail = 14;
                    break;
                case 5:
                    roundDetail = 15;
                    break;
            }

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail);
                mainFrame.frame.getContentPane().removeAll();
                new Thread(new GameLoop(new Game(new Size(1600, 1000),1600, 1000))).start();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();
            });
        }
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
            mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels()));
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
            mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels()));
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
            mainFrame.frame.add(new MenuPanel(mainFrame, new SubPanels()));
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });
    }
}