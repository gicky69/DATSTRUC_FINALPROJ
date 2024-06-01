package entity;

import controller.Controller;
import core.Position;

public class Player extends GameObject {

    private Controller controller;

    public Player(Position pos, Controller controller)  {
        super();
        this.position = pos;
        this.controller = controller;
    }

    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (controller.isClickedUp()){
            deltaY -= 3;
            System.out.println("up");
        }
        if (controller.isClickedDown()){
            deltaY += 3;
        }
        if (controller.isClickedLeft()){
            deltaX -= 3;
        }
        if (controller.isClickedRight()){
            deltaX += 3;
        }

        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
        System.out.println(position.getX() + " " + position.getY());
    }
}
