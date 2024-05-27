package tile;

import display.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] tileMap;


    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[2];
        tileMap = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        getTileImage();
        loadMap();
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

    // CountDownLatch is used to wait for the image to load before drawing it. This is to prevent null pointer exception.
    private final CountDownLatch latch = new CountDownLatch(1);

    public void getTileImage() {
        // Swing Worker is used to load the images in the background, bypassing the EDT or Event Dispatch Thread ran on the main thread.
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    for (int i = 0; i < tile.length; i++) {
                        tile[i] = new Tile();
                        tile[i].image = ImageIO.read(getClass().getResourceAsStream("/Tile/Tile" + i + ".png"));
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
                        System.out.print(tileMap[row][col] + " ");
                    }
                    System.out.println();
                }
                return null;
            }

            //
            @Override
            protected void done() {
                latch.countDown(); // to signal code that SwingWorker has finished loading the images.
            }

        };
        worker.execute();
    }

    public void draw(Graphics2D g2) {
        try {
            latch.await(); // to  wait until the latch counts to 0, then this will run.
            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                int worldX = col * gamePanel.tileSize;
                int worldY = row * gamePanel.tileSize;
                int screenX = worldX - gamePanel.player.getWorldPosition().getX() + gamePanel.player.getScreenPosition().getX();
                int screenY = worldY - gamePanel.player.getWorldPosition().getY() + gamePanel.player.getScreenPosition().getY();

                g2.drawImage(tile[tileMap[row][col]].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                col++;

                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}