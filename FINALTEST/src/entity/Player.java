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

        position = new Position(position.getX() + deltaX, position.getY() + deltaY);

        collisionOn = false;
        game.entityCollision.tileChecker(this);

        if (collisionOn) {
            switch (direction) {
                case "up":
                    position = new Position(position.getX() + deltaX, position.getY() + entitySpeed);
                    break;
                case "down":
                    position = new Position(position.getX() + deltaX, position.getY() - entitySpeed);
                    break;
                case "left":
                    position = new Position(position.getX() + entitySpeed, position.getY() + deltaY);
                    break;
                case "right":
                    position = new Position(position.getX() - entitySpeed, position.getY() + deltaY);
                    break;
            }
        }

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

    public boolean getCollisionState() {
        return this.collisionOn;
    }
}