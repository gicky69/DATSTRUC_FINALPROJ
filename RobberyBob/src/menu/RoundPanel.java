package menu;

import core.Size;
import display.GamePanel;
import display.SubPanels;
import entity.Player;
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
    public GamePanel gamePanel;
    public int roundDetail;
    public int[] currentRound;
    public String currentDifficulty;
    public List<JLabel> easyDifficulty;
    public List<JLabel> mediumDifficulty;
    public List<JLabel> hardDifficulty;
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

        double buttonLabelWidth = screenWidth/5.5;

        easyDifficulty = new ArrayList<>();
        mediumDifficulty = new ArrayList<>();
        hardDifficulty = new ArrayList<>();

        JLabel difficultyLabel = new JLabel();
        double difficultyLabelWidth = screenWidth*0.8;
        double difficultyLabelHeight = screenHeight*0.15;
        Image difficultyLabelImage = new ImageIcon("RobberyBob/resources/images/RoundPanel/selectDifficultyTitle.png"
            ).getImage().getScaledInstance((int) difficultyLabelWidth, (int) difficultyLabelHeight, Image.SCALE_REPLICATE);
        difficultyLabel.setIcon(new ImageIcon(difficultyLabelImage));
        difficultyLabel.setBounds((int) ((screenWidth/2)-(difficultyLabelWidth/2)), (int)(screenHeight*.06), (int) difficultyLabelWidth, (int) difficultyLabelHeight);
        difficultyLabel.setMinimumSize(new Dimension((int) difficultyLabelWidth, (int) difficultyLabelHeight));
        difficultyLabel.setMaximumSize(new Dimension((int) difficultyLabelWidth, (int) difficultyLabelHeight));
        this.add(difficultyLabel);

        double diffLabelWidth = screenWidth/5.5;
        double diffLabelHeight = screenHeight*.08;

        JLabel easyLabel = new JLabel("Easy");
        easyLabel.setFont(new Font("DePixel", Font.BOLD, 40));
        easyLabel.setForeground(Color.WHITE);
        easyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        easyLabel.setBounds((int) (screenWidth/5.3-diffLabelWidth/2), (int) (screenHeight/4.2), (int) diffLabelWidth, (int) diffLabelHeight);
        this.add(easyLabel);

        JLabel mediumLabel = new JLabel("Medium");
        mediumLabel.setFont(new Font("DePixel", Font.BOLD, 40));
        mediumLabel.setForeground(Color.WHITE);
        mediumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mediumLabel.setBounds((int) (screenWidth/2-diffLabelWidth/2), (int) (screenHeight/4.2), (int) diffLabelWidth, (int) diffLabelHeight);
        this.add(mediumLabel);

        JLabel hardLabel = new JLabel("Hard");
        hardLabel.setFont(new Font("DePixel", Font.BOLD, 40));
        hardLabel.setForeground(Color.WHITE);
        hardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hardLabel.setBounds((int) (screenWidth/1.19-diffLabelWidth/2), (int) (screenHeight/4.2), (int) diffLabelWidth, (int) diffLabelHeight);
        this.add(hardLabel);

        createDifficultyButtons(easyDifficulty, currentRound[0], ((int) screenWidth/2)/5, "easy");
        createDifficultyButtons(mediumDifficulty, currentRound[1], (int) ((screenWidth/2)-buttonLabelWidth/2), "medium");
        createDifficultyButtons(hardDifficulty, currentRound[2], ((int) screenWidth/2)+((int) screenWidth/2)/2, "hard");

        backButton = new JLabel();
        double backButtonWidth = screenWidth/6;
        double backButtonHeight = screenHeight/12.5;
        Image backButtonImageNC = new ImageIcon("RobberyBob/resources/images/RoundPanel/backButtonNotClicked.png"
            ).getImage().getScaledInstance((int) (backButtonWidth), (int) (backButtonHeight), Image.SCALE_REPLICATE);
        Image backButtonImageC = new ImageIcon("RobberyBob/resources/images/RoundPanel/backButtonClicked.png"
            ).getImage().getScaledInstance((int) (backButtonWidth), (int) (backButtonHeight), Image.SCALE_REPLICATE);
        backButton.setBounds((int) (screenWidth/2-backButtonWidth/2), (int) (screenHeight/1.25), (int) backButtonWidth, (int) backButtonHeight);
        backButton.setIcon(new ImageIcon(backButtonImageNC));
        backButton.setMinimumSize(new Dimension((int) backButtonWidth, (int) backButtonHeight));
        backButton.setMaximumSize(new Dimension((int) backButtonWidth, (int) backButtonHeight));
        this.add(backButton);

        JLabel roundPanelBG = new JLabel();
        ImageIcon roundPanelImage = new ImageIcon("RobberyBob/resources/images/RoundPanel/menuPanelBG.png");
        Image image = roundPanelImage.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        roundPanelBG.setIcon(scaledImageIcon);
        roundPanelBG.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);
        roundPanelBG.setMinimumSize(new Dimension((int) screenWidth, (int) screenHeight));
        roundPanelBG.setMaximumSize(new Dimension((int) screenWidth, (int) screenHeight));
        this.add(roundPanelBG);

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playPressed();
                mainFrame.frame.getContentPane().removeAll();
                MenuPanel menuPanel = new MenuPanel(mainFrame, subPanels, accessPanel, soundManager);
                menuPanel.setBounds(0,0,mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                mainFrame.frame.add(menuPanel);
                mainFrame.update();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                soundManager.playHover();
                backButton.setIcon(new ImageIcon(backButtonImageC));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(new ImageIcon(backButtonImageNC));
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

    public void updatePlayerRoundData(String playerID, int difficultyIndex, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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
                    Player player = gamePanel.getPlayer();

                    if (!player.caught) {
                        int difficultyIndexNum = difficultyIndexMap.get(currentDifficulty);
                        if (roundData[difficultyIndexNum] >= currentRound[difficultyIndexNum] &&
                                roundDetail % 10 == currentRound[difficultyIndexNum]
                                && roundData[difficultyIndexNum] <= 5) {
                            roundData[difficultyIndexNum] += 1;
                        }
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

    private void createDifficultyButtons(List<JLabel> difficultyButtons, int currentRound, int xPosition, String difficulty) {
        for (int roundNum=1; roundNum<=5; roundNum++) {
            JLabel roundButton = createRoundButton(roundNum, currentRound, xPosition, difficulty);
            this.add(roundButton);
            difficultyButtons.add(roundButton);
        }
    }

    private JLabel createRoundButton(int roundNum, int currentRound, int xPosition, String difficulty) {
        double buttonLabelWidth = screenWidth/5.5;
        double buttonLabelHeight = screenHeight*.08;
        JLabel roundButton = new JLabel("Round " + roundNum);
        roundButton.setFont(new Font("DePixel", Font.BOLD, 20));
        roundButton.setForeground(Color.BLACK);
        roundButton.setEnabled(roundNum <= currentRound);
        roundButton.setBounds(xPosition, (int) (screenHeight/4.4) + (roundNum * 100), (int) buttonLabelWidth, (int) buttonLabelHeight);

        int roundDetail = getRoundDetail(difficulty, roundNum);

        // Set the image for the button based on roundNum
        String imagePathNC = "RobberyBob/resources/images/RoundPanel/round" + roundNum + "NotClicked.png";
        String imagePathC = "RobberyBob/resources/images/RoundPanel/round" + roundNum + "Clicked.png";
        Image roundButtonImageNC = new ImageIcon(imagePathNC).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image roundButtonImageC = new ImageIcon(imagePathC).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        roundButton.setIcon(new ImageIcon(roundButtonImageNC));
        roundButton.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        roundButton.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));

        if (roundButton.isEnabled()) {
            roundButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    soundManager.playPressed();
                    updateRoundDetail();
                    System.out.println("round button clicked");
                    subPanels.setRoundDetail(roundDetail, RoundPanel.this);
                    mainFrame.frame.getContentPane().setVisible(false);
                    mainFrame.frame.setVisible(false);
                    mainFrame.update();
                    currentDifficulty = difficulty;
                    new Thread(new GameLoop(new Game(new Size((int) screenWidth, (int) screenHeight), (int) screenWidth, (int) screenHeight, RoundPanel.this))).start();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    soundManager.playHover();
                    roundButton.setIcon(new ImageIcon(roundButtonImageC));
                    soundManager.playHover();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    roundButton.setIcon(new ImageIcon(roundButtonImageNC));
                }
            });
        }

        return roundButton;
    }

    private int getRoundDetail(String difficulty, int roundNum) {
        Map<String, Integer> difficultyIndexMap = new HashMap<>();
        difficultyIndexMap.put("easy", 10);
        difficultyIndexMap.put("medium", 20);
        difficultyIndexMap.put("hard", 30);

        return difficultyIndexMap.get(difficulty) + roundNum;
    }

    private void updateDifficultyButtons(List<JLabel> difficultyButtons, int currentRound) {
        for (int i = 0; i < difficultyButtons.size(); i++) {
            JLabel roundButton = difficultyButtons.get(i);
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
