package display;

import game.Game;
import map.Map;

import java.awt.*;

public class Renderer {
    Camera camera;
    private Map map;

    public void render(Game game, Graphics graphics) {
        this.camera = game.getCamera();

        // Draw the Player's sprite
    }

    public void renderMap(Graphics2D g2) {

    }
}
