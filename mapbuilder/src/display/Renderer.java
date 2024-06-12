package display;

import entity.Player;
import game.Game;
import map.Map;

import java.awt.*;

public class Renderer {
    Camera camera;
    private Map map;

    public void render(Game game, Graphics graphics, Player player) {
        this.camera = game.getCamera();
    }

    public void renderMap(Display display, Graphics2D g2) {
        display.map.draw(g2);
    }
}
