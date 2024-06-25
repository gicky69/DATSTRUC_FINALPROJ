import menu.*;
import menu.Frame;
import sound.*;

import java.awt.*;

public class Main {
    Frame mainFrame;
    LandingPanel landingPanel;
    SoundManager soundManager;

    public Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();

        // directory for sfx and bgm
        soundManager = new SoundManager();
        soundManager.importFX("RobberyBob/resources/sound/sfx/");
        soundManager.importBGM("RobberyBob/resources/sound/bgm/landingPage/");

        soundManager.playBGM();
        mainFrame = new Frame(screenWidth, screenHeight);
        landingPanel = new LandingPanel(mainFrame, soundManager);

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