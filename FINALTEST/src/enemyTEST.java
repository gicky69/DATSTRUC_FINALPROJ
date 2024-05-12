import java.awt.*;
import java.util.Random;

public class enemyTEST {
    int enemyX = 800;
    int enemyY = 800;
    int enemySpeed = 5;

    public void update(Graphics g) {
        drawEnemy(g);

    }

    public void drawEnemy(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(enemyX, enemyY, 50, 50);
    }
}
