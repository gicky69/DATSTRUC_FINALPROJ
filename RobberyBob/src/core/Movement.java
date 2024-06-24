package core;

import core.Position;
import core.Vector2D;

public class Movement {
    private float xvel;
    private float yvel;

    public void MoveTowards(Position position, Position pos) {
        float oldPosX = position.getfX();
        float oldPosY = position.getfY();

        Position normalized = Normalize2D(position, pos);
        xvel = normalized.getfX();
        yvel = normalized.getfY();

        if (Vector2D.getDistance(position, pos)< 4) {
            position.setfX(pos.getfX());
            position.setfY(pos.getfY());
        }
        else {
            position.setfX(position.getfX() - xvel * 4);
            position.setfY(position.getfY() - yvel * 4);
        }

        //# region Collision




        //#endregion
    }

    public void stop() {
        // Stops the movement of the entity
        xvel = 0;
        yvel = 0;
    }


    //#region Normalize
    public Position Normalize2D(Position position, Position pos) { // This normalizes the 2d vector value (search normalize 2d vector to learn more)
        // Gets the target position in x and y
        int px = pos.getX();
        int py = pos.getY();

        // Gets the relative target position in x and y
        int tx = position.getX() - Math.abs(px);
        int ty = position.getY() - Math.abs(py);

        // Initiates "X velocity" and "Y velocity"
        float nx = 0f;
        float ny = 0f;

        // This is an equation that gets the normalized vector of target
        if (tx != 0) {
            nx = (float) tx / (float)Math.hypot(Math.abs(tx), Math.abs(ty)); //Math.hypot returns the hypotenuse value from the 2 vectors
        }
        if (ty != 0) {
            ny = (float) ty / (float)Math.hypot(Math.abs(tx), Math.abs(ty));
        }

        return new Position(nx, ny);
    }
    //#endregion


}