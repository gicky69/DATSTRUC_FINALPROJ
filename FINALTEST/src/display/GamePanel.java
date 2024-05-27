package display;

import input.KeyInputs;
import game.Game;
import entity.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GamePanel extends JFrame {

    private Renderer renderer;

    public GamePanel(int width, int height, KeyInputs input) {
        setLayout(null);
        setResizable(false);

        this.renderer = new Renderer();

        setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setBackground(Color.GRAY);

        setVisible(true);
        createBufferStrategy(2);

        addKeyListener(input);

        setFocusable(true);
        requestFocusInWindow();
    }

    public void render(Game game) {
        // Draw the Player's sprite
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();

        g.clearRect(0,0, getWidth(), getHeight());

        renderer.render(game, g);

//        game.getGameObjects().forEach(gameObject -> g.drawImage(gameObject.getSprite(),
//                gameObject.getPosition().getX(),
//                gameObject.getPosition().getY(),
//                null
//        ));

//        for (GameObject gameObject : game.getGameObjects()) {
//            g.drawImage(gameObject.getSprite(), gameObject.getPosition().getX(), gameObject.getPosition().getY(), null);
//        }
        g.dispose();
        bufferStrategy.show();
    }
}