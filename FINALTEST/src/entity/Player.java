package entity;

import controller.Controller;
import core.Position;
import core.collision.Collision;
import game.Game;


import javax.swing.*;
import java.awt.*;
import display.GamePanel;

public class Player extends GameObject {

    //#region Design and Animation Init

    ImageIcon myAmeL, myAmeR, myAmeU, myAmeD, myAmeDefaultR, myAmeDefaultL;

    //#endregion

    //#region Player Init

    private Controller controller;
    private int PlayerSpeed = 4;
    private Position worldPosition;
    private Position screenPosition;
    private GamePanel gamePanel;

    public Player(Position pos, Controller controller, GamePanel gamePanel) {

        super();
        this.position = pos;
        this.worldPosition = position;
        this.screenPosition = new Position(0, 0);
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

    // getters and setters for worldPosition and screenPosition
    public Position getWorldPosition() {
        return worldPosition;
    }

    public void setWorldPosition(Position worldPosition) {
        this.worldPosition = worldPosition;
    }

    public Position getScreenPosition() {
        return screenPosition;
    }

    public void setScreenPosition(Position screenPosition) {
        this.screenPosition = screenPosition;
    }

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (controller.isRequestingUp()) {
            deltaY -= PlayerSpeed;
        }
        if (controller.isRequestingDown()) {
            deltaY += PlayerSpeed;
        }
        if (controller.isRequestingLeft()) {
            deltaX -= PlayerSpeed;
        }
        if (controller.isRequestingRight()) {
            deltaX += PlayerSpeed;
        }

        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
        setWorldPosition(position);
        System.out.println("WORLD POSITION: " + position.getX() + " " + position.getY());
        System.out.println("SCREEN POSITION: " + screenPosition.getX() + " " + screenPosition.getY());
        setScreenPosition(new Position(gamePanel.screenWidth / 2, gamePanel.screenHeight / 2));

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
}