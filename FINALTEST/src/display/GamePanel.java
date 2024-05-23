package display;

import input.KeyInputs;
import game.Game;
import entity.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GamePanel extends JFrame {
    public GamePanel(int width, int height, KeyInputs input) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setBackground(Color.GRAY);
        this.setVisible(true);
        this.createBufferStrategy(2);

        this.addKeyListener(input);

        setFocusable(true);
        requestFocusInWindow();
    }

    public void render(Game game) {
        // Draw the Player's sprite
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();

        g.clearRect(0,0, getWidth(), getHeight());
        // Drawing the game object's sprites.
        for (GameObject gameObject : game.getGameObjects()) {
            g.drawImage(gameObject.getSprite(), gameObject.getPosition().getX(), gameObject.getPosition().getY(), null);
        }
        g.dispose();
        bufferStrategy.show();
    }
}