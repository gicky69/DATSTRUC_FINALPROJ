package display;

import entity.Player;
import input.KeyInputs;
import game.Game;
import menu.RoundPanel;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;



public class GamePanel extends JFrame {
    public final Game game;
    public Frame menuFrame;
    private SubPanels subPanels;
    public TileManager tileManager;
    public RoundPanel roundPanel;
    public Player player;

    private Renderer renderer;
    public Camera camera;
    public ImageLoader imageLoader;

    // screen settings
    public final int tileSize = 40;

    // world settings
    public final int SPRITE_SIZE = 40;
    public final int maxWorldCol = 80;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public GamePanel(int width, int height, KeyInputs input, Camera camera, Game game,
                     SubPanels subPanels, RoundPanel roundPanel, ImageLoader imageLoader) {
        this.subPanels = subPanels;
        this.roundPanel = roundPanel;
        this.camera = camera;
        this.game = game;
        this.renderer = new Renderer();
        this.imageLoader = imageLoader;
        tileManager = new TileManager(this, subPanels, roundPanel);
        subPanels.setPausePanel(this, game);

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