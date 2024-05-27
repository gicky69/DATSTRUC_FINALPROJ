package core.collision;

import core.Position;
import entity.GameObject;
import game.Game;

import java.util.ArrayList;
import java.util.List;

public class Physics2D {

    public Game game;

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static float detectIntersect(Position origin, Position end, Position linecolp1, Position linecolp2) {

        //getLineCast(float xs1, float ys1, float xe1, float ye1, float xs2, float ys2, float xe2, float ye2)
        float xs1 = origin.getfX();
        float ys1 = origin.getfY();
        float xe1 = end.getfX();
        float ye1 = end.getfY();
        float xs2 = linecolp1.getfX();
        float ys2 = linecolp1.getfY();
        float xe2 = linecolp2.getfX();
        float ye2 = linecolp2.getfY();

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
            return -1;
        }

        // Calculate intersection point
        float x = (intercept[1] - intercept[0]) / (slope[0] - slope[1]);
        float y = slope[0] * x + intercept[0];

        //Check if it is outside the parallel lines
        if ((xs2<xe2 && xs2>x) || (xs2>xe2 && xe2>x) || (xs2<xe2 && xe2<x) || (xs2>xe2 && xs2<x))  {
            return -1;
        }
        if ((ys2<ye2 && ys2>y) || (ys2>ye2 && ye2>y) || (ys2<ye2 && ye2<y) || (ys2>ye2 && ys2<y))  {
            return -1;
        }

        // Calculate intersection distance
        return dist(xs1,ys1,x,y);

    }

    static float dist = -1;
    List<Collider> colliders = new ArrayList<>();

    public List<Collider> getLineCast(Position origin, Position end, int layerMask) {
        colliders.clear();
        game.getGameObjects().forEach((object) -> {
            dist = -1;
            object.getCollision().getCollisionLines().forEach( (LC) -> {
                float getdist = detectIntersect(origin, end, LC.getPoint1(), LC.getPoint2());
                if (getdist<dist || dist<0) {
                    dist = getdist;
                }
            });
            if (dist!=-1 && object.getCollision().getLayerMask(layerMask)) {
                colliders.add(new Collider(object, dist));
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
