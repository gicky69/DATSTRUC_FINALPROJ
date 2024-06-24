package entity;

import core.Position;
import core.Vector2D;
import core.physics2d.Collider;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Wall extends GameObject {


    public Wall(Position position) {
        this.position = position;
    }

    //#endregion

    @Override
    public void update() {
    }

    //#region Design and Animations

    @Override
    public Image getSprite() {
        return null;
    }
    //#endregion
}
