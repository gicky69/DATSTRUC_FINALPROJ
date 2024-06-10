package entity;

import controller.Controller;
import core.Position;


import javax.swing.*;
import java.awt.*;
import display.GamePanel;

public class Player extends GameObject {

    //#region Design and Animation Init

    ImageIcon myAmeL, myAmeR, myAmeU, myAmeD, myAmeDefaultR, myAmeDefaultL;

    //#endregion

    //#region Player Init

    private Controller controller;
    private GamePanel gamePanel;
    public boolean itemCollected = false;

    public Player(Position pos, Controller controller, GamePanel gamePanel) {

        super();
        this.position = pos;
        this.controller = controller;
        this.gamePanel = gamePanel;

        myAmeDefaultR = new ImageIcon("FINALTEST/images/GamePanel/MC_Default_Right-GamePanel.gif");
        myAmeDefaultL = new ImageIcon("FINALTEST/images/GamePanel/MC_Default_Left-GamePanel.gif");
        myAmeL = new ImageIcon("FINALTEST/images/GamePanel/MC_Left-GamePanel.gif");
        myAmeR = new ImageIcon("FINALTEST/images/GamePanel/MC_Right-GamePanel.gif");
        myAmeU = new ImageIcon("FINALTEST/images/GamePanel/MC_UP-GamePanel.gif");
        myAmeD = new ImageIcon("FINALTEST/images/GamePanel/MC_Down-GamePanel.gif");

    }

    //#endregion

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        int oldPosX = position.getX();
        int oldPosY = position.getY();

        if (controller.isRequestingUp()) {
            direction = "up";
            deltaY -= entitySpeed;
        }
        if (controller.isRequestingDown()) {
            direction = "down";
            deltaY += entitySpeed;
        }
        if (controller.isRequestingLeft()) {
            direction = "left";
            deltaX -= entitySpeed;
        }
        if (controller.isRequestingRight()) {
            direction = "right";
            deltaX += entitySpeed;
        }

        // Normalize speed if more than one key is pressed
        double length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (length > entitySpeed) {
            deltaX = (int) (deltaX / length * entitySpeed);
            deltaY = (int) (deltaY / length * entitySpeed);
        }

        position = new Position(position.getX() + deltaX, position.getY());

        // allow diagonal movements if player collides
        collisionOn = false;
        game.entityCollision.tileChecker(this);
        if (collisionOn) {
            position = new Position(oldPosX, position.getY());
        }

        // if player collides horizontally, revert to old position
        oldPosX = position.getX();

        collisionOn = false;
        game.entityCollision.tileChecker(this);
        if (collisionOn) {
            position = new Position(oldPosX, oldPosY);
        }

        // collision vertically
        position = new Position(position.getX(), position.getY() + deltaY);

        collisionOn = false;
        game.entityCollision.tileChecker(this);
        if (collisionOn) {
            position = new Position(position.getX(), oldPosY);
        }

        finishStatus();

    }

    @Override
    public Image getSprite() {
        Image image = myAmeDefaultR.getImage();

        // Change sprite lng
        if (controller.isRequestingUp()){
            image = myAmeU.getImage();
        }
        if (controller.isRequestingDown()){
            image = myAmeD.getImage();
        }
        if (controller.isRequestingLeft()){
            image = myAmeL.getImage();
        }
        if (controller.isRequestingRight()){
            image = myAmeR.getImage();
        }
        return image;
    }

    public void finishStatus() {
        // get player's position divided by the tile size
        int playerTileX = position.getX() / gamePanel.tileSize;
        int playerTileY = position.getY() / gamePanel.tileSize;

        // 2D array, will check if player's position is on finish part and if item is collected
        if (gamePanel.tileManager.tileMap[playerTileY][playerTileX] == 3 && itemCollected) {
            System.out.println("ROUND FINISHED");

    
        }
    }

}