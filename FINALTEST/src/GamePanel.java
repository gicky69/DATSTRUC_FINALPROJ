import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Point[] enemies = new Point[10];

    public GamePanel() {
        setSize(1920, 1080);
        setLayout(null);

        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Point(0, 0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEnemies(g);
        moveEnemies(g);
    }

    public void drawEnemies(Graphics g) {
        g.setColor(Color.RED);

        for (int i = 0; i < enemies.length; i++) {
            g.fillOval(enemies[i].x, enemies[i].y, 50, 50);
        }
    }

    public void moveEnemies(Graphics g) {

    }
}