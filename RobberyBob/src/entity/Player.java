package entity;

import controller.Controller;
import core.Position;


import javax.swing.*;
import java.awt.*;

import core.gameplay.footstep;
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
    public int itemsCollected;
    private SubPanels subPanels;
    private Image[][] frames;
    public ImageLoader imageLoader;
    private Rectangle hitbox;

    public Player(Position pos, Controller controller, GamePanel gamePanel, SubPanels subPanels) {

        super();
        this.position = pos;
        this.controller = controller;
        this.gamePanel = gamePanel;
        this.subPanels = subPanels;
        imageLoader = new ImageLoader();

        // Initialize Footstep
        footstep = new footstep(0f, 0f);

        imageLoader.loadImage("player");
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
        if (currentTime - imageLoader.lastFrameTime >= imageLoader.frameDelay) {
            imageLoader.currentFrameIndex = (imageLoader.currentFrameIndex + 1) % frames[imageLoader.currentDirectionIndex].length;
            imageLoader.lastFrameTime = currentTime;
        }

        if (controller.isRequestingUp()) {
            direction = "up";
            deltaY -= entitySpeed;
            imageLoader.currentDirectionIndex = 2;
        } if (controller.isRequestingDown()) {
            direction = "down";
            deltaY += entitySpeed;
            imageLoader.currentDirectionIndex = 0;
        } if (controller.isRequestingLeft()) {
            direction = "left";
            deltaX -= entitySpeed;
            imageLoader.currentDirectionIndex = 1;
        } if (controller.isRequestingRight()) {
            direction = "right";
            deltaX += entitySpeed;
            imageLoader.currentDirectionIndex = 3;
        }

        if (!controller.isRequestingUp() && !controller.isRequestingDown() && !controller.isRequestingLeft() && !controller.isRequestingRight()) {
            deltaX = 0;
            deltaY = 0;
            footstep.setRadius(0.0f);
            footstep.setNoise(0.0f);
            imageLoader.currentFrameIndex = 0;
        }

        if (controller.isSprinting()) {
            footstep.setRadius(5.5f);
            footstep.setNoise(3.5f);
            entitySpeed = 6;
    } else if (deltaX != 0 || deltaY != 0) {
            footstep.setRadius(1.5f);
            footstep.setNoise(1.0f);
            entitySpeed = 4;
        } if (controller.isSneaking()) {
            footstep.setNoise(0.5f);
            footstep.setRadius(0.5f);
            entitySpeed = 1.5;
            imageLoader.frameDelay = 350;
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

        System.out.println("Footstep: " + footstep.getRadius() + ", " + footstep.getNoise());

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

    // is used for renderer class
    @Override
    public Image getSprite() {
        return frames[imageLoader.currentDirectionIndex][imageLoader.currentFrameIndex];
    }

    public void finishStatus() {
        // get player's position divided by the tile size
        int playerTileX = position.getX() / gamePanel.tileSize;
        int playerTileY = position.getY() / gamePanel.tileSize;

        // 2D array, will check if player's position is on finish part and if item is collected
        if (gamePanel.tileManager.tileMap[playerTileY][playerTileX] == 3 && itemCollected) {
            subPanels.roundOver = true;
            System.out.println("ROUND OVER");
            System.out.println("Items collected: " + itemsCollected);
            gamePanel.roundPanel.updateDisplay();
            subPanels.setRoundOverPanel(gamePanel, game, gamePanel.roundPanel, this);
            subPanels.roundOverPanel.setVisible(true);

            gamePanel.revalidate();
            gamePanel.repaint();
        }

        // if enemy has caught player
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Controller getController() {
        return controller;
    }

    // get Footstep
    public footstep getFootstep() {
        return footstep;
    }

}