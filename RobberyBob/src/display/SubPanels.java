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
        pausePanel.setLayout(null);
        pausePanel.setVisible(false);

        JLabel pauseLB = new JLabel();
        pauseLB.setLayout(null);
        pauseLB.setBounds(0,0,500,500);
        ImageIcon pauseIMG = new ImageIcon("RobberyBob/resources/images/MainIBG/pauseBG-PausePanel.png");
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

        JLabel resumeButton = new JLabel();
        ImageIcon resumeButtonNC = new ImageIcon("RobberyBob/resources/images/buttons/resumeNotClicked-PausePanel.png");
        resumeButton.setLayout(null);
        resumeButton.setBounds(175,170,150,100);
        resumeButton.setIcon(resumeButtonNC);

        resumeButton.addMouseListener(new MouseListener() {
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
                ImageIcon resumeButtonImageC = new ImageIcon("RobberyBob/resources/images/buttons/resumeClicked-PausePanel.png");
                resumeButton.setIcon(resumeButtonImageC);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resumeButton.setIcon(resumeButtonNC);
            }
        });

        JLabel quitButton = new JLabel();
        ImageIcon quitButtonNC = new ImageIcon("RobberyBob/resources/images/buttons/quitNotClicked-PausePanel.png");
        quitButton.setLayout(null);
        quitButton.setBounds(175,240,150,100);
        quitButton.setIcon(quitButtonNC);

        quitButton.addMouseListener(new MouseListener() {
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
                ImageIcon quitImageC = new ImageIcon("RobberyBob/resources/images/buttons/quitClicked-PausePanel.png");
                quitButton.setIcon(quitImageC);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonNC);

            }
        });

        pausePanel.add(resumeButton);
        pausePanel.add(quitButton);
        pausePanel.add(pauseLB);

        gamePanel.setLayout(null);
        gamePanel.add(pausePanel);

    }
    public void setRoundOverPanel(GamePanel gamePanel, Game game, RoundPanel roundPanel) {
        this.gamePanel = gamePanel;
        this.roundPanel = roundPanel;

        System.out.println("CALLING SETROUNDOVER");

        Player player = gamePanel.getPlayer();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        roundOverPanel = new JPanel();
        roundOverPanel.setLayout(null);
        roundOverPanel.setSize(screenWidth, screenHeight);
        roundOverPanel.setVisible(false);

        JLabel background = new JLabel();
        Image bgWin = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/backgroundWin.png"
            ).getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_REPLICATE);
        Image bgLose = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/backgroundLose.png"
        ).getImage().getScaledInstance(screenWidth, screenHeight, Image.SCALE_REPLICATE);
        background.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);

        // buttons' width and height
        double buttonLabelWidth = screenWidth/6;
        double buttonLabelHeight = screenHeight/13.5;

        JLabel nextButton = new JLabel();
        Image nextButtonNC = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/nextButtonNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image nextButtonC = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/nextButtonClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        nextButton.setIcon(new ImageIcon(nextButtonNC));
        nextButton.setVisible(false);

        JLabel retryButton = new JLabel();
        Image retryButtonNC = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/retryButtonNotClicked.png"
        ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image retryButtonC = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/retryButtonClicked.png"
        ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        retryButton.setIcon(new ImageIcon(retryButtonNC));
        retryButton.setVisible(false);

        JLabel backButton = new JLabel();
        Image backButtonNC = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/backButtonNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image backButtonC = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/backButtonClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        backButton.setIcon(new ImageIcon(backButtonNC));

        JLabel stage = new JLabel();
        Image stage1Image = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/stage.png"
        ).getImage().getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        Image stage2Image = new ImageIcon("RobberyBob/resources/images/RoundOverPanel/stage2.png"
            ).getImage().getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        stage.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);

        // SCORES
        JLabel greeting = new JLabel("");
        int greetingWidth = 700;
        greeting.setBounds((screenWidth/2)-(greetingWidth/2),(int) (screenHeight/1.95),greetingWidth,100);
        greeting.setFont(new Font("DePixel", Font.BOLD, 60));
        greeting.setHorizontalAlignment(SwingConstants.CENTER);
        greeting.setForeground(Color.WHITE);

        JLabel scoreLabel = new JLabel();
        int scoreLabelWidth = 600;
        scoreLabel.setBounds((screenWidth/2)-(scoreLabelWidth/2),(int) (screenHeight/1.3),scoreLabelWidth,100);
        scoreLabel.setFont(new Font("DePixel", Font.BOLD, 68));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setVisible(false);
        switch (roundPanel.currentDifficulty) {
            case "easy":
                scoreLabel.setText(10 * player.itemsCollected + " points!");
                break;
            case "medium":
                scoreLabel.setText(25 * player.itemsCollected + " points!");
                break;
            case "hard":
                scoreLabel.setText(40 * player.itemsCollected + " points!");
                break;
        }

        System.out.println(player.caught);
        // Check if player is caught
        if (player.caught) {
            background.setIcon(new ImageIcon(bgLose));
            stage.setIcon(new ImageIcon(stage2Image));
            retryButton.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) (screenHeight/1.4), (int) buttonLabelWidth, (int)buttonLabelHeight);
            backButton.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) (screenHeight/1.2), (int) buttonLabelWidth, (int)buttonLabelHeight);
            retryButton.setVisible(true);
            greeting.setText("YOU GOT CAUGHT!");

        } else {
            greeting.setText("YOU SCORED:");
            background.setIcon(new ImageIcon(bgWin));
            stage.setIcon(new ImageIcon(stage1Image));
            nextButton.setBounds((int) ((screenWidth-buttonLabelWidth)/1.05), (int) (screenHeight/1.3), (int) buttonLabelWidth, (int)buttonLabelHeight);
            backButton.setBounds((int) 50, (int) (screenHeight/1.3), (int) buttonLabelWidth, (int)buttonLabelHeight);
            nextButton.setVisible(true);
            scoreLabel.setVisible(true);

            Timer timer = new Timer(600, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Toggle visibility of the JLabel
                    scoreLabel.setVisible(!scoreLabel.isVisible());
                }
            });

            timer.start();        }

        // add to round over panel
        roundOverPanel.add(greeting);
        roundOverPanel.add(scoreLabel);
        roundOverPanel.add(backButton);
        roundOverPanel.add(retryButton);
        roundOverPanel.add(nextButton);
        roundOverPanel.add(stage);
        roundOverPanel.add(background);


        nextButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roundOver = false;
                roundOverPanel.setVisible(false);
                updateScore(roundPanel.accessPanel.playerInUse);
                player.itemsCollected = 0;
                updateRoundDetails();
                player.caught = false;
                roundPanel.roundDetail++;
                double width = roundPanel.getWidth();
                double height = roundPanel.getHeight();
                new Thread(new GameLoop(new Game(new Size((int)width, (int)height),(int)width, (int)height, roundPanel))).start();

                roundOverPanel.setFocusable(false);
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
                nextButton.setIcon(new ImageIcon(nextButtonC));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextButton.setIcon(new ImageIcon(nextButtonNC));
            }
        });

        backButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roundOver = false;
                roundOverPanel.setVisible(false);
                updateScore(roundPanel.accessPanel.playerInUse);
                updateRoundDetails();
                player.itemsCollected = 0;
                player.caught = false;
                roundPanel.updateDisplay();
                roundPanel.mainFrame.frame.setVisible(true);
                gamePanel.setVisible(false);
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
                backButton.setIcon(new ImageIcon(backButtonC));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(new ImageIcon(backButtonNC));

            }
        });


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
        Player player = gamePanel.getPlayer();
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
