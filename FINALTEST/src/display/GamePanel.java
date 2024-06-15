package display;

import entity.Player;
import input.KeyInputs;
import game.Game;
import menu.RoundPanel;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferStrategy;


public class GamePanel extends JFrame {
    private final Game game;
    public Frame menuFrame;
    private SubPanels subPanels;
    public TileManager tileManager;
    public RoundPanel roundPanel;
    public Player player;

    private Renderer renderer;
    public Camera camera;

    // screen settings
    public final int screenWidth = 1600;
    public final int screenHeight = 1000;
    public final int tileSize = 40;

    // world settings
    public final int SPRITE_SIZE = 40;
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //panels and boolean
    public boolean roundOver = false;

    public GamePanel(int width, int height, KeyInputs input, Camera camera, Game game, SubPanels subPanels, RoundPanel roundPanel) {
        this.subPanels = subPanels;
        this.roundPanel = roundPanel;
        this.camera = camera;
        this.game = game;
        this.renderer = new Renderer();
        tileManager = new TileManager(this, subPanels, roundPanel);
        subPanels.setPausePanel(this, game);
        subPanels.setRoundOverPanel(this, game, roundPanel);

        // frame settings
        this.setLayout(null);
        this.setPreferredSize(new Dimension(width, height));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.createBufferStrategy(2);
        this.addKeyListener(input);
        setBackground(Color.GRAY);
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
//        tileManager.draw(g2);

        // Drawing the game object's sprites.
//        for (GameObject gameObject : game.getGameObjects()) {
//
//            if (gameObject instanceof Player) {
//                g2.drawImage(gameObject.getSprite(), player.getPosition().getX(), player.getPosition().getY(), null);
//            } else {
//                if (gameObject instanceof Enemy) {
//                    g2.drawImage(gameObject.getSprite(), gameObject.getPosition().getX(), gameObject.getPosition().getY(), null);
//                    ((Enemy) gameObject).drawFOV(g2);
//                }
//            }
//
//        }

        renderer.renderMap(this, g2);
        renderer.render(game, g2, player);

        g2.dispose();
        bufferStrategy.show();
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Player getPlayer() {
        return player;
    }
}