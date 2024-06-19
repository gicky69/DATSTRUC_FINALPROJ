package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundPanel extends  JPanel {
    public Frame mainFrame;
    public AccessPanel accessPanel;
    public SubPanels subPanels;
    public int roundDetail;
    public int[] currentRound;
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

            });
        }

        for (int i=1; i <= currentRound[0]; i++) {
            easyDifficulty.get(i).setEnabled(true);
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

            });
        }

        for (int i=1; i <= currentRound[1]; i++) {
            mediumDifficulty.get(i).setEnabled(true);
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

            });
        }

        for (int i=1; i <= currentRound[2]; i++) {
            hardDifficulty.get(i).setEnabled(true);
            System.out.println("UPDATING HARD DIFFICULTY");
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

    public void update() {
        this.revalidate();
        this.repaint();
        this.mainFrame.frame.getContentPane().setVisible(true);
    }

    //getters

}
