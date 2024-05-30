package display;

import entity.Enemy;
import entity.Player;
import input.KeyInputs;
import game.Game;
import entity.GameObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GamePanel extends JFrame {

    TileManager tileManager;
    public Player player;

    public final int screenWidth = 1600;
    public final int screenHeight = 1000;
    public final int tileSize = 40;
    public final int maxScreenCol = screenWidth/tileSize;
    public int maxScreenRow = screenHeight/tileSize;

    // world settings
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public GamePanel(int width, int height, KeyInputs input) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setBackground(Color.GRAY);
        this.setVisible(true);
        this.createBufferStrategy(2);

        this.addKeyListener(input);

        tileManager = new TileManager(this);

        setFocusable(true);
        requestFocusInWindow();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void render(Game game) {
        // Draw the Player's sprite
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0,0, getWidth(), getHeight());
        tileManager.draw(g2);

        // Drawing the game object's sprites.
        for (GameObject gameObject : game.getGameObjects()) {

            if (gameObject instanceof Player) {
                g2.drawImage(gameObject.getSprite(), player.getScreenPosition().getX(), player.getScreenPosition().getY(), null);
            } else {
                g2.drawImage(gameObject.getSprite(), gameObject.getPosition().getX(), gameObject.getPosition().getY(), null);
                if (gameObject instanceof Enemy) {
                    ((Enemy) gameObject).drawFOV(g2);
                }
            }

        }
        g2.dispose();
        bufferStrategy.show();
    }

}