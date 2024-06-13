package display;

import game.Game;

import javax.swing.*;

// This class is used to create subpanels for the game frame
public class SubPanels {

    GamePanel gamePanel;
    public JPanel pausePanel, roundOverPanel;
    public boolean roundOver = false;

    public void setPausePanel(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        pausePanel = new JPanel();
        pausePanel.setVisible(false);
        pausePanel.setSize(500, 500);

        JButton resume = new JButton("Resume");
        resume.setSize(100, 50);
        pausePanel.add(resume);
        gamePanel.add(pausePanel);

        // Calculate the center position of the GamePanel
        int centerX = (gamePanel.getWidth() - pausePanel.getWidth()) / 2;
        int centerY = (gamePanel.getHeight() - pausePanel.getHeight()) / 2;
        pausePanel.setLocation(centerX,centerY);

        resume.addActionListener(e -> {
            game.isPaused = false;
            pausePanel.setVisible(false);
        });
    }
    public void setRoundOverPanel(GamePanel gamePanel, Game game) {
        this.gamePanel = gamePanel;
        roundOverPanel = new JPanel();
        roundOverPanel.setSize(500, 500);
        roundOverPanel.setVisible(false);

        JButton nextRound = new JButton("Next Round");
        nextRound.setSize(100, 50);
        roundOverPanel.add(nextRound);
        gamePanel.add(roundOverPanel);

        // Calculate the center position of the GamePanel
        int centerX = (gamePanel.getWidth() - roundOverPanel.getWidth()) / 2;
        int centerY = (gamePanel.getHeight() - roundOverPanel.getHeight()) / 2;
        roundOverPanel.setLocation(centerX,centerY);

        nextRound.addActionListener(e -> {
            roundOver = false;
            roundOverPanel.setVisible(false);
            System.out.println("Next Round!");
            gamePanel.revalidate();
            gamePanel.repaint();

            game.isPaused = false;
        });

    }
}
