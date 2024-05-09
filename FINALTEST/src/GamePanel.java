import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    JPanel GamePanel;


    public GamePanel() {
        GamePanel.setSize(1920, 1080);
        GamePanel.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public void drawEnemies(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100, 100, 50, 50);
    }

    public void moveEnemies(Graphics g) {

    }
}
