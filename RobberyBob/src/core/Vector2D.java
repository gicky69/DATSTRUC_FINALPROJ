package core;

import java.awt.geom.Point2D;

public class Vector2D {


    public static Position angleToVector(float angleInDegrees) {
        // Convert angle from degrees to radians
        double angleInRadians = Math.toRadians(angleInDegrees);
        // Calculate the x and y components of the directional vector
        float xComponent = (float)Math.cos(angleInRadians);
        float yComponent = (float)Math.sin(angleInRadians);
        return new Position(xComponent, yComponent);
    }

    public static float getAngleInDegrees(Position origin, Position end) {
        double angle = Math.atan2(end.getY() - origin.getY(), end.getX() - origin.getX());
        // Convert radians to degrees
        angle = Math.toDegrees(angle);
        // Ensure the angle is positive
        if (angle < 0) {
            angle += 360;
        }
        return (float)angle;
    }

    public static float getDistance(Position vector1, Position vector2) {

        double[] vector3 = {vector1.getfX(), vector1.getfY()};
        double[] vector4 = {vector2.getfX(), vector2.getfY()};
        double sum = 0.0;
        for (int i = 0; i < vector3.length; i++) {
            double diff = vector3[i] - vector4[i];
            sum += diff * diff;
        }

        return (float)Math.sqrt(sum);
    }
}