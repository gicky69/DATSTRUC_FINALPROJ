package core.gameplay;

import core.Position;
import display.Camera;
import game.Game;

import java.awt.*;

public class footstep {
    private float radius;
    private float noise;

    public footstep(float radius, float noise) {
        this.radius = radius;
        this.noise = noise;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getNoise() {
        return noise;
    }

    public void setNoise(float noise) {
        this.noise = noise;
    }


    public void draw(Graphics2D g2d, Position playerPosition, Game game) {
        int radiusInPixels = (int) (radius * 100); // Convert radius to pixels if necessary
        int diameter = radiusInPixels * 2;

        Position camera = game.getCamera().getPosition();

        float topLeftX = playerPosition.getfX() - camera.getfX() - radiusInPixels - 7;
        float topLeftY = playerPosition.getfY() - camera.getfY() - radiusInPixels - 5;

        g2d.drawOval((int)topLeftX, (int)topLeftY, diameter, diameter);
    }
}
