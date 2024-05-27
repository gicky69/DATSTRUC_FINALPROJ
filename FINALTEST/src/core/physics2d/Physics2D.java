package core.physics2d;

import core.Position;
import entity.GameObject;
import game.Game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Physics2D {

    public static Game game;

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static Collider detectIntersect(Position origin, Position end, Position linecolp1, Position linecolp2) {

        //getLineCast(float xs1, float ys1, float xe1, float ye1, float xs2, float ys2, float xe2, float ye2)
        float xs1 = origin.getfX();
        float ys1 = origin.getfY();
        float xe1 = end.getfX();
        float ye1 = end.getfY();
        float xs2 = linecolp1.getfX() + 0.001f; //These numbers are to prevent division by zero
        float ys2 = linecolp1.getfY() + 0.002f;
        float xe2 = linecolp2.getfX() + 0.003f;
        float ye2 = linecolp2.getfY() + 0.004f;
        float neverzero;

        //Points
        float p1x;
        float p1y;
        float p2x;
        float p2y;

        float[] slope = new float[2];
        float[] intercept = new float[2];
        int aid = 0;

        aid = 0;
        p1x = xs1;
        p1y = ys1;
        p2x = xe1;
        p2y = ye1;
        slope[aid] = (p2y - p1y) / (p2x - p1x);
        intercept[aid] = p1y - slope[aid] * p1x;

        aid = 1;
        p1x = xs2;
        p1y = ys2;
        p2x = xe2;
        p2y = ye2;
        slope[aid] = (p2y - p1y) / (p2x - p1x);
        intercept[aid] = p1y - slope[aid] * p1x;

        // Check if lines are parallel
        if (slope[0] == slope[1]) {
            return null;
        }

        // Calculate intersection point
        neverzero = (slope[0] - slope[1]);
        float x = (intercept[1] - intercept[0]) / (slope[0] - slope[1]);
        float y = slope[0] * x + intercept[0];

        //Check if it is inside the parallel lines
        boolean isIntersect = false;
        if (Math.abs(xe2-xs2)>Math.abs(ye2-ys2)){
            if ((xe2>xs2 && xe2>x && x>xs2) || (xs2>xe2 && xs2>x && x>xe2)) {
                isIntersect = true;
            }
        } else {
            if ((ye2>ys2 && ye2>y && y>ys2) || (ys2>ye2 && ys2>y && y>ye2)) {
                isIntersect = true;
            }
        }
        // Calculate intersection distance
        if (isIntersect) {
            // Calculate intersection distance
            Collider collider = new Collider();
            collider.point = new Position(x,y);
            collider.distance = dist(xs1,ys1,x,y);
            return collider;
        } else {
            return null;
        }

    }

    static float dist = -1;
    static List<Collider> colliders = new ArrayList<>();
    static Collider collider;

    public static List<Collider> LineCastObjects(Position start, Position end, int layerMask) {
        colliders.clear();
        collider = null;
        List<GameObject> gameObjects = game.getGameObjects();

        gameObjects.forEach((object) -> {
            dist = -1;
            object.getCollision().getCollisionLines().forEach( (LC) -> {
                Collider getcollider = detectIntersect(start, end,
                        new Position(LC.getPoint1().getfX() + object.getPosition().getfX(), LC.getPoint1().getfY() + object.getPosition().getfY()),
                        new Position(LC.getPoint2().getfX() + object.getPosition().getfX(), LC.getPoint2().getfY() + object.getPosition().getfY()));
                if (getcollider!=null) {
                    if (dist==-1 || dist > collider.distance) {
                        dist = getcollider.distance;
                        collider = getcollider;
                    }
                }
            });
            if (dist!=-1 && object.getCollision().getLayerMask(layerMask)) {
                collider.gameObject = object;
                colliders.add(collider);
            }
            //System.out.println("that was, name: " + object.name + "/ distance: " + dist);
        });
        colliders.sort(new Comparator<Collider>() {
            @Override
            public int compare(Collider a1, Collider a2) {
                return Float.compare(a1.distance, a2.distance);
            }
        });
        return colliders;
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
}
