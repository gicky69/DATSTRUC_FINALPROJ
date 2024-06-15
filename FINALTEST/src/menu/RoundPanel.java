package menu;

import core.Size;
import display.SubPanels;
import game.Game;
import game.GameLoop;

import javax.swing.*;

public class RoundPanel extends  JPanel {
    Frame mainFrame;
    SubPanels subPanels;
    JLabel temp = new JLabel("Round Panel");
    public int roundDetail;
    public RoundPanel(Frame mainFrame, SubPanels subPanels) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.add(temp);

        for (int roundNum = 1; roundNum <= 5; roundNum++) {
            JButton roundButton = new JButton("Round " + roundNum);
            System.out.println("Round " + roundNum);
            roundButton.setBounds(860, 300 + (roundNum * 100), 200, 50);
            this.add(roundButton);

            int roundDetail = 10+roundNum;

            roundButton.addActionListener(e -> {
                subPanels.setRoundDetail(roundDetail, this);
                mainFrame.frame.getContentPane().removeAll();
                new Thread(new GameLoop(new Game(new Size(1600, 1000),1600, 1000, this))).start();
                mainFrame.frame.revalidate();
                mainFrame.frame.repaint();
            });
        }
    }

    // will update the roundDetail variable in this class based on the passed variable on the subpanels.
    public void updateRoundDetail() {
        this.roundDetail = subPanels.getRoundDetail();
    }
}
