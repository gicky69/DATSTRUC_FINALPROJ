package controller;

import core.Position;

public class NPCController implements Controller {

    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;

    @Override
    public boolean isRequestingUp() {
        return up;
    }

    @Override
    public boolean isRequestingDown() {
        return down;
    }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    @Override
    public boolean isRequestingRight() {
        return right;
    }

    @Override
    public boolean isSprinting() {
        return false;
    }

    @Override
    public boolean isSneaking() {
        return false;
    }

    @Override
    public boolean isSprintKeyReleased() {
        return false;
    }

    @Override
    public boolean isPaused() {
        return false;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();

        // Directions
        up = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        down = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        left = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        right = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        //



    }

    public void stop() {
        up = false;
        down = false;
        right = false;
        left = false;
    }
}
