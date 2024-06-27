package display;

import entity.Player;
import game.Game;

import javax.swing.*;
import java.awt.*;

public class Renderer {
    Camera camera;
    Player player;
    private boolean showHitbox = true;

    public Renderer() {
        Timer blinkTimer = new Timer(500, e -> showHitbox = !showHitbox);
        blinkTimer.start();

        // Create a Timer that stops the blinkTimer and hides the hitbox after 5 seconds
        new Timer(4000, e -> {
            blinkTimer.stop();
            showHitbox = false;
        }).start();
    }

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

            // draw status bar
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("DePixel", Font.PLAIN, 15));
            graphics.drawString("Stamina: " + player.stamina,(int) (screenWidth/1.1),(int) (screenHeight/5));
            graphics.drawString("Items Collected: " + player.itemsCollected,(int) (screenWidth/1.14),(int) (screenHeight/5)+20);


//            graphics.drawString("Player Coords" + player.getPosition().getX() + ", " + player.getPosition().getY(),
//                    (int) (screenWidth/2),(int) (screenHeight/8)+40);

            // draw player footstep radius
            player.getFootstep().draw((Graphics2D) graphics, player.getPosition(), game);

            // Draw the player's hitbox for debugging
            if (player != null && showHitbox) {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(2));
                Rectangle hitbox = player.getHitbox();
                graphics.drawRect(hitbox.x - camera.getPosition().getX()-30, hitbox.y - camera.getPosition().getY()-40, hitbox.width, hitbox.height);
            }

        });


    }

    public void renderMap(GamePanel gamePanel, Graphics2D g2) {
        gamePanel.tileManager.draw(this, g2);
    }
}
