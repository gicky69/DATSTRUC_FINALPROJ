package display;

import core.Size;
import game.Game;
import game.GameLoop;
import menu.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

// This class is used to create subpanels for the game frame
public class SubPanels {

    GamePanel gamePanel;
    RoundPanel roundPanel;
    public JPanel pausePanel, roundOverPanel;
    public boolean roundOver = false;
    int roundDetail;

    public void setRoundDetail(int roundDetail, RoundPanel roundPanel) {
        this.roundDetail = roundDetail;
        this.roundPanel = roundPanel;
        roundPanel.updateRoundDetail();
    }

    public int getRoundDetail() {
        return roundDetail;
    }

    public void setPausePanel(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        pausePanel = new JPanel();
        pausePanel.setVisible(false);
        pausePanel.setSize(500, 500);

        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int centerX = (gamePanel.getWidth() - pausePanel.getWidth()) / 2;
                int centerY = (gamePanel.getHeight() - pausePanel.getHeight()) / 2;
                pausePanel.setLocation(centerX, centerY);
            }
        });

        JButton resumeButton = new JButton("Resume");
        JButton quitRoundButton = new JButton("Quit Round");
        resumeButton.setSize(100, 50);
        pausePanel.add(resumeButton);
        pausePanel.add(quitRoundButton);

        resumeButton.addActionListener(e -> {
            game.isPaused = false;
            pausePanel.setVisible(false);
        });

        quitRoundButton.addActionListener(e -> {
            game.isPaused = false;
            roundOver = true;
            pausePanel.setVisible(false);
            roundPanel.updateDisplay();
            roundPanel.mainFrame.frame.setVisible(true);
            gamePanel.revalidate();
            gamePanel.repaint();
        });

        gamePanel.setLayout(null);
        gamePanel.add(pausePanel);

    }
    public void setRoundOverPanel(GamePanel gamePanel, Game game, RoundPanel roundPanel) {
        this.gamePanel = gamePanel;
        this.roundPanel = roundPanel;
        roundOverPanel = new JPanel();
        roundOverPanel.setSize(500, 500);
        roundOverPanel.setVisible(false);

        JButton nextRound = new JButton("Next Round");
        JButton roundPanelButton = new JButton("Go Back");
        nextRound.setSize(100, 50);
        roundPanelButton.setSize(100, 50);
        roundOverPanel.add(nextRound);
        roundOverPanel.add(roundPanelButton);
        gamePanel.add(roundOverPanel);

        // Calculate the center position of the GamePanel
        int centerX = (gamePanel.getWidth() - roundOverPanel.getWidth()) / 2;
        int centerY = (gamePanel.getHeight() - roundOverPanel.getHeight()) / 2;
        roundOverPanel.setLocation(centerX,centerY);

        nextRound.addActionListener(e -> {
            roundOver = false;
            roundOverPanel.setVisible(false);

            updateRoundDetails();
            roundPanel.roundDetail++;
            System.out.println("ROUND DETAIL ON SUBPANELS: " + roundDetail);
            double width = roundPanel.getWidth();
            double height = roundPanel.getHeight();
            new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, roundPanel))).start();
            gamePanel.revalidate();
            gamePanel.repaint();
            game.isPaused = false;
        });

        roundPanelButton.addActionListener(e -> {
            roundOver = false;
            roundOverPanel.setVisible(false);

            updateRoundDetails();
            roundPanel.updateDisplay();
            roundPanel.mainFrame.frame.setVisible(true);
            gamePanel.revalidate();
            gamePanel.repaint();
            game.isPaused = false;
        });

    }

    public void updateRoundDetails() {
        int currentDifficultyindex = 0;
        switch (roundPanel.currentDifficulty) {
            case "easy":
                break;
            case "medium":
                currentDifficultyindex = 1;
                break;
            case "hard":
                currentDifficultyindex = 2;
                break;
        }
        roundPanel.updatePlayerRoundData(roundPanel.accessPanel.playerInUse, currentDifficultyindex);

    }

}
