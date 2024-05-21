import java.awt.*;

public abstract class GameObject {
    private Position position;
    private Size size;

    public GameObject() {
        position = new Position(0,0);
        size = new Size(64, 64);
    }

    public abstract void update();
    public abstract Image getSprite();
}
