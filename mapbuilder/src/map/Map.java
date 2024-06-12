package map;

import display.Display;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

public class Map {

    private Display display;
    public Tile[] tile;
    public int[][] tileMap;
    public List<Point> clickTiles;

    private String selectedMap;

    public Map(Display display) {
        this.display = display;
        tile = new Tile[2];
        tileMap = new int[40][80];
        clickTiles = new ArrayList<>();

        Graphics g = display.getBufferStrategy().getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 40, 40);
    }

    public void draw(Graphics g2) {
        int playerPosX = (int)display.player.getPosition().getX();
        int playerPosY = (int)display.player.getPosition().getY();
        Point point = new Point();

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 80; j++) {
                int worldX = j * 40 - playerPosX;
                int worldY = i * 40 - playerPosY;

                point.setLocation(worldX, worldY);
                clickTiles.add(point);

                switch (tileMap[i][j]) {
                    case 0:
                        g2.setColor(Color.BLACK);
                        g2.fillRect(worldX, worldY, 40, 40);
                        break;
                    case 1:
                        g2.setColor(Color.GREEN);
                        g2.drawRect(worldX, worldY, 40, 40);
                        break;
                    case 2:
                        g2.setColor(Color.BLACK);
                        g2.fillRect(worldX, worldY, 40, 40);
                        break;
                    case 3:
                        g2.setColor(Color.ORANGE);
                        g2.fillRect(worldX, worldY, 40, 40);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public File createMap() {
        int count = 0;
        File file;
        do {
            String filename = "resources/maps/map" + count + ".txt";
            file = new File(filename);
            count++;
        } while (file.exists());

        selectedMap = file.getPath();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 80; j++) {
                    writer.write("1 ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadDirectly(selectedMap);

        Graphics g = display.getBufferStrategy().getDrawGraphics();
        draw(g);

        return file;
    }

    public void saveMap() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedMap, false))) {
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 80; j++) {
                    writer.write(tileMap[i][j] + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDirectly(String mapPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(mapPath))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                for (int j = 0; j < values.length; j++) {
                    tileMap[i][j] = Integer.parseInt(values[j]);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("resources/maps"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedMap = selectedFile.getPath();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedMap))) {
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(" ");
                    for (int j = 0; j < values.length; j++) {
                        tileMap[i][j] = Integer.parseInt(values[j]);
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}