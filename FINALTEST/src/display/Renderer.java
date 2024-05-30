package display;

import game.Game;

import java.awt.*;


public class Renderer {
    Camera camera;

    public void render(Game game, Graphics graphics) {
        this.camera = game.getCamera();

        // Draw the Player's sprite
        game.getGameObjects().forEach(gameObject -> graphics.drawImage(gameObject.getSprite(),
                gameObject.getPosition().getX() - camera.getPosition().getX() - 64,
                gameObject.getPosition().getY() - camera.getPosition().getY() - 64,
                null
        ));
    }

    public void renderMap(GamePanel gamePanel, Graphics2D g2) {
        gamePanel.tileManager.draw(this, g2);
    }
}
