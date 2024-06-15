package core;

public class Position {
    private float x;
    private float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        int intx = (int)Math.round(x);
        return intx;
    }

    public int getY() {
        int inty = (int)Math.round(y);
        return inty;
    }

    public int gridX() {
        return (int) x / 40;
    }

    public int gridY() {
        return (int) y / 40;
    }

    public static Position ofGridPosition(int gridX, int gridY) {
        return new Position(gridX * 40 + 20, gridY * 40 + 20);
    }

    public float getfX() {
        return x;
    }

    public float getfY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
