package display;

import entity.Player;
import game.Game;

import java.awt.*;

public class Renderer {
    Camera camera;

    public void render(Game game, Graphics graphics) {
        this.camera = game.getCamera();

        // Draw the Player's sprite
        game.getGameObjects().forEach(gameObject -> {
            graphics.drawImage(gameObject.getSprite(),
                    gameObject.getPosition().getX() - camera.getPosition().getX() - 64,
                    gameObject.getPosition().getY() - camera.getPosition().getY() - 64,
                    null
            );

            // Draw a rectangle on the player
            if (gameObject instanceof Player) {
                graphics.setColor(Color.RED);
                graphics.drawRect(
                        gameObject.getPosition().getX() - camera.getPosition().getX() - 32,
                        gameObject.getPosition().getY() - camera.getPosition().getY() - 32,
                        10,
                        10);

                // Print the rectangle (player's) position
                System.out.println("Rectangle Position: X = " + gameObject.getPosition().getX() + ", Y = "
                        + gameObject.getPosition().getY());

            }
        });
    }

    public void renderMap(GamePanel gamePanel, Graphics2D g2) {
        gamePanel.tileManager.draw(this, g2);
    }
}
