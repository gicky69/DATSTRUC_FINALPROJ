package core.collision;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class RayCast {

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static float getLineCast(float xs1, float ys1, float xe1, float ye1, float xs2, float ys2, float xe2, float ye2) {

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

        //Point intersection = new Point(x, y);
        //System.out.println("xs1: " + xs1 + " / ys1: " + ys1);
        //System.out.println("x: " + x + " / y: " + y);
        // Calculate intersection distance
        return dist(xs1,ys1,x,y);

        /*
        class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}

class Line {
    double slope, intercept;

    Line(Point p1, Point p2) {
        slope = (p2.y - p1.y) / (p2.x - p1.x);
        intercept = p1.y - slope * p1.x;
    }
}

public class LineIntersection {
    public static double findIntersectionDistance(Line line1, Line line2) {
        // Check if lines are parallel
        if (line1.slope == line2.slope) {
            System.out.println("Lines are parallel. No intersection exists.");
            return -1;
        }

        // Calculate intersection point
        double x = (line2.intercept - line1.intercept) / (line1.slope - line2.slope);
        double y = line1.slope * x + line1.intercept;

        Point intersection = new Point(x, y);

        // Calculate distance from origin
        return intersection.distanceFromOrigin();
    }

    public static void main(String[] args) {
        // Define points for lines
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 5);
        Point p3 = new Point(2, 4);
        Point p4 = new Point(5, 1);

        // Create lines
        Line line1 = new Line(p1, p2);
        Line line2 = new Line(p3, p4);

        // Find intersection distance
        double intersectionDistance = findIntersectionDistance(line1, line2);

        // Print intersection distance or -1 if lines are parallel
        if (intersectionDistance != -1) {
            System.out.println("Distance from origin to intersection: " + intersectionDistance);
        } else {
            System.out.println("Lines are parallel. No intersection exists.");
        }
    }
}
         */

        /*
        float s, t;
        s = (-p1_y * (p0_x - p2_x) + p1_x * (p0_y - p2_y)) / (-p3_x * p1_y + p1_x * p3_y);
        t = (p3_x * (p0_y - p2_y) - p3_y * (p0_x - p2_x)) / (-p3_x * p1_y + p1_x * p3_y);

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            float x = p0_x + (t * p1_x);
            float y = p0_y + (t * p1_y);

            return dist(p0_x, p0_y, x, y);
        }

        return -1; // No collision
         */

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
