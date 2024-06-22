package entity;

import core.Movement;
import core.Position;
import core.Vector2D;
import core.physics2d.Collider;
//import tile.pathfinder.pathfinder.Node;
import tile.pathfinder.pathfinder;
//import tile.Pathfinder;

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
    private pathfinder pf;
    private int deltaY = 3;

    private boolean Pursuing = true;

    private List<Position> pathToFollow;
    private int currentPathIndex;

    // Build Lines
    private static final int RAYS = 360;

    LinkedList<Line2D.Float> lines;

    public Enemy(Position position) {
        this.position = position;
        this.pf = new pathfinder();
        movement = new Movement();
    }

    //#endregion

    @Override
    public void update() {
//        if (Pursuing) {
//            Pursue();
//        } else {
//            Seeking();
//        }

        //System.out.println("enemy x: " + position.getfX() + " enemy y: " + position.getfY());
    }

    //#region Enemy Behavior
    public void Seeking() {
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
        } else {
            Pursuing = false;
        }

    }

    public void Wander() {

    }

    public void Pursue() {
        Position target = game.getGameObjects().get(0).getPosition(); // Get Player
        Position start = position;

//        System.out.println(Vector2D.getDistance(position, target));
        pf.printInfo = 0;
        pathToFollow = pf.findPath(start, target, game.getMap());
        currentPathIndex = 1;
        followPath();
    }

    public void Flee() {
        GameObject target = game.getGameObjects().get(0); // Get Player
        MoveAwayFrom(target.position); // Move towards the player's position
    }
    //#endregion

    //#region Enemy Actions

    public void setPosition(Position position) {
        this.position = position;
    }

    public void followPath() {
        if (currentPathIndex < pathToFollow.size()) {
            Position target = pathToFollow.get(currentPathIndex);
            movement.MoveTowards(position, target);
            if (position.getfX() == target.getfX() && position.getfY() == target.getfY()) {
                currentPathIndex++;
            }
        } else {
            Pursuing = false;
        }
    }

    public void MoveAwayFrom(Position pos) {

        Position normalized = movement.Normalize2D(position, pos); // Normalizes the relative position so it returns the directional value it is pointing to
        float xvel = normalized.getfX();
        float yvel = normalized.getfY();

        // Move away from the target with relative velocity times speed
        position = new Position(position.getfX() + xvel * (float) EnemySpeed, position.getfY() + yvel * (float) EnemySpeed);
    }

    //#endregion
    //#endregion

    //#region FOV

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
