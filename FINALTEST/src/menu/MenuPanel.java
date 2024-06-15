package menu;

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
    JButton playButton, shopButton, settingsButton, tutorialButton, exitButton;
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

        tutorialButton = new JButton("Tutorial");
        tutorialButton.setBounds(860, 600, 200, 50);
        this.add(tutorialButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(860, 700, 200, 50);
        this.add(exitButton);

        playButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();

            // view round panel
            roundPanel = new RoundPanel(mainFrame, subPanels);
            roundPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            mainFrame.frame.add(roundPanel);
            mainFrame.frame.setTitle("Choose Difficulty & Round");
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();

        });

        shopButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.setTitle("Shop");
            shopPanel = new ShopPanel(mainFrame);
            mainFrame.frame.add(shopPanel);
            shopPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            shopPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });

        settingsButton.addActionListener(e -> {
            mainFrame.frame.setTitle("Settings");
            mainFrame.frame.getContentPane().removeAll();
            settingsPanel = new SettingsPanel(mainFrame);
            mainFrame.frame.add(settingsPanel);
            settingsPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            settingsPanel.requestFocusInWindow();
            mainFrame.frame.revalidate();
            mainFrame.frame.repaint();
        });

        tutorialButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();
            mainFrame.frame.setTitle("Tutorial");
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

