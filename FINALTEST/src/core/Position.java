package core;

public class Position {

    public static int PROXIMITY_RANGE = 5;

    private float x;
    private float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        int intx = Math.round(x);
        return intx;
    }

    public int getY() {
        int inty = Math.round(y);
        return inty;
    }

    public int gridX() {
        return (int) x / 40;
    }

    public int gridY() {
        return (int) y / 40;
    }

    public static Position copyOf(Position position) {
        return new Position(position.getX(), position.getY());
    }

    public static Position ofGridPosition(int gridX, int gridY) {
        return new Position(gridX * 40 + 40 / 2, gridY * 40 + 40 / 2);
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

    public boolean isInRangeOf(Position position) {
        return Math.abs(x - position.getfX()) < PROXIMITY_RANGE && Math.abs(y - position.getfY()) < PROXIMITY_RANGE;
    }

    public void setfX(float v) {
        x = v;
    }

    public void setfY(float v) {
        y = v;
    }
}
