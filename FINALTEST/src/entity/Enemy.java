package entity;

import core.Position;
import core.Vector2D;
import core.physics2d.Collider;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Enemy extends GameObject {

    //#region Enemy Init
    private int EnemySpeed = 3;
    private float lookdirection = 90;
    private int viewDistance = 500; // How far can the enemy see?
    private int FOVSize = 90; // How big is the enemy FOV?
    private int deltaY = 3;

    private boolean Pursuing = false;

    // Build Lines
    private static final int RAYS = 360;

    LinkedList<Line2D.Float> lines;

    public Enemy(Position position) {
        lines = buildLines();
        this.position = position;
    }

    //#endregion

    @Override
    public void update() {
        Seeking();

        if (Pursuing) {
            Pursue();
        }

        lines = buildLines();

        //System.out.println("enemy x: " + position.getfX() + " enemy y: " + position.getfY());
    }

    //#region Enemy Behavior
    public void Seeking() {
        // direction is now in degrees 0-360
        // If closely reaching the bottom then go back down.

        Position moveto = Vector2D.angleToVector(lookdirection);
        float movetox = moveto.getfX() * EnemySpeed;
        float movetoy = moveto.getfY() * EnemySpeed;
        position = new Position(position.getfX() + movetox, position.getfY() + movetoy);


        // Will cast a line from itself, to the player (player is index 0), layerMask will only target entities which have layer 0 enabled
        List<Collider> colliders = game.p2d.LineCastObjects(position, game.getGameObjects().get(0).getPosition(), 0);
        int testPrintColliders = 2; // If you want to test the collider, if true, will print information about colliding objects between the enemy and player.
        if (!colliders.isEmpty() && colliders.get(0)!=null) {
            Collider collider  = colliders.get(0);
            if (testPrintColliders==1) { System.out.println("name: " + collider.gameObject.name); } // Will check the object that was hit closest according to the linecast
            if (collider.gameObject.name.equals("Player")) {
                Pursuing = true;
                // Check if the player is within the ray's angle, is the player close enough?
                float pdir = Vector2D.getAngleInDegrees(position, collider.point);
                float cdir = Math.abs(lookdirection-pdir)*2f;
                if (cdir>360) { cdir = cdir - 360; }
                if (testPrintColliders==1) { System.out.println("lookaway: " + cdir + " / distance: " + collider.distance); }
                if (cdir<90) {
                    if (testPrintColliders==1) { System.out.println("Player is within the enemy FOV"); }
                }
                if (collider.distance<300) {
                    if (testPrintColliders==1) { System.out.println("Player is within the enemy distance"); }
                }

                // If the enemy should detect the player
                if ((cdir<FOVSize) && collider.distance<viewDistance) {
                    // What happens if player is detected??
                    if (testPrintColliders==2) {
                        System.out.println("Player is detected");
                    }
                }
            } else {
                Pursuing = false;
            }
        }

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
        float oldPosX = position.getfX();
        float oldPosY = position.getfY();

        Position normalized = Normalize2D(pos); // Normalizes the relative position so it returns the directional value it is pointing to
        float xvel = normalized.getfX();
        float yvel = normalized.getfY();

        //# region Collision
        // Enemy collision with the Wall
        // Move towards the target with relative velocity times speed
        position = new Position(position.getfX() - xvel * (float) EnemySpeed, position.getfY());

        // allow diagonal movement
        collisionOn = false;
        game.entityCollision.tileChecker(game.getGameObjects());
        if (collisionOn) {
            position = new Position(oldPosX, position.getfY());
        }

        oldPosX = position.getfX();

        //  Horizontal Movement
        collisionOn = false;
        game.entityCollision.tileChecker(game.getGameObjects());
        if (collisionOn) {
            position = new Position(oldPosX, oldPosY);
        }

        position = new Position(position.getfX(), position.getfY() - yvel * (float) EnemySpeed);

        // Vertical Movement
        collisionOn = false;
        game.entityCollision.tileChecker(game.getGameObjects());
        if (collisionOn) {
            position = new Position(position.getX(), oldPosY);
        }

        //# endregion
    }

    public void MoveAwayFrom(Position pos) {

        Position normalized = Normalize2D(pos); // Normalizes the relative position so it returns the directional value it is pointing to
        float xvel = normalized.getfX();
        float yvel = normalized.getfY();

        // Move away from the target with relative velocity times speed
        position = new Position(position.getfX() + xvel * (float) EnemySpeed, position.getfY() + yvel * (float) EnemySpeed);
    }

    public void ShortestPath(Position pos) {
        // A* Algorithm


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

    //#region Raycasting
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

            if (lookdirection == 1) {
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
