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
        Pursue(); // Pursuing the player
        //System.out.println("enemy x: " + position.getfX() + " enemy y: " + position.getfY());
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

        // Gets the target position in x and y
        int px = pos.getX();
        int py = pos.getY();
        
        // Gets the relative target position in x and y
        int tx = position.getX() - Math.abs(px);
        int ty = position.getY() - Math.abs(py);
        
        // Initiates "X velocity" and "Y velocity"
        float xvel = 0f;
        float yvel = 0f;

        // This is an equation that gets the normalized vector of target
        if (tx != 0 && Math.abs(tx) > EnemySpeed) {
            xvel = (float) tx / Math.abs(tx + ty);
        }
        if (ty != 0 && Math.abs(ty) > EnemySpeed) {
            yvel = (float) ty / Math.abs(tx + ty);
        }

        // This is to normalize the speed to be more precise
        // Without this, enemy can sometimes go 10% faster or slower
        float nspd = Math.abs(xvel)+Math.abs(yvel);
        System.out.println("enemy nspd: " + nspd);
        if (xvel!=0 && Float.isFinite(nspd)) { xvel = xvel/nspd; }
        if (yvel!=0 && Float.isFinite(nspd)) { yvel = yvel/nspd; }

        // Move towards the target with relative velocity times speed
        position = new Position(position.getfX() - xvel*(float)EnemySpeed, position.getfY() - yvel*(float)EnemySpeed);

    }

    public void MoveAwayFrom(Position pos) {

        // Gets the target position in x and y
        int px = pos.getX();
        int py = pos.getY();

        // Gets the relative target position in x and y
        int tx = position.getX() - Math.abs(px);
        int ty = position.getY() - Math.abs(py);

        // Initiates "X velocity" and "Y velocity"
        float xvel = 0f;
        float yvel = 0f;

        // This is an equation that gets the normalized vector of target
        if (tx != 0 && Math.abs(tx) > EnemySpeed) {
            xvel = (float) tx / Math.abs(tx + ty);
        }
        if (ty != 0 && Math.abs(ty) > EnemySpeed) {
            yvel = (float) ty / Math.abs(tx + ty);
        }

        // This is to normalize the speed to be more precise
        // Without this, enemy can sometimes go 10% faster or slower
        float nspd = Math.abs(xvel)+Math.abs(yvel);
        if (xvel!=0 && Float.isFinite(nspd)) { xvel = xvel/nspd; }
        if (yvel!=0 && Float.isFinite(nspd)) { yvel = yvel/nspd; }

        // Move away from the target with relative velocity times speed
        position = new Position(position.getfX() + xvel*(float)EnemySpeed, position.getfY() + yvel*(float)EnemySpeed);

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
