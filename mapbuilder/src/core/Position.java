package core;

public class Position {
    private float x;
    private float y;
    private int ix;
    private int iy;

    public int getIx() {
        return ix;
    }

    public int getIy() {
        return iy;
    }

    public Position (float x, float y) {
        this.x = x;
        this.y = y;
        this.ix = (int) x;
        this.iy = (int) y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
