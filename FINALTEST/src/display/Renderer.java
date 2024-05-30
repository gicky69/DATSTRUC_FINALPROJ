package display;

import game.Game;

import java.awt.*;


public class Renderer {

    public void render(Game game, Graphics graphics) {
        Camera camera = game.getCamera();

        // Draw the Player's sprite
        game.getGameObjects().forEach(gameObject -> graphics.drawImage(gameObject.getSprite(),
                gameObject.getPosition().getX() - camera.getPosition().getX() - 32,
                gameObject.getPosition().getY() - camera.getPosition().getY() - 32,
                null
        ));
    }

    public void renderMap(GamePanel gamePanel, Graphics2D g2) {
        gamePanel.tileManager.draw(g2);
    }
}
