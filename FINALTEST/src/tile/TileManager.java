package tile;

import display.GamePanel;
import display.Renderer;
import display.SubPanels;
import menu.RoundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.concurrent.CountDownLatch;

public class TileManager {
    GamePanel gamePanel;
    SubPanels subPanels;
    public Tile[] tile;
    public int[][] tileMap;
    public RoundPanel roundPanel;
    // check only

    public TileManager(GamePanel gamePanel, SubPanels subPanels, RoundPanel roundPanel) {
        this.gamePanel = gamePanel;
        this.subPanels = subPanels;
        this.roundPanel = roundPanel;

        tile = new Tile[4];
        tileMap = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        getTileImage();

        System.out.println("FROM TILEMANAGER: " + roundPanel.roundDetail );
        loadMap("FINALTEST/resources/Map/map" + roundPanel.roundDetail +".txt");
    }

    // map is imported from txt.
    public void loadMap(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (int col = 0; col < tokens.length; col++) {
                    tileMap[row][col] = Integer.parseInt(tokens[col]);
//                    tileMap[row][col] = Character.getNumericValue(line.charAt(col));
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
                        tile[i].setLength(40);
                        if (tile[i].image == null) {
                            System.out.println("Image not loaded");
                        } else {
                            System.out.println("Image loaded successfully");
                        }

                        if (i == 0) {
                            tile[i].collision = true;
                            tile[i].pathable = false;
                        } else if (i == 2){
                            tile[i].collision = true;
                            tile[i].pathable = false;
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

    public void draw(Renderer renderer, Graphics g2) {
        try {
            latch.await(); // to  wait until the latch counts to 0, then this will run.
            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                int worldX = col * gamePanel.tileSize - gamePanel.camera.getPosition().getX();
                int worldY = row * gamePanel.tileSize - gamePanel.camera.getPosition().getY();

//                // Only adjust positions relative to the camera if the camera is within the map boundaries
//                if (gamePanel.camera.getPosition().getX() > 0 && gamePanel.camera.getPosition().getX() < gamePanel.getWorldWidth() - gamePanel.screenWidth) {
//                    worldX -= gamePanel.player.getPosition().getX() - gamePanel.screenWidth / 2;
//                }
//                if (gamePanel.camera.getPosition().getY() > 0 && gamePanel.camera.getPosition().getY() < gamePanel.getWorldHeight() - gamePanel.screenHeight) {
//                    worldY -= gamePanel.player.getPosition().getY() - gamePanel.screenHeight / 2;
//                }

                g2.drawImage(tile[tileMap[row][col]].image, worldX, worldY, null);
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

    //# region Getters

    // get Map
    public int[][] getMap() {
        return tileMap;
    }
    //# endregion
}