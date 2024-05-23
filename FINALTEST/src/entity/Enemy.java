package entity;

import core.Position;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;


public class Enemy extends GameObject {

    public Enemy(Position position) {
        this.position = position;
    }

    @Override
    public void update() {
        position = new Position(position.getX(), position.getY());
//        Seeking();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 64, 64);

        graphics.dispose();
        return image;
    }

    public void Seeking() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (position.getX() > 100) {
                    position = new Position(position.getX() - 5, position.getY());
                }
                if (position.getY() > 100) {
                    position = new Position(position.getX(), position.getY() - 5);
                }
            }
        }, 0, 1000);
    }
}
