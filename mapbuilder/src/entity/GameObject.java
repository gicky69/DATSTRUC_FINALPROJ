package entity;

import core.Position;
import game.Game;

public abstract class GameObject {
    protected Position position;
    public Game game;

    public abstract void update();

    public Position getPosition() {
        return position;
    }
}
