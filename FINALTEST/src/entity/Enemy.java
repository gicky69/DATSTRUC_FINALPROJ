package entity;

import controller.Controller;
import core.Position;
import game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;


public class Enemy extends GameObject {

    //#region Enemy Init

    private int EnemySpeed = 3;

    public Enemy(Position position) {
        this.position = position;
    }

    //#endregion

    @Override
    public void update() {
        Pursue();
    }

    //#region Enemy Behavior

    public void Seeking() {

    }

    public void Pursue() {
        GameObject target = game.getGameObjects().get(0); // Get Player
        MoveTowards(target.position); // Move towards the player's position
    }

    public void Flee() {
        GameObject target = game.getGameObjects().get(0); // Get Player
        MoveAwayFrom(target.position); // Move towards the player's position
    }
    //#endregion

    //#region Enemy Actions

    public void MoveTowards(Position pos) {

        int mx = pos.getX();
        int my = pos.getY();

        int tx = position.getX() - Math.abs(mx);
        int ty = position.getY() - Math.abs(my);
        float nx = 0f;
        float ny = 0f;
        if (tx != 0 && Math.abs(tx) > EnemySpeed) {
            nx = (float) tx / Math.abs(tx + ty);
        }
        if (ty != 0 && Math.abs(ty) > EnemySpeed) {
            ny = (float) ty / Math.abs(tx + ty);
        }
        float nspd = Math.abs(nx)+Math.abs(ny);
        nx = nx/nspd;
        ny = ny/nspd;
        System.out.println("nx: " + nx + ", ny: " + ny + ", speed: " + (Math.abs(nx)+Math.abs(ny)));
        position = new Position(position.getfX() - nx*(float)EnemySpeed, position.getfY() - ny*(float)EnemySpeed);

    }

    public void MoveAwayFrom(Position pos) {

        int mx = pos.getX();
        int my = pos.getY();

        int tx = position.getX() - Math.abs(mx);
        int ty = position.getY() - Math.abs(my);
        float nx = 0f;
        float ny = 0f;
        if (tx != 0 && Math.abs(tx) > EnemySpeed) {
            nx = (float) tx / Math.abs(tx + ty);
        }
        if (ty != 0 && Math.abs(ty) > EnemySpeed) {
            ny = (float) ty / Math.abs(tx + ty);
        }
        float nspd = Math.abs(nx)+Math.abs(ny);
        nx = nx/nspd;
        ny = ny/nspd;
        System.out.println("nx: " + nx + ", ny: " + ny + ", speed: " + (Math.abs(nx)+Math.abs(ny)));
        position = new Position(position.getfX() + nx*(float)EnemySpeed, position.getfY() + ny*(float)EnemySpeed);

    }

    //#endregion

    //#region Design and Animations

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 64, 64);

        graphics.dispose();
        return image;
    }

    //#endregion
}
