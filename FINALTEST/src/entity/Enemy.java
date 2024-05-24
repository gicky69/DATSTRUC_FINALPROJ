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

    private double fov = Math.toRadians(90);
    private double viewDistance = 400;

    public Enemy(Position position) {
        this.position = position;

    }

    //#endregion

    @Override
    public void update() {
        Seeking();
        //System.out.println("enemy x: " + position.getfX() + " enemy y: " + position.getfY());
    }

    //#region Enemy Behavior

    public void updateFOV(double angle) {
        GameObject target = game.getGameObjects().get(0);

        double distance = Math.sqrt(Math.pow(target.position.getX() - position.getX(), 2) + Math.pow(target.position.getY() - position.getY(), 2));

        System.out.println("Distance: " + distance);
        System.out.println("Angle: " + angle);
        for (GameObject gameObject : game.getGameObjects()) {
            if (gameObject instanceof Player) {
                if (distance <= viewDistance) {
                    if (angle >= Math.toRadians(90) && angle <= Math.toRadians(180)) {
                        Pursue();
                    }
                }
            }
        }
    }

    public void Seeking() {
        GameObject target = game.getGameObjects().get(0); // Get Player
        int px = position.getX();
        int py = position.getY();

        double oscillaitonSpeed = 0.05;
        double oscillationAmplitude = 50;
        double oscillationOffset = 0;

        oscillationOffset += oscillaitonSpeed;
        double dy = oscillationAmplitude * Math.sin(oscillationOffset);
        position = new Position(position.getfX(), position.getfY() + (float) dy);
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
        // it checks if nspd is infinite, This is to avoid infinite calculation
        float nspd = Math.abs(xvel)+Math.abs(yvel);
        if (xvel!=0 && nspd!=0) { xvel = xvel/nspd; }
        if (yvel!=0 && nspd!=0) { yvel = yvel/nspd; }

        // Move towards the target with relative velocity times speed
        if (tx+ty!=0) { // This is to avoid infinite calculation
            position = new Position(position.getfX() - xvel * (float) EnemySpeed, position.getfY() - yvel * (float) EnemySpeed);
        } else { //if the number is an exact 45 degrees, do this instead:
            if (tx>0) { xvel=-0.5f; }
            if (ty>0) { yvel=-0.5f; }
            if (tx<0) { xvel=0.5f; }
            if (ty<0) { yvel=0.5f; }
            position = new Position(position.getfX() + xvel * (float) EnemySpeed, position.getfY() + yvel * (float) EnemySpeed);
        }

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
        // it checks if nspd is infinite, This is to avoid infinite calculation
        float nspd = Math.abs(xvel)+Math.abs(yvel);
        if (xvel!=0 && Float.isFinite(nspd)) { xvel = xvel/nspd; }
        if (yvel!=0 && Float.isFinite(nspd)) { yvel = yvel/nspd; }

        // Move away from the target with relative velocity times speed
        if (tx+ty!=0) { // This is to avoid infinite calculation
            position = new Position(position.getfX() + xvel * (float) EnemySpeed, position.getfY() + yvel * (float) EnemySpeed);
        } else { //if the number is an exact 45 degrees, do this instead:
            if (tx>0) { xvel=-0.5f; }
            if (ty>0) { yvel=-0.5f; }
            if (tx<0) { xvel=0.5f; }
            if (ty<0) { yvel=0.5f; }
            position = new Position(position.getfX() + xvel * (float) EnemySpeed, position.getfY() + yvel * (float) EnemySpeed);
        }

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
