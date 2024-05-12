import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyHandler implements KeyListener {

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) up = true;
        if (e.getKeyCode() == KeyEvent.VK_S) down = true;
        if (e.getKeyCode() == KeyEvent.VK_A) left = true;
        if (e.getKeyCode() == KeyEvent.VK_D) right = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) up = false;
        if (e.getKeyCode() == KeyEvent.VK_S) down = false;
        if (e.getKeyCode() == KeyEvent.VK_A) left = false;
        if (e.getKeyCode() == KeyEvent.VK_D) right = false;
    }
}

public class GamePanel extends JPanel implements Runnable {
    JPanel GamePanel;
    Thread GameThread;
    KeyHandler keyHandler = new KeyHandler();
    int playerSpeed = 5;
    int playerX = 100;
    int playerY = 100;

    public GamePanel() {

        this.setLayout(null);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        start();
    }

    public void start() {
        GameThread = new Thread(this);
        GameThread.start();
    }

    public void update() {
        if (keyHandler.up) {
            playerY -= playerSpeed;
        }
        if (keyHandler.down) {
            playerY += playerSpeed;
        }
        if (keyHandler.left) {
            playerX -= playerSpeed;
        }
        if (keyHandler.right) {
            playerX += playerSpeed;
        }
    }

    @Override
    public void run() {
        while (GameThread != null) {
            update();
            this.repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Eto yung player
        g2d.setColor(Color.RED);
        g2d.fillRect(playerX, playerY, 50, 50);
    }

}