package core;

public class Lines {
    private Position point1;
    private Position point2;

    public Lines(Position p1, Position p2) {
        this.point1 = p1;
        this.point2 = p2;
    }

    public Position getPoint1() {
        return point1;
    }

    public Position getPoint2() {
        return point2;
    }
}
