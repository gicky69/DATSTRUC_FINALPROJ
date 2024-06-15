package display;

import game.Game;
import menu.RoundPanel;

import javax.swing.*;

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
        System.out.println("THIS ROUND DETAIL: " + roundDetail);
        return roundDetail;
    }

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
            System.out.println("Next Round!");
            gamePanel.revalidate();
            gamePanel.repaint();

            game.isPaused = false;
        });

        roundPanelButton.addActionListener(e -> {
            roundOver = false;
            roundOverPanel.setVisible(false);
            roundPanel.currentRound++;
            System.out.println("CURRENT ROUND ON SUBPANELS " + roundPanel.currentRound);
            roundPanel.update();
            roundPanel.mainFrame.frame.setVisible(true);
            gamePanel.revalidate();
            gamePanel.repaint();
            game.isPaused = false;
        });

    }

}
