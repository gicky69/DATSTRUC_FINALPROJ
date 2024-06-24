import display.SubPanels;
import menu.*;
import menu.Frame;

import javax.swing.*;
import java.awt.*;

public class Main {
    Frame mainFrame;
    JButton startButton;
    JButton exitButton;
    AccessPanel loginPanel;
    MenuPanel menuPanel;
    ShopPanel shopPanel;
    SettingsPanel settingsPanel;
    HTPPanel htpPanel;
    SubPanels subPanels;

    public Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        mainFrame = new Frame((int) screenWidth, (int) screenHeight);
        subPanels = new SubPanels();
        loginPanel = new AccessPanel(mainFrame, subPanels);

        mainFrame.frame.add(loginPanel);
        loginPanel.requestFocusInWindow();
        mainFrame.frame.revalidate();
        mainFrame.frame.repaint();
        mainFrame.frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }
}