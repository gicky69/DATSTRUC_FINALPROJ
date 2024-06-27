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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        // Draw the Player's sprite
        game.getGameObjects().forEach(gameObject -> {
            graphics.drawImage(gameObject.getSprite(),
                    gameObject.getPosition().getX() - camera.getPosition().getX()-30,
                    gameObject.getPosition().getY() - camera.getPosition().getY()-30,
                    null
            );

            // draw player position
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("DePixel", Font.PLAIN, 15));
            graphics.drawString("Stamina: " + player.stamina,(int) (screenWidth/1.1),(int) (screenHeight/5));
            graphics.drawString("Items Collected: " + player.itemsCollected,(int) (screenWidth/1.14),(int) (screenHeight/5)+20);


            // draw player footstep radius
            player.getFootstep().draw((Graphics2D) graphics, player.getPosition(), game);

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
