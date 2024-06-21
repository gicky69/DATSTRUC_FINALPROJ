package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundPanel extends  JPanel {
    public Frame mainFrame;
    public AccessPanel accessPanel;
    public SubPanels subPanels;
    public int roundDetail;
    public int[] currentRound;
    public String currentDifficulty;
    public List<JButton> easyDifficulty;
    public List<JButton> mediumDifficulty;
    public List<JButton> hardDifficulty;
    JLabel temp = new JLabel("Round Panel");
    JLabel backButton;
    ImageIcon backButtonIMG, backButtonHighlight;

    public RoundPanel(Frame mainFrame, SubPanels subPanels, AccessPanel accessPanel) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.accessPanel = accessPanel;
        this.currentRound = getPlayerRoundData(accessPanel.playerInUse);
        this.add(temp);
        this.setLayout(null);


        // EASY DIFFICULTY
        easyDifficulty = new ArrayList<>();
        JLabel easyLabel = new JLabel("Easy Difficulty");
        easyLabel.setBounds(600, 100, 200, 50);
        easyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(easyLabel);
        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum <= currentRound[0]);
            roundButton.setBounds(600, 100 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            easyDifficulty.add(roundButton);

            // unique id for each round
            int roundDetail = 10+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().setVisible(false);
                currentDifficulty = "easy";

                // will get screen size of user
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, this))).start();

                mainFrame.frame.setVisible(false);
                mainFrame.update();

            });
        }


        // MEDIUM DIFFICULTY
        mediumDifficulty = new ArrayList<>();
        JLabel mediumLabel = new JLabel("Medium Difficulty");
        mediumLabel.setBounds(800, 100, 200, 50);
        mediumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(mediumLabel);
        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum <= currentRound[1]);
            roundButton.setBounds(800, 100 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            mediumDifficulty.add(roundButton);

            // unique id for each round
            int roundDetail = 20+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().setVisible(false);
                currentDifficulty = "medium";

                // will get screen size of user
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, this))).start();

                mainFrame.frame.setVisible(false);
                mainFrame.update();

            });
        }

        // HARD DIFFICULTY
        hardDifficulty = new ArrayList<>();
        JLabel hardLabel = new JLabel("Hard Difficulty");
        hardLabel.setBounds(1000, 100, 200, 50);
        hardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(hardLabel);
        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum <= currentRound[2]);
            roundButton.setBounds(1000, 100 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            hardDifficulty.add(roundButton);

            // unique id for each round
            int roundDetail = 30+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().setVisible(false);
                currentDifficulty = "hard";

                // will get screen size of user
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                double width = screenSize.getWidth();
                double height = screenSize.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, this))).start();

                mainFrame.frame.setVisible(false);
                mainFrame.update();

            });
        }

        backButton = new JLabel("Back");
        backButton.setBounds(860, 700, 250, 150);
        backButtonHighlight = new ImageIcon("FINALTEST/images/buttons/backClicked-AllPanel.png");
        backButtonIMG = new ImageIcon("FINALTEST/images/buttons/backNotClicked-AllPanel.png");
        backButton.setIcon(backButtonIMG);
        this.add(backButton);

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();

                MenuPanel menuPanel = new MenuPanel(mainFrame, subPanels, accessPanel);
                menuPanel.setBounds(0,0,mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(menuPanel);
                mainFrame.update();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonHighlight);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonIMG);

            }
        });

    }

    private int[] getPlayerRoundData(String playerId) {
        String filePath = "FINALTEST/Database/playerdata.txt"; // Replace with the path to your text file

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if (data[0].equals(playerId)) {
                    String[] roundDataStrings = data[1].split(",");
                    int[] roundData = new int[roundDataStrings.length];
                    for (int i = 0; i < roundDataStrings.length; i++) {
                        roundData[i] = Integer.parseInt(roundDataStrings[i]);
                    }
                    return roundData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // will update the roundDetail variable in this class based on the passed variable on the subpanels.
    public void updateRoundDetail() {
        this.roundDetail = subPanels.getRoundDetail();
    }

    public void updatePlayerRoundData(String playerID, int difficultyIndex) {
        System.out.println("RUNNING updatePlayerRoundData()");
        String filePath = "FINALTEST/Database/playerdata.txt";
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if (data[0].equals(playerID)) {
                    String[] roundDataStrings = data[1].split(",");
                    int[] roundData = new int[roundDataStrings.length];

                    for (int i = 0; i < roundDataStrings.length; i++) {
                        roundData[i] = Integer.parseInt(roundDataStrings[i]);
                    }

                    // Increment the round number for the given difficulty
                    System.out.println("round data and diff index before: "+roundData[difficultyIndex]);
                    roundData[difficultyIndex] += 1;
                    System.out.println("round data and diff index after: "+roundData[difficultyIndex]);

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < roundData.length; i++) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(roundData[i]);
                    }
                    data[1] = sb.toString();
                    line = String.join(":", data);
                }
                fileContent.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write the updated data back to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateDifficultyButtons(easyDifficulty, currentRound[0]);
        updateDifficultyButtons(mediumDifficulty, currentRound[1]);
        updateDifficultyButtons(hardDifficulty, currentRound[2]);
    }

    private void updateDifficultyButtons(List<JButton> difficultyButtons, int currentRound) {
        for (int i = 0; i < difficultyButtons.size(); i++) {
            JButton roundButton = difficultyButtons.get(i);
            // Enable the button if the current round is greater than or equal to the button's round number
            roundButton.setEnabled(i + 1 <= currentRound);
        }
    }




    public void updateDisplay() {
        this.currentRound = getPlayerRoundData(accessPanel.playerInUse);

        updateDifficultyButtons(easyDifficulty, currentRound[0]);
        updateDifficultyButtons(mediumDifficulty, currentRound[1]);
        updateDifficultyButtons(hardDifficulty, currentRound[2]);

        this.revalidate();
        this.repaint();
        this.mainFrame.frame.getContentPane().setVisible(true);
    }

    //getters

}
