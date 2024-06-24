package display;

import core.Size;
import entity.Item;
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
        pauseIMG = new ImageIcon("FINALTEST/images/MainIBG/pauseBG-PausePanel.png");
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
        resumeNC = new ImageIcon("FINALTEST/images/buttons/resumeNotClicked-PausePanel.png");
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
                resumeC = new ImageIcon("FINALTEST/images/buttons/resumeClicked-PausePanel.png");
                resumeBGLB.setIcon(resumeC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeBGLB.setIcon(resumeNC);

            }
        });

        quitBGLB = new JLabel();
        quitNC = new ImageIcon("FINALTEST/images/buttons/quitNotClicked-PausePanel.png");
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
                quitC = new ImageIcon("FINALTEST/images/buttons/quitClicked-PausePanel.png");
                quitBGLB.setIcon(quitC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitBGLB.setIcon(quitNC);

            }
        });


        /*JButton resumeButton = new JButton("Resume");
        JButton quitRoundButton = new JButton("Quit Round");
        resumeButton.setSize(100, 50);*/
        pausePanel.add(resumeBGLB);
        pausePanel.add(quitBGLB);
        pausePanel.add(pauseLB);

        /*resumeButton.addActionListener(e -> {
            game.isPaused = false;
            pausePanel.setVisible(false);
        });*/

        /*quitRoundButton.addActionListener(e -> {
            game.isPaused = false;
            roundOver = true;
            pausePanel.setVisible(false);
            roundPanel.updateDisplay();
            roundPanel.mainFrame.frame.setVisible(true);
            gamePanel.revalidate();
            gamePanel.repaint();
        });*/

        gamePanel.setLayout(null);
        gamePanel.add(pausePanel);

    }
    public void setRoundOverPanel(GamePanel gamePanel, Game game, RoundPanel roundPanel) {
        this.gamePanel = gamePanel;
        this.roundPanel = roundPanel;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        int buttonWidth = (int) (screenWidth * 0.14);

        roundOverPanel = new JPanel();
        roundOverPanel.setLayout(null);
        roundOverPanel.setSize((int) screenWidth, (int) screenHeight);
        roundOverPanel.setVisible(false);

        roundOverLB = new JLabel();
        roundOverLB.setBounds(0,0,(int)screenWidth,(int) screenHeight);
        roundOverBG = new ImageIcon("FINALTEST/images/MainIBG/roundoverBGIMG-RoundPanel.png");
        Image scaledRoundOverBG = roundOverBG.getImage();
        Image finalScaledRoundOverBG = scaledRoundOverBG.getScaledInstance((int)screenWidth, (int)screenHeight, Image.SCALE_SMOOTH);
        roundOverLB.setIcon(new ImageIcon(finalScaledRoundOverBG));

        nextLB = new JLabel();
        nextNC = new ImageIcon("FINALTEST/images/buttons/nextNC-RoundPanel.png");
        nextC = new ImageIcon("FINALTEST/images/buttons/nextC-RoundPanel.png");
        nextLB.setLayout(null);
        nextLB.setBounds((int) (screenWidth/2)-(buttonWidth/2),(int) screenHeight-550,250,150);
        nextLB.setIcon(nextNC);
        roundOverPanel.add(nextLB);

        goBackLB = new JLabel();
        goBackNC = new ImageIcon("FINALTEST/images/buttons/backNotClicked-AllPanel.png");
        goBackC = new ImageIcon("FINALTEST/images/buttons/backClicked-AllPanel.png");
        goBackLB.setLayout(null);
        goBackLB.setBounds((int) (screenWidth/2)-(buttonWidth/2),(int) screenHeight-420,250,150);
        goBackLB.setIcon(goBackNC);
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

        /*nextRound.addActionListener(e -> {
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
        });*/

        /*roundPanelButton.addActionListener(e -> {
            roundOver = false;
            roundOverPanel.setVisible(false);
            updateScore(roundPanel.accessPanel.playerInUse);
            updateRoundDetails();
            roundPanel.updateDisplay();
            roundPanel.mainFrame.frame.setVisible(true);
            gamePanel.revalidate();
            gamePanel.repaint();
            game.isPaused = false;
        });*/

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
        String filePath = "FINALTEST/Database/playerdata.txt";
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
                        roundData[difficultyIndexNum] += 10;
                    } else if (difficultyIndexNum == 1) {
                        roundData[difficultyIndexNum] += 25;
                    } else if (difficultyIndexNum == 2) {
                        roundData[difficultyIndexNum] += 40;
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
