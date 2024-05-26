package entity;

import core.collision.Collision;
import core.Position;
import core.Size;
import game.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;


// Abstract class GameObject, so bale iniinherit ng Player at enemy or any other entities pa yung gameobject which is kung nainherit na nila meron na silang attributes kung ano mang attributes nasa GameObject.
public abstract class GameObject {
    protected Position position;
    protected Size size;
    protected Collision collision;
    public Game game;
    public String name = "entity";

    public GameObject() {
        position = new Position(50,50);
        size = new Size(64, 64);
//        collision = new Collision();
//        collision.BoxCollision(size.getWidth(), size.getHeight());
    }

    public abstract void update();
    public abstract Image getSprite();

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public Collision getCollision() { return collision; }

    public Rectangle2D.Float getBounds() {
        return new Rectangle2D.Float(position.getfX(), position.getfY(), size.getWidth(), size.getHeight());
    }
}
