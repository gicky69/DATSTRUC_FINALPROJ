package tile;

import display.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] tileMap;


    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        tileMap = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        loadMap();
        getTileImage();
    }


    // map is imported from txt.
    public void loadMap() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("FINALTEST/resources/Map/map1.txt"));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (int col = 0; col < tokens.length; col++) {
                    tileMap[row][col] = Integer.parseInt(tokens[col]);
                }
                row++;
            }
            System.out.println("MAP LOADED SUCCESSFULLY");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getTileImage() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    tile[0] = new Tile();
                    tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tile/GreenTile.png"));

                    tile[1] = new Tile();
                    tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tile/GreenTile.png"));

                    for (int i = 0; i < 2; i++) {
                        if (tile[i].image == null) {
                            System.out.println("Image not loaded");
                        } else {
                            System.out.println("Image loaded successfully");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int row = 0; row < tileMap.length; row++) {
                    for (int col = 0; col < tileMap[row].length; col++) {
                        //System.out.print(tileMap[row][col] + " ");
                    }
                    //System.out.println();
                }
                return null;
            }

        };
        worker.execute();
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;

        while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            int worldX = col * gamePanel.tileSize;
            int worldY = row * gamePanel.tileSize;
            int screenX = worldX-gamePanel.player.getWorldPosition().getX() + gamePanel.player.getScreenPosition().getX();
            int screenY = worldY-gamePanel.player.getWorldPosition().getY() + gamePanel.player.getScreenPosition().getY();

            //System.out.print(tileMap[row][col]);

            // to be changed
            g2.drawImage(tile[0].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;

            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
}
