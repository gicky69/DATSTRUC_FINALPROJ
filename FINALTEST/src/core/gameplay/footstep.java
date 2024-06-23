package core.gameplay;

import core.Position;

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


    public void draw(Graphics2D g2d, Position playerPosition) {
        int radiusInPixels = (int) (radius * 100); // Convert radius to pixels if necessary
        int diameter = radiusInPixels * 2;

        float topLeftX = playerPosition.getfX();
        float topLeftY = playerPosition.getfY();

        g2d.drawOval((int)topLeftX, (int)topLeftY, diameter, diameter);
    }
}
