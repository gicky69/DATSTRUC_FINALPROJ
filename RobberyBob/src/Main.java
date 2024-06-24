import display.SubPanels;
import menu.*;
import menu.Frame;
import Sound.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    Frame mainFrame;
    LandingPanel landingPanel;
    SoundManager soundManager;
    Sound sound;

    public Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();

        soundManager = new SoundManager();
        soundManager.importFX("/resources/sound/sfx/");
        soundManager.importBGM("RobberyBob/resources/sound/bgm/");

        soundManager.playBGM();
        mainFrame = new Frame(screenWidth, screenHeight);
        landingPanel = new LandingPanel(mainFrame);

        mainFrame.frame.add(landingPanel);
        landingPanel.requestFocusInWindow();
        mainFrame.frame.revalidate();
        mainFrame.frame.repaint();
        mainFrame.frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}