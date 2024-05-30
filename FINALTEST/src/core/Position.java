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
