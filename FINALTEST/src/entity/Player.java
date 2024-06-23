package entity;

import controller.Controller;
import core.Position;


import javax.swing.*;
import java.awt.*;
import display.GamePanel;
import display.ImageLoader;
import display.SubPanels;

public class Player extends GameObject {

    //#region Design and Animation Init

    ImageIcon myAmeL, myAmeR, myAmeU, myAmeD, myAmeDefaultR, myAmeDefaultL;

    //#endregion

    //#region Player Init

    private Controller controller;
    private GamePanel gamePanel;
    public boolean itemCollected = false;
    private SubPanels subPanels;
    private Image[][] frames;
    private ImageLoader imageLoader;
    private int currentFrameIndex = 0;
    private int currentDirectionIndex = 0;
    private long lastFrameTime = System.currentTimeMillis();
    private long frameDelay = 120; // milli

    private Rectangle hitbox;

    public Player(Position pos, Controller controller, GamePanel gamePanel, SubPanels subPanels, ImageLoader imageLoader) {

        super();
        this.position = pos;
        this.controller = controller;
        this.gamePanel = gamePanel;
        this.subPanels = subPanels;
        this.imageLoader = imageLoader;

        frames = imageLoader.loadSpriteSheet("player", 48, 64, 4, 4);
        hitbox = new Rectangle(position.getX(), position.getY(), frames[0][0].getWidth(null), frames[0][0].getHeight(null));

    }

    //#endregion

    @Override
    public void update() {

        // Update the player's state and frame
        int deltaX = 0;
        int deltaY = 0;

        int oldPosX = position.getX();
        int oldPosY = position.getY();

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay) {
            currentFrameIndex = (currentFrameIndex + 1) % frames[currentDirectionIndex].length;
            lastFrameTime = currentTime;
        }

        if (controller.isRequestingUp()) {
            direction = "up";
            deltaY -= entitySpeed;
            currentDirectionIndex = 2;
        } if (controller.isRequestingDown()) {
            direction = "down";
            deltaY += entitySpeed;
            currentDirectionIndex = 0;
        } if (controller.isRequestingLeft()) {
            direction = "left";
            deltaX -= entitySpeed;
            currentDirectionIndex = 1;
        } if (controller.isRequestingRight()) {
            direction = "right";
            deltaX += entitySpeed;
            currentDirectionIndex = 3;
        } if (!controller.isRequestingUp() && !controller.isRequestingDown() && !controller.isRequestingLeft() && !controller.isRequestingRight()) {
            deltaX = 0;
            deltaY = 0;
            currentFrameIndex = 0;
        } if (controller.isSprinting()) {
            entitySpeed = 5;
        } else {
            entitySpeed = 3;
        } if (controller.isSneaking()) {
            entitySpeed = 1.5;
            frameDelay = 350;
        } if (controller.isPaused()) {
            game.togglePause();
        }

        hitbox.setLocation(position.getX(), position.getY());

        // Normalize speed if more than one key is pressed
        double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (entitySpeed < 5) {
            if (length > entitySpeed) {
                deltaX = (int) (deltaX / length * entitySpeed);
                deltaY = (int) (deltaY / length * entitySpeed);
            }
        }

        position = new Position(position.getX() + deltaX, position.getY());

        collisionOn = false;
        game.entityCollision.tileChecker(game.getGameObjects());
        if (collisionOn) {
            position = new Position(oldPosX, position.getY());
        }

        position = new Position(position.getX(), position.getY() + deltaY);

        collisionOn = false;
        game.entityCollision.tileChecker(game.getGameObjects());
        if (collisionOn) {
            position = new Position(position.getX(), oldPosY);
        }


        finishStatus();

    }

    @Override
    public Image getSprite() {
        return frames[currentDirectionIndex][currentFrameIndex];
    }

    public void finishStatus() {
        // get player's position divided by the tile size
        int playerTileX = position.getX() / gamePanel.tileSize;
        int playerTileY = position.getY() / gamePanel.tileSize;

        // 2D array, will check if player's position is on finish part and if item is collected
        if (gamePanel.tileManager.tileMap[playerTileY][playerTileX] == 3 && itemCollected) {
            subPanels.roundOver = true;
            System.out.println("ROUND OVER");
            gamePanel.roundPanel.updateDisplay();
            subPanels.setRoundOverPanel(gamePanel, game, gamePanel.roundPanel);
            subPanels.roundOverPanel.setVisible(true);

            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Controller getController() {
        return controller;
    }

}