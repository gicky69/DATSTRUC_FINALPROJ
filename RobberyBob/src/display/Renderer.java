package display;

import entity.Player;
import game.Game;

import java.awt.*;

public class Renderer {
    Camera camera;
    Player player;

    public void render(Game game, Graphics graphics, Player player) {
        this.camera = game.getCamera();
        this.player = player;

        // Draw the Player's sprite
        game.getGameObjects().forEach(gameObject -> {
            graphics.drawImage(gameObject.getSprite(),
                    gameObject.getPosition().getX() - camera.getPosition().getX() - 30,
                    gameObject.getPosition().getY() - camera.getPosition().getY() - 40,
                    null
            );

            // draw player position
            graphics.setColor(Color.BLACK);
            graphics.drawString("Player Position: ",20,50);
            graphics.drawString("X: " + player.getPosition().getX() +" Y: "
                    + player.getPosition().getY(),20,70);

            // display item collection state
            graphics.drawString("Item Collection Status: " + player.itemCollected,20,90);

            // draw player footstep radius
            player.getFootstep().draw((Graphics2D) graphics, player.getPosition());

            // Draw the player's hitbox for debugging
            if (player != null) {
                graphics.setColor(Color.RED);
                Rectangle hitbox = player.getHitbox();
                graphics.drawRect(hitbox.x - camera.getPosition().getX()-30, hitbox.y - camera.getPosition().getY()-40, hitbox.width, hitbox.height);
            }

        });


    }

    public void renderMap(GamePanel gamePanel, Graphics2D g2) {
        gamePanel.tileManager.draw(this, g2);
    }
}