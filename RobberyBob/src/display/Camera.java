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
    private int width;
    private int height;

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

    public void update(Game game, GamePanel gamePanel) {
        if (ObjectWithFocus.isPresent()) {
            Position objectPosition = ObjectWithFocus.get().getPosition();

            int screenX = objectPosition.getX() - windowSize.getWidth() / 2;
            int screenY = objectPosition.getY() - windowSize.getHeight() / 2;

            this.position.setX(screenX);
            this.position.setY(screenY);

            limitWithinBounds(game, gamePanel);

        }

    }

    public void limitWithinBounds(Game game, GamePanel gamePanel) {
        int worldWidth = gamePanel.getWorldWidth();
        int worldHeight = gamePanel.getWorldHeight();

        // Continue updating camera position even when it reaches the map border
        if (position.getX() < 0) {
            position.setX(0);
        } else if (position.getX() + windowSize.getWidth() > worldWidth) {
            position.setX(worldWidth - windowSize.getWidth());
        }

        if (position.getY() < 0) {
            position.setY(0);
        } else if (position.getY() + windowSize.getHeight() > worldHeight) {
            position.setY(worldHeight - windowSize.getHeight());
        }
    }
}
