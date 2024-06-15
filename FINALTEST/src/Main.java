import display.SubPanels;
import menu.*;

import javax.swing.*;

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
        mainFrame = new Frame(1600,900);
        subPanels = new SubPanels();
        loginPanel = new AccessPanel(mainFrame, subPanels);
        menuPanel = new MenuPanel(mainFrame, subPanels);
        shopPanel = new ShopPanel(mainFrame);
        settingsPanel = new SettingsPanel(mainFrame);
        htpPanel = new HTPPanel(mainFrame);

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