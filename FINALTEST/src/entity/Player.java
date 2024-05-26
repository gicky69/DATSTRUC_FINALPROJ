package entity;

import controller.Controller;
import core.Position;
import game.Game;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player extends GameObject {

    //#region Design and Animation Init

    ImageIcon myAmeL, myAmeR, myAmeU, myAmeD, myAmeDefaultR, myAmeDefaultL;

    //#endregion

    //#region Player Init

    private Controller controller;
    private int PlayerSpeed = 5;

    public Player(Position pos, Controller controller) {

        super();

        this.position = pos;

        this.controller = controller;
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

    //#region Player Hitbox

    //#endregion
}
