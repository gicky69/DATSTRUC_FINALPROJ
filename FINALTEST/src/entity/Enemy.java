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

    //#region Normalize

    Position Normalize2D(Position pos) { // This normalizes the 2d vector value (search normalize 2d vector to learn more)
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
        if (tx != 0 && Math.abs(tx) > EnemySpeed) {
            nx = (float) tx / (float)Math.hypot(Math.abs(tx), Math.abs(ty)); //Math.hypot returns the hypotenuse value from the 2 vectors
        }
        if (ty != 0 && Math.abs(ty) > EnemySpeed) {
            ny = (float) ty / (float)Math.hypot(Math.abs(tx), Math.abs(ty));
        }

        return new Position(nx, ny);
    }

    public void MoveTowards(Position pos) {

        Position normalized = Normalize2D(pos); // Normalizes the relative position so it returns the directional value it is pointing to
        float xvel = normalized.getfX();
        float yvel = normalized.getfY();

        // Move towards the target with relative velocity times speed
        position = new Position(position.getfX() - xvel * (float) EnemySpeed, position.getfY() - yvel * (float) EnemySpeed);

    }

    public void MoveAwayFrom(Position pos) {

        Position normalized = Normalize2D(pos); // Normalizes the relative position so it returns the directional value it is pointing to
        float xvel = normalized.getfX();
        float yvel = normalized.getfY();

        // Move away from the target with relative velocity times speed
        position = new Position(position.getfX() + xvel * (float) EnemySpeed, position.getfY() + yvel * (float) EnemySpeed);
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
