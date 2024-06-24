package display;

import core.Size;
import entity.Item;
import entity.Player;
import game.Game;
import game.GameLoop;
import menu.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This class is used to create subpanels for the game frame
public class SubPanels {
    GamePanel gamePanel;
    RoundPanel roundPanel;
    public JPanel pausePanel, roundOverPanel;
    public boolean roundOver = false;
    Player player;
    int roundDetail;
    JLabel pauseLB, resumeBGLB, quitBGLB, roundOverLB, nextLB, goBackLB;
    ImageIcon pauseIMG, resumeNC, resumeC, quitNC, quitC, nextNC, nextC, goBackC, goBackNC, roundOverBG;

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
        pausePanel.setLayout(null);
        pausePanel.setVisible(false);

        pauseLB = new JLabel();
        pauseLB.setLayout(null);
        pauseLB.setBounds(0,0,500,500);
        pauseIMG = new ImageIcon("RobberyBob/resources/images/MainIBG/pauseBG-PausePanel.png");
        pauseLB.setIcon(pauseIMG);
        pausePanel.setSize(500, 500);

        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int centerX = (gamePanel.getWidth() - pausePanel.getWidth()) / 2;
                int centerY = (gamePanel.getHeight() - pausePanel.getHeight()) / 2;
                pausePanel.setLocation(centerX, centerY);
            }
        });

        resumeBGLB = new JLabel();
        resumeNC = new ImageIcon("RobberyBob/resources/images/buttons/resumeNotClicked-PausePanel.png");
        resumeBGLB.setLayout(null);
        resumeBGLB.setBounds(175,170,150,100);
        resumeBGLB.setIcon(resumeNC);

        resumeBGLB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.isPaused = false;
                pausePanel.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resumeC = new ImageIcon("RobberyBob/resources/images/buttons/resumeClicked-PausePanel.png");
                resumeBGLB.setIcon(resumeC);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeBGLB.setIcon(resumeNC);
            }
        });

        quitBGLB = new JLabel();
        quitNC = new ImageIcon("RobberyBob/resources/images/buttons/quitNotClicked-PausePanel.png");
        quitBGLB.setLayout(null);
        quitBGLB.setBounds(175,240,150,100);
        quitBGLB.setIcon(quitNC);

        quitBGLB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.isPaused = false;
                roundOver = true;
                pausePanel.setVisible(false);
                roundPanel.updateDisplay();
                roundPanel.mainFrame.frame.setVisible(true);
                gamePanel.revalidate();
                gamePanel.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quitC = new ImageIcon("RobberyBob/resources/images/buttons/quitClicked-PausePanel.png");
                quitBGLB.setIcon(quitC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitBGLB.setIcon(quitNC);

            }
        });

        pausePanel.add(resumeBGLB);
        pausePanel.add(quitBGLB);
        pausePanel.add(pauseLB);

        gamePanel.setLayout(null);
        gamePanel.add(pausePanel);

    }
    public void setRoundOverPanel(GamePanel gamePanel, Game game, RoundPanel roundPanel, Player player) {
        this.gamePanel = gamePanel;
        this.roundPanel = roundPanel;
        this.player = player;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        int buttonWidth = 250;

        roundOverPanel = new JPanel();
        roundOverPanel.setLayout(null);
        roundOverPanel.setSize(screenWidth, screenHeight);
        roundOverPanel.setVisible(false);

        // BACKGROUND image
        roundOverLB = new JLabel();
        roundOverLB.setBounds(0,0,screenWidth, screenHeight);
        roundOverBG = new ImageIcon("RobberyBob/resources/images/MainIBG/roundoverBGIMG-RoundPanel.png");
        Image scaledRoundOverBG = roundOverBG.getImage();
        Image finalScaledRoundOverBG = scaledRoundOverBG.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
        roundOverLB.setIcon(new ImageIcon(finalScaledRoundOverBG));

        // SCORES
        JLabel greeting = new JLabel("Mission Accomplished!");
        int greetingWidth = 650;
        greeting.setBounds((screenWidth/2)-(greetingWidth/2),screenHeight-700,greetingWidth,100);
        greeting.setFont(new Font("DePixel", Font.BOLD, 35));
        greeting.setHorizontalAlignment(SwingConstants.CENTER);
        greeting.setForeground(Color.WHITE);

        JLabel scoreLabel = new JLabel();
        int scoreLabelWidth = 600;
        scoreLabel.setBounds((screenWidth/2)-(scoreLabelWidth/2),screenHeight-660,scoreLabelWidth,100);
        scoreLabel.setFont(new Font("DePixel", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        switch (roundPanel.currentDifficulty) {
            case "easy":
                scoreLabel.setText("You have scored " + 10 * player.itemsCollected + " points!");
                break;
            case "medium":
                scoreLabel.setText("You have scored " + 25 * player.itemsCollected + " points!");
                break;
            case "hard":
                scoreLabel.setText("You have scored " + 40 * player.itemsCollected + " points!");
                break;
        }
        nextLB = new JLabel();
        nextNC = new ImageIcon("RobberyBob/resources/images/buttons/nextNC-RoundPanel.png");
        nextC = new ImageIcon("RobberyBob/resources/images/buttons/nextC-RoundPanel.png");
        nextLB.setBounds((screenWidth/2)-(buttonWidth/2),screenHeight-550,250,150);
        nextLB.setIcon(nextNC);

        goBackLB = new JLabel();
        goBackNC = new ImageIcon("RobberyBob/resources/images/buttons/backNotClicked-AllPanel.png");
        goBackC = new ImageIcon("RobberyBob/resources/images/buttons/backClicked-AllPanel.png");
        goBackLB.setBounds((screenWidth/2)-(buttonWidth/2),screenHeight-420,250,150);
        goBackLB.setIcon(goBackNC);


        // add to round over panel
        roundOverPanel.add(greeting);
        roundOverPanel.add(scoreLabel);
        roundOverPanel.add(nextLB);
        roundOverPanel.add(goBackLB);
        
        nextLB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roundOver = false;
                roundOverPanel.setVisible(false);
                updateScore(roundPanel.accessPanel.playerInUse);
                updateRoundDetails();
                roundPanel.roundDetail++;
                double width = roundPanel.getWidth();
                double height = roundPanel.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, roundPanel))).start();

                gamePanel.revalidate();
                gamePanel.repaint();
                game.isPaused = false;

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nextLB.setIcon(nextC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextLB.setIcon(nextNC);
            }
        });

        goBackLB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roundOver = false;
                roundOverPanel.setVisible(false);
                updateScore(roundPanel.accessPanel.playerInUse);
                updateRoundDetails();
                roundPanel.updateDisplay();
                roundPanel.mainFrame.frame.setVisible(true);
                gamePanel.revalidate();
                gamePanel.repaint();
                game.isPaused = false;

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                goBackLB.setIcon(goBackC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                goBackLB.setIcon(goBackNC);

            }
        });

        roundOverPanel.add(roundOverLB);

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

    // will update score based on difficulty
    public void updateScore(String playerID) {
        String filePath = "RobberyBob/resources/Database/playerdata.txt";
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if (data[0].equals(playerID)) {
                    String[] roundDataStrings = data[2].split(",");
                    int[] roundData = new int[roundDataStrings.length];

                    for (int i = 0; i < roundDataStrings.length; i++) {
                        roundData[i] = Integer.parseInt(roundDataStrings[i]);
                    }

                    Map<String, Integer> difficultyIndexMap = new HashMap<>();
                    difficultyIndexMap.put("easy", 0);
                    difficultyIndexMap.put("medium", 1);
                    difficultyIndexMap.put("hard", 2);

                    // check conditions for updating the round data
                    int difficultyIndexNum = difficultyIndexMap.get(gamePanel.roundPanel.currentDifficulty);

                    // add scores to player data
                    if (difficultyIndexNum == 0) {
                        roundData[difficultyIndexNum] += 10 * player.itemsCollected;
                    } else if (difficultyIndexNum == 1) {
                        roundData[difficultyIndexNum] += 25 * player.itemsCollected;
                    } else if (difficultyIndexNum == 2) {
                        roundData[difficultyIndexNum] += 40 * player.itemsCollected;
                    }

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < roundData.length; i++) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(roundData[i]);
                    }
                    data[2] = sb.toString();
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

    }

}
