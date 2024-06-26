package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;
import sound.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
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
    SoundManager soundManager;
    JLabel temp = new JLabel("Round Panel");
    JLabel backButton, roundLB;
    ImageIcon backButtonIMG, backButtonHighlight, roundIMG;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double screenWidth = screenSize.getWidth();
    double screenHeight = screenSize.getHeight();

    public RoundPanel(Frame mainFrame, SubPanels subPanels, AccessPanel accessPanel, SoundManager soundManager) {
        this.soundManager = soundManager;
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.accessPanel = accessPanel;
        this.currentRound = getPlayerRoundData(accessPanel.playerInUse);
        this.add(temp);
        this.setLayout(null);

        easyDifficulty = new ArrayList<>();
        mediumDifficulty = new ArrayList<>();
        hardDifficulty = new ArrayList<>();


        createDifficultyButtons(easyDifficulty, currentRound[0], ((int) screenWidth/2)/4, "easy");
        createDifficultyButtons(mediumDifficulty, currentRound[1], ((int) screenWidth/2)-(250/2), "medium");
        createDifficultyButtons(hardDifficulty, currentRound[2], ((int) screenWidth/2)+((int) screenWidth/2)/2, "hard");

//        // EASY DIFFICULTY
//        easyDifficulty = new ArrayList<>();
//        JLabel easyLabel = new JLabel("");
//        this.add(easyLabel);
//        for (int roundNum = 1; roundNum <= 5; roundNum++) {
//            JButton roundButton = new JButton("Round " + roundNum);
//            Font roundFont = new Font("Constantia", Font.BOLD, 20);
//            roundButton.setFont(roundFont);
//            roundButton.setBackground(Color.blue.darker());
//            roundButton.setForeground(Color.white);
//
//            roundButton.setEnabled(roundNum <= currentRound[0]);
//            roundButton.setBounds(((int) screenWidth/2)/4, 300 + (roundNum * 100), 200, 50);
//            this.add(roundButton);
//            easyDifficulty.add(roundButton);
//
//            // unique id for each round
//            int roundDetail = 10+roundNum;
//
//            roundButton.addActionListener(e -> {
//                subPanels.setRoundDetail(roundDetail, this);
//                mainFrame.frame.getContentPane().setVisible(false);
//                mainFrame.frame.setVisible(false);
//                mainFrame.update();
//                currentDifficulty = "easy";
//                new Thread(new GameLoop(new Game(new Size((int)screenWidth, (int)screenHeight),(int)screenWidth, (int)screenHeight, this))).start();
//
//            });
//        }
//
//
//        // MEDIUM DIFFICULTY
//        mediumDifficulty = new ArrayList<>();
//        for (int roundNum = 1; roundNum <= 5; roundNum++) {
//            JButton roundButton = new JButton("Round " + roundNum);
//            Font roundFont = new Font("Constantia", Font.BOLD, 20);
//            roundButton.setFont(roundFont);
//            roundButton.setBackground(Color.blue.darker());
//            roundButton.setForeground(Color.white);
//            roundButton.setEnabled(roundNum <= currentRound[1]);
//            roundButton.setBounds(((int) screenWidth/2)-(250/2), 300 + (roundNum * 100), 200, 50);
//            this.add(roundButton);
//            mediumDifficulty.add(roundButton);
//
//            // unique id for each round
//            int roundDetail = 20+roundNum;
//
//            roundButton.addActionListener(e -> {
//                subPanels.setRoundDetail(roundDetail, this);
//                mainFrame.frame.getContentPane().setVisible(false);
//                mainFrame.frame.setVisible(false);
//                mainFrame.update();
//                currentDifficulty = "medium";
//                new Thread(new GameLoop(new Game(new Size((int)screenWidth, (int)screenHeight),(int)screenWidth, (int)screenHeight, this))).start();
//
//            });
//        }
//
//        // HARD DIFFICULTY
//        hardDifficulty = new ArrayList<>();
//        for (int roundNum = 1; roundNum <= 5; roundNum++) {
//            JButton roundButton = new JButton("Round " + roundNum);
//            Font roundFont = new Font("Constantia", Font.BOLD, 20);
//            roundButton.setFont(roundFont);
//            roundButton.setBackground(Color.blue.darker());
//            roundButton.setForeground(Color.white);
//            roundButton.setEnabled(roundNum <= currentRound[2]);
//            roundButton.setBounds(((int) screenWidth/2)+((int) screenWidth/2)/2, 300 + (roundNum * 100), 200, 50);
//            this.add(roundButton);
//            hardDifficulty.add(roundButton);
//
//            // unique id for each round
//            int roundDetail = 30+roundNum;
//
//            roundButton.addActionListener(e -> {
//                subPanels.setRoundDetail(roundDetail, this);
//                mainFrame.frame.getContentPane().setVisible(false);
//                mainFrame.frame.setVisible(false);
//                mainFrame.update();
//                currentDifficulty = "hard";
//                new Thread(new GameLoop(new Game(new Size((int)screenWidth, (int)screenHeight),(int)screenWidth, (int)screenHeight, this))).start();
//
//            });
//        }

        backButton = new JLabel("Back");
        backButton.setBounds(20, 50, 250, 150);
        backButtonHighlight = new ImageIcon("RobberyBob/resources/images/buttons/backClicked-AllPanel.png");
        backButtonIMG = new ImageIcon("RobberyBob/resources/images/buttons/backNotClicked-AllPanel.png");
        backButton.setIcon(backButtonIMG);
        this.add(backButton);

        roundLB = new JLabel();
        roundLB.setBounds(0,0,(int) screenWidth, (int) screenHeight);
        roundIMG = new ImageIcon("RobberyBob/resources/images/MainIBG/roundBG-RoundPanel.png");
        Image image = roundIMG.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledRoundLB = new ImageIcon(scaledImage);
        roundLB.setIcon(scaledRoundLB);
        roundLB.setVisible(true);
        this.add(roundLB);


        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mainFrame.frame.getContentPane().removeAll();
                MenuPanel menuPanel = new MenuPanel(mainFrame, subPanels, accessPanel, soundManager);
                menuPanel.setBounds(0,0,mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(menuPanel);
                mainFrame.update();
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
        String filePath = "RobberyBob/resources/Database/playerdata.txt"; // Replace with the path to your text file

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
        String filePath = "RobberyBob/resources/Database/playerdata.txt";
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

                    Map<String, Integer> difficultyIndexMap = new HashMap<>();
                    difficultyIndexMap.put("easy", 0);
                    difficultyIndexMap.put("medium", 1);
                    difficultyIndexMap.put("hard", 2);

                    // check conditions for updating the round data
                    int difficultyIndexNum = difficultyIndexMap.get(currentDifficulty);
                    if (roundData[difficultyIndexNum] >= currentRound[difficultyIndexNum] &&
                            roundDetail % 10 == currentRound[difficultyIndexNum]
                            && roundData[difficultyIndexNum] <= 5) {
                        roundData[difficultyIndexNum] += 1;
                    }

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
        System.out.println("ROUND DETAIL ON ROUNDPANEL: " + roundDetail);
    }

    private void createDifficultyButtons(List<JButton> difficultyButtons, int currentRound, int xPosition, String difficulty) {
        for (int roundNum=1; roundNum<=5; roundNum++) {
            JButton roundButton = createRoundButton(roundNum, currentRound, xPosition, difficulty);
            this.add(roundButton);
            difficultyButtons.add(roundButton);
        }
    }

    private JButton createRoundButton(int roundNum, int currentRound, int xPosition, String difficulty) {
        JButton roundButton = new JButton("Round " + roundNum);
        roundButton.setFont(new Font("DePixel", Font.BOLD, 20));
        roundButton.setForeground(Color.BLACK);
        roundButton.setEnabled(roundNum <= currentRound);
        roundButton.setBounds(xPosition, 300 + (roundNum * 100), 200, 50);

        int roundDetail = getRoundDetail(difficulty, roundNum);

        roundButton.addActionListener(e -> {
            System.out.println("round button clicked");
            subPanels.setRoundDetail(roundDetail, this);
            mainFrame.frame.getContentPane().setVisible(false);
            mainFrame.frame.setVisible(false);
            mainFrame.update();
            currentDifficulty = difficulty;
            new Thread(new GameLoop(new Game(new Size((int)screenWidth, (int)screenHeight),(int)screenWidth, (int)screenHeight, this))).start();
        });

        return roundButton;
    }

    private int getRoundDetail(String difficulty, int roundNum) {
        Map<String, Integer> difficultyIndexMap = new HashMap<>();
        difficultyIndexMap.put("easy", 10);
        difficultyIndexMap.put("medium", 20);
        difficultyIndexMap.put("hard", 30);

        return difficultyIndexMap.get(difficulty) + roundNum;
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
