package core;

public class Position {

    public static int PROXIMITY_RANGE = 5;

    private float x;
    private float y;
    private int ix;
    private int iy;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
        this.ix = Math.round(x);
        this.iy = Math.round(y);
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
//        System.out.println("Current Position " + getfX() + " " + getfY());
        //System.out.println("Target Position " + position.getfX() + " " + position.getfY());
        boolean inRange = Math.abs(x - position.getfX()) < PROXIMITY_RANGE && Math.abs(y - position.getfY()) < PROXIMITY_RANGE;
//        System.out.println("In range: " + inRange);
        return inRange;
        //
    }

    public void setfX(float v) {
        x = v;
    }

    public void setfY(float v) {
        y = v;
    }
}
