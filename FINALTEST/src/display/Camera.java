package display;

import core.Position;
import core.Size;
import entity.GameObject;
import game.Game;

import java.util.Optional;

public class Camera {
    private Position position;
    private Size windowSize;

    private Optional<GameObject> ObjectWithFocus;

    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
    }

    public Position getPosition() {
        return position;
    }

    public void focusOn(GameObject object) {
        this.ObjectWithFocus = Optional.of(object);
    }

    public void update(Game game) {
        if (ObjectWithFocus.isPresent()) {
            Position objectPosition = ObjectWithFocus.get().getPosition();

            this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - windowSize.getHeight() / 2);
        }
    }
}
