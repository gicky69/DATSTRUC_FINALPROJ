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
    SubPanels subPanels;
    JLabel temp = new JLabel("Round Panel");
    public int roundDetail;
    public int currentRound = 1;
    private List<JButton> roundButtons = new ArrayList<>();

    public RoundPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.add(temp);

        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            roundButton.setEnabled(roundNum == 1);
            System.out.println("Round " + roundNum);
            roundButton.setBounds(860, 300 + (roundNum * 100), 200, 50);
            this.add(roundButton);
            roundButtons.add(roundButton);

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

                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();

                System.out.println(currentRound);
                if (currentRound < roundButtons.size()) {
                    roundButtons.get(currentRound).setEnabled(true);
                }
            });
        }
    }

    // will update the roundDetail variable in this class based on the passed variable on the subpanels.
    public void updateRoundDetail() {
        this.roundDetail = subPanels.getRoundDetail();
    }

    public void update() {
        for (int i = 0; i < roundButtons.size(); i++) {
            roundButtons.get(i).setEnabled(i < currentRound);
        }
        System.out.println("CURRENT ROUND ON ROUND PANEL: " + currentRound);
        this.revalidate();
        this.repaint();
        this.mainFrame.frame.getContentPane().setVisible(true);
    }
}
