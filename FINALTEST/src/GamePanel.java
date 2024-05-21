import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {
    JPanel GamePanel;
    enemyTEST enemy = new enemyTEST();

    // initialize moving object (Ame)


    public GamePanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setDoubleBuffered(true);
        this.setBackground(Color.GRAY);
        this.setFocusable(true);
    }
}