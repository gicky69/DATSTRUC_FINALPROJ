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
        //Seeking();
        Pursuing(1300, 800);
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
                else
                {
                    position = new Position(500, 500);
                }
                if (position.getY() > 100) {
                    position = new Position(position.getX(), position.getY() - 5);
                }
            }
        }, 0, 1000);
    }

    public void Pursuing(int targetx, int targety) {

        int mx = targetx;
        int my = targety;

        int speed = 5;

        int tx = position.getX() - Math.abs(mx);
        int ty = position.getY() - Math.abs(my);
        float nx = 0f;
        float ny = 0f;
        if (tx != 0 && Math.abs(tx) > speed) {
            if (position.getX() < mx) {
                nx = (float) tx / (tx + ty);
            } else {
                nx = -((float) tx / (tx + ty));
            }
        }
        if (ty != 0 && Math.abs(ty) > speed) {
            if (position.getY() < my) {
                ny = (float) ty / (tx + ty);
            } else {
                ny = -((float) ty / (tx + ty));
            }
        }
        System.out.println("nx: " + tx + ", ny: " + ty);
        if (nx>1) { nx = 1; }
        if (nx<-1) { nx = -1; }
        if (ny>1) { ny = 1; }
        if (ny<-1) { ny = -1; }
        position = new Position((int)(position.getX() + (int)Math.round(nx*(float)speed)), (int)(position.getY() + (int)Math.round(ny*(float)speed)));

        /*
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int tx = position.getX() - Math.abs(mx);
                int ty = position.getY() - Math.abs(my);
                float nx = 0f;
                float ny = 0f;
                if (tx != 0) {
                    if (position.getX() < mx) {
                        nx = (float) tx / (tx + ty);
                    } else {
                        nx = -((float) tx / (tx + ty));
                    }
                }
                if (tx != 0) {
                    if (position.getY() < my) {
                        ny = (float) ty / (tx + ty);
                    } else {
                        ny = -((float) ty / (tx + ty));
                    }
                }
                System.out.println("nx: " + nx + ", ny: " + ny);
                if (nx>1) { nx = 1; }
                if (nx<-1) { nx = -1; }
                if (ny>1) { ny = 1; }
                if (ny<-1) { ny = -1; }
                position = new Position((int)(position.getX() + (nx*2)), (int)(position.getY() + (ny*2)));
            }
        }, 0, 1000);

         */
    }

    //public void
}
