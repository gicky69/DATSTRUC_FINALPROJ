package entity;

import controller.Controller;
import core.Position;


import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {
    ImageIcon myAmeL, myAmeR, myAmeU, myAmeD, myAmeDefaultR, myAmeDefaultL;
    private int PlayerSpeed = 5;
    private Controller controller;
    Image sprite;

    public Player(Controller controller) {
        super();
        this.controller = controller;
        myAmeDefaultR = new ImageIcon("FINALTEST/images/GamePanel/MC_Default_Right-GamePanel.gif");
        myAmeDefaultL = new ImageIcon("FINALTEST/images/GamePanel/MC_Default_Left-GamePanel.gif");
        myAmeL = new ImageIcon("FINALTEST/images/GamePanel/MC_Left-GamePanel.gif");
        myAmeR = new ImageIcon("FINALTEST/images/GamePanel/MC_Right-GamePanel.gif");
        myAmeU = new ImageIcon("FINALTEST/images/GamePanel/MC_UP-GamePanel.gif");
        myAmeD = new ImageIcon("FINALTEST/images/GamePanel/MC_Down-GamePanel.gif");

        sprite = myAmeDefaultR.getImage();

    }

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (controller.isRequestingUp()) {
            sprite = myAmeU.getImage();
            deltaY -= PlayerSpeed;
        }
        if (controller.isRequestingDown()) {
            sprite = myAmeD.getImage();
            deltaY += PlayerSpeed;
        }
        if (controller.isRequestingLeft()) {
            sprite = myAmeL.getImage();
            deltaX -= PlayerSpeed;
        }
        if (controller.isRequestingRight()) {
            sprite = myAmeR.getImage();
            deltaX += PlayerSpeed;
        }

        if (controller.isDefault()) {
            sprite = myAmeDefaultR.getImage();
        }

        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }

    @Override
    public Image getSprite() {

        return sprite;
    }
}
