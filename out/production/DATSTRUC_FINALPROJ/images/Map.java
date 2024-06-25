package map;

import core.Position;
import paint.PaintPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {

    public List<Point> points = new ArrayList<>();
    private PaintPanel paintPanel;
private Position cameraPosition;

    public Map(PaintPanel PaintPanel) {
        this.paintPanel = PaintPanel;
        cameraPosition = PaintPanel.player.game.getCamera().getPosition();
        for (int row=0;row<1600;row += 40) {
            for (int col=0;col<900;col += 40) {
                // Draw the map
                points.add(new Point(row, col));
            }
        }
    }

    public void draw(Graphics2D g2) {
        // Draw the map
        for (Point point : points) {
            try {
                BufferedImage img = ImageIO.read(getClass().getResourceAsStream("/Tile/Tile1.png"));
                int drawX = point.x - (int)cameraPosition.getX();
                int drawY = point.y - (int)cameraPosition.getY();

                g2.drawImage(img, drawX, drawY, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
