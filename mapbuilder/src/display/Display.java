package display;

import entity.Player;
import input.KeyInput;
import map.Map;
import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Display extends JFrame {

    public Map map;
    private Renderer renderer;
    public Player player;

    public final int screenWidth = 1600;
    public final int screenHeight = 1000;

    public Display(int swidth, int sheight, KeyInput keyInput) {
        setTitle("MTest");
        setLayout(null);
        setPreferredSize(new Dimension(swidth, sheight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        setBackground(Color.GRAY);
        setResizable(false);
        setVisible(true);
        createBufferStrategy(2);

        this.renderer = new Renderer();
        map = new Map(this);

        addKeyListener(keyInput);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tileClick(e);
            }
        };

        addMouseListener(mouseAdapter);
        setFocusable(true);
        requestFocusInWindow();
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void render(Game game) {
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g2.clearRect(0, 0, screenWidth, screenHeight);

        renderer.renderMap(this, g2);
        renderer.render(game, g2, player);

        g.dispose();
        bufferStrategy.show();
    }

    public void addTile(MouseEvent e, int tileNum) {
        int playerPosX = (int)player.getPosition().getX();
        int playerPosY = (int)player.getPosition().getY();

        int mouseX = e.getX() + playerPosX;
        int mouseY = e.getY() + playerPosY;

        // Convert mouse coordinates to tile coordinates
        int tileX = mouseX / 40;
        int tileY = mouseY / 40;

        // Check if there is a tile at the clicked position
        System.out.println("Tile clicked at: " + tileX + ", " + tileY);
        map.tileMap[tileY][tileX] = tileNum;
    }

    public void tileClick(MouseEvent e) {
        switch(player.lastKeyPressed) {
            case 49:
                addTile(e, 1);
                break;
            case 50:
                addTile(e, 2);
                break;
            case 51:
                addTile(e, 3);
                break;
            default:
                addTile(e, 0);
                break;
        }
    }
}