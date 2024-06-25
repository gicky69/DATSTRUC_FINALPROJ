package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = true;
    public boolean pathable = false;
    public int x;
    public int y;
    public int gridX;
    public int gridY;

    public int length;

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isPathable() {
        return this.pathable;
    }

    //# region Getters
    public int getGridY() {
        return gridY;
    }

    public int getGridX() {
        return gridX;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    //# endregion

    //# region Setters
    public void setGridX(int gridX) {
        this.gridX = gridX * 40; // 40 is the size of the tile
    }

    public void setGridY(int gridY) {
        this.gridY = gridY * 40;
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public void setPosY(int y) {
        this.y = y;
    }
    //# endregion
}
