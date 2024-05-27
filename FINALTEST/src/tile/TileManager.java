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
    int tileSize = 40;
    int maxScreenCol = 48;
    int maxScreenRow = 27;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[100];
        tileMap = new int[maxScreenRow][maxScreenCol];
        getTileImage();
        loadMap();
    }

    public void loadMap() {
        String map = "FINALTEST/resources/Map/map1.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(map))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (int col = 0; col < tokens.length; col++) {
                    tileMap[row][col] = Integer.parseInt(tokens[col]);
                }
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void getTileImage() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    tile[0] = new Tile();
                    tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tile/GreenTile.png"));
                    if (tile[0].image == null) {
                        System.out.println("Image not loaded");
                    } else {
                        System.out.println("Image loaded successfully");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                for (int row = 0; row < tileMap.length; row++) {
//                    for (int col = 0; col < tileMap[row].length; col++) {
//                        System.out.print(tileMap[row][col] + " ");
//                    }
//                    System.out.println();
//                }
                return null;
            }

        };
        worker.execute();
    }

    public void draw(Graphics2D g2) {
        int col;
        int row;
        int x = 0;
        int y = 0;

        for (row = 0; row < tileMap.length; row++) {
            for (col = 0; col < tileMap[row].length; col++) {
                int rc = tileMap[row][col];
                if (rc == 1) {
                    g2.drawImage(tile[0].image, x, y, tileSize, tileSize, null);
                }
                x += tileSize;
            }
            y += tileSize;
            x = 0;
        }

        for (row = 0; row < tileMap.length; row++) {
            for (col = 0; col < tileMap[row].length; col++) {
                int rc = tileMap[row][col];
                if (rc == 1) {
                    g2.drawImage(tile[0].image, x, y, tileSize, tileSize, null);
                }
                x += tileSize;
            }
            y += tileSize;
            x = 0;
        }

        //g2.drawImage(tile[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null); // draw the tile image.

    }
}
