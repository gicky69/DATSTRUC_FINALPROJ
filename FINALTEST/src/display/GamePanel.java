package display;

import input.KeyInputs;
import game.Game;
import entity.GameObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GamePanel extends JFrame {

    TileManager tileManager;

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

    public void render(Game game) {
        // Draw the Player's sprite
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0,0, getWidth(), getHeight());
        tileManager.draw(g2);

        // Drawing the game object's sprites.
        for (GameObject gameObject : game.getGameObjects()) {
            g2.drawImage(gameObject.getSprite(), gameObject.getPosition().getX(), gameObject.getPosition().getY(), null);
        }
        g2.dispose();
        bufferStrategy.show();
    }
}