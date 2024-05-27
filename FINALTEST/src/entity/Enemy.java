package entity;

import core.Position;
import game.Game;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Enemy extends GameObject {

    //#region Enemy Init
    private int EnemySpeed = 3;
    private int direction = 1;
    private static final int RAYS = 360;

    private Position worldPosition;
    private Position screenPosition;

    LinkedList<Line2D.Float> lines;

    public Enemy(Position position) {
        lines = buildLines();
        this.position = position;

    }

    //#endregion

    @Override
    public void update() {
        Seeking();
        lines = buildLines();

        // Will cast a line from itself, to the player (player is index 0), layerMask will only target entities which have layer 0 enabled
        List<GameObject> linecasthits = game.LineCastObjects(position, game.getGameObjects().get(0).getPosition(), 0);
        if (!linecasthits.isEmpty() && linecasthits.get(0)!=null) {
            System.out.println("name: " + linecasthits.get(0).name); // Will check the object that was hit closest according to the linecast
        }

        //System.out.println("enemy x: " + position.getfX() + " enemy y: " + position.getfY());
    }

    //#region Enemy Behavior
    public void Seeking() {
        GameObject target = game.getGameObjects().get(0); // Get Player

        // If closely reaching the bottom then go back down.
        if (position.getY() >= 800) {
            direction = -1;
        }

        if (position.getY() <= 300) {
            direction = 1;
        }

        position = new Position(position.getfX(), position.getfY() + direction * (float) EnemySpeed);
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

    //#endregion

    //#region FOV

    public void drawFOV(Graphics2D g) {
        LinkedList<Line2D.Float> rays = ray(lines, (int) position.getfX(), (int) position.getfY(), RAYS, 500);
        g.setColor(Color.RED);
        for (Line2D.Float ray : rays) {
            g.drawLine((int)ray.x1, (int)ray.y1, (int)ray.x2, (int)ray.y2);
        }
    }

    public LinkedList<Line2D.Float> buildLines() {
        LinkedList<Line2D.Float> lines = new LinkedList<>();
        for (int i = 0;i < 10;i++) {
            lines.add(new Line2D.Float(0, 0,0, 0));
        }

        return lines;
    }

    public LinkedList<Line2D.Float> ray(LinkedList<Line2D.Float> lines, int x, int y, int resolution, int maxDist) {
        LinkedList<Line2D.Float> rays = new LinkedList<>();
        double dir = 0;
        for (int i = 0;i < resolution;i++) {
            dir = -Math.PI / 3 + -Math.PI / 3 * ((double) i / resolution);

            if (direction == 1) {
                dir = Math.PI / 3 + Math.PI / 3 * ((double) i / resolution);
            }

            float minDist = maxDist;
            for (Line2D.Float line : lines) {
                float dist = getRayCast(x+32, y, x +(float) Math.cos(dir) * maxDist, y + (float) Math.sin(dir) * maxDist, line.x1, line.y1, line.x2, line.y2);
                if (dist < minDist && dist > 0) {
                    minDist = dist;
                }
            }
            rays.add(new Line2D.Float(x+32,y, x+(float) Math.cos(dir) * minDist, y + (float) Math.sin(dir) * minDist));
        }
        return rays;
    }

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static float getRayCast(float p0_x, float p0_y, float p1_x, float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) {
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = p1_x - p0_x;
        s1_y = p1_y - p0_y;
        s2_x = p3_x - p2_x;
        s2_y = p3_y - p2_y;

        float s, t;
        s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            float x = p0_x + (t * s1_x);
            float y = p0_y + (t * s1_y);

            return dist(p0_x, p0_y, x, y);
        }

        return -1; // No collision
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
