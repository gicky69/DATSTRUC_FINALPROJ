import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyHandler implements KeyListener {

    public boolean up, down, left, right;
    public boolean lastDirectionRight = true; // Assuming the default direction is right

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = true;
            lastDirectionRight = false; // Reset the last direction when a new direction is pressed
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = true;
            lastDirectionRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = true;
            lastDirectionRight = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = true;
            lastDirectionRight = true;
        }
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

    enemyTEST enemy = new enemyTEST();

    // initialize moving object (Ame)
    ImageIcon myAmeL, myAmeR, myAmeU, myAmeD, myAmeDefaultR, myAmeDefaultL;

    public GamePanel() {

        myAmeDefaultR = new ImageIcon("FINALTEST/images/GamePanel/MC_Default_Right-GamePanel.gif");
        myAmeDefaultL = new ImageIcon("FINALTEST/images/GamePanel/MC_Default_Left-GamePanel.gif");
        myAmeL = new ImageIcon("FINALTEST/images/GamePanel/MC_Left-GamePanel.gif");
        myAmeR = new ImageIcon("FINALTEST/images/GamePanel/MC_Right-GamePanel.gif");
        myAmeU = new ImageIcon("FINALTEST/images/GamePanel/MC_UP-GamePanel.gif");
        myAmeD = new ImageIcon("FINALTEST/images/GamePanel/MC_Down-GamePanel.gif");

        this.setLayout(null);
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setDoubleBuffered(true);
        this.setBackground(Color.GRAY);
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
        double drawInterval = 1000000000/60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (GameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                this.repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Eto yung player for green square
        /*g2d.setColor(Color.GREEN);
        g2d.fillRect(playerX, playerY, 50, 50);*/

        //Ame on Idle:
        if (!keyHandler.up && !keyHandler.down && !keyHandler.left && !keyHandler.right){
            if (keyHandler.lastDirectionRight) {
                g2d.drawImage(myAmeDefaultR.getImage(), playerX, playerY, null);
            } else {
                g2d.drawImage(myAmeDefaultL.getImage(), playerX, playerY, null);
            }
        } else {
            // Implementing movingAme:
            if (keyHandler.up){
                g2d.drawImage(myAmeU.getImage(), playerX, playerY, null);
            } else if (keyHandler.down) {
                g2d.drawImage(myAmeD.getImage(), playerX, playerY, null);
            } else if (keyHandler.left) {
                g2d.drawImage(myAmeL.getImage(), playerX, playerY, null);
            } else if (keyHandler.right) {
                g2d.drawImage(myAmeR.getImage(), playerX, playerY, null);
            }
        }

        // Eto yung enemy
        enemy.update(g, this);
    }
}