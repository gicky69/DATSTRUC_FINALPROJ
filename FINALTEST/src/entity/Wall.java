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
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, 64, 64);

        graphics.dispose();
        return image;
    }
    //#endregion
}
