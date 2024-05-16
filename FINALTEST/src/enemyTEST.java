import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.TimerTask;

public class enemyTEST {
    int enemyX = 800;
    int enemyY = 500;
    int enemyVisionX = 100;
    int enemyVisionY = 50;

    boolean onSight = false;
    boolean isLookingLnR = false;
    boolean isLookingUnD = false;

    boolean lastDirectionR = false;
    boolean lastDirectionL = false;
    boolean lastDirectionU = false;
    boolean lastDirectionD = false;

    boolean moveRight = true;

    public void update(Graphics g, GamePanel gp) {
        drawEnemy(g);
        drawVision(g);
        Find();

        int enemyVisionXRadius = enemyX + enemyVisionX;
    }

    public void Find() {
        int delay = 5000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (moveRight) {
                    int enemySpeed = 3;
                    if (enemyX < 1300) {
                        enemyX += enemySpeed;
                        lastDirectionR = true;
                    } else {
                        moveRight = false;
                        lastDirectionR = false;
                    }
                } else {
                    int enemySpeed = 3;
                    if (enemyX > 500) {
                        enemyX -= enemySpeed;
                        lastDirectionL = true;
                    } else {
                        moveRight = true;
                        lastDirectionL = false;
                    }
                }
            }
        };
        new Timer(delay, taskPerformer).start();
    }

    public void Pursue() {

    }

    public void drawEnemy(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(enemyX, enemyY, 50, 50);
    }

    public void drawVision(Graphics g) {
        g.setColor(Color.RED);
        if (lastDirectionR) {
            enemyVisionX = 100;
            enemyVisionY = 50;
            g.fillRect(enemyX+50, enemyY, enemyVisionX, enemyVisionY);
        } else if (lastDirectionL) {
            enemyVisionX = 100;
            enemyVisionY = 50;
            g.fillRect(enemyX-enemyVisionX, enemyY, enemyVisionX, enemyVisionY);
        }
    }
}
