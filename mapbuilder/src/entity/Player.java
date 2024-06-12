package entity;

import controller.Controller;
import core.Position;

public class Player extends GameObject {

    private Controller controller;

    public int lastKeyPressed = 0;

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

        // Key Inputs
        if (controller.clickedGrass()) {
            lastKeyPressed = 48;
        }
        if (controller.clickedWall()) {
            lastKeyPressed = 49;
        }
        if (controller.clickedDoor()) {
            lastKeyPressed = 50;
        }
        if (controller.clickedFloor()) {
            lastKeyPressed = 51;
        }

//        System.out.println("Last key pressed: " + lastKeyPressed);
        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }
}
