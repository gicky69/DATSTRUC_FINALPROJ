package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoundPanel extends  JPanel {
    public Frame mainFrame;
    public SubPanels subPanels;
    public int roundDetail;
    public static int[] currentRound = {1,1,1};
    JLabel temp = new JLabel("Round Panel");
    JButton backButton;

    public RoundPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.add(temp);
        this.setLayout(null);

        // EASY DIFFICULTY
        List<JButton> easyDifficulty = new ArrayList<>();
        JLabel easyLabel = new JLabel("Easy Difficulty");
        easyLabel.setBounds(600, 100, 200, 50);
        easyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(easyLabel);
        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum == 1);
            roundButton.setBounds(600, 100 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            easyDifficulty.add(roundButton);

            // unique id for each round
            int roundDetail = 10+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().setVisible(false);

                // will get screen size of user
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, this))).start();

                mainFrame.frame.setVisible(false);
                mainFrame.update();

                if (currentRound[0] < easyDifficulty.size()) {
                    easyDifficulty.get(currentRound[0]).setEnabled(true);
                }
                currentRound[0]++;
            });
        }

        // MEDIUM DIFFICULTY
        List<JButton> mediumDifficulty = new ArrayList<>();
        JLabel mediumLabel = new JLabel("Medium Difficulty");
        mediumLabel.setBounds(800, 100, 200, 50);
        mediumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(mediumLabel);
        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum == 1);
            roundButton.setBounds(800, 100 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            mediumDifficulty.add(roundButton);

            // unique id for each round
            int roundDetail = 20+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().setVisible(false);

                // will get screen size of user
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, this))).start();

                mainFrame.frame.setVisible(false);
                mainFrame.update();

                if (currentRound[1] < mediumDifficulty.size()) {
                    mediumDifficulty.get(currentRound[1]).setEnabled(true);
                }

                currentRound[1]++;
            });
        }

        // HARD DIFFICULTY
        List<JButton> hardDifficulty = new ArrayList<>();
        JLabel hardLabel = new JLabel("Hard Difficulty");
        hardLabel.setBounds(1000, 100, 200, 50);
        hardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(hardLabel);
        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum == 1);
            roundButton.setBounds(1000, 100 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            hardDifficulty.add(roundButton);

            // unique id for each round
            int roundDetail = 30+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().setVisible(false);

                // will get screen size of user
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, this))).start();

                mainFrame.frame.setVisible(false);
                mainFrame.update();

                if (currentRound[2] < hardDifficulty.size()) {
                    hardDifficulty.get(currentRound[2]).setEnabled(true);
                }

                currentRound[2]++;
            });
        }



        backButton = new JButton("Back");
        backButton.setBounds(800, 800, 200, 50);
        this.add(backButton);
        backButton.addActionListener(e -> {
            mainFrame.frame.getContentPane().removeAll();

            MenuPanel menuPanel = new MenuPanel(mainFrame, subPanels);
            menuPanel.setBounds(0,0,mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
            mainFrame.frame.add(menuPanel);
            mainFrame.update();
        });
    }

    // will update the roundDetail variable in this class based on the passed variable on the subpanels.
    public void updateRoundDetail() {
        this.roundDetail = subPanels.getRoundDetail();
    }

    public void update() {
        this.revalidate();
        this.repaint();
        this.mainFrame.frame.getContentPane().setVisible(true);
    }
}
