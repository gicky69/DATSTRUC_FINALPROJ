import java.util.List;
import java.util.ArrayList;

public class Game {
    private Frame frame;
    private List<GameObject> gameObjects;

    public Game(int width, int height) {
        frame = new Frame(width, height);
        gameObjects = new ArrayList<>();
    }

    public void update() {

    }

    public void render() {

    }
}
