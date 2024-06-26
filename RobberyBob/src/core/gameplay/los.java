package core.gameplay;

import map.GameMap;

public class los {

    public boolean LineOfSight(int x0, int y0, int x1, int y1, GameMap map, int maxDistance) {
        // Calculate the Euclidean distance
        double distance = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));

        // If the distance is greater than the maximum allowed distance, return false
        if (distance > maxDistance) {
            return false;
        }

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (x0 != x1 || y0 != y1) {
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }

            if (map.map[y0 / 40][x0 / 40] == 2 || map.map[y0 / 40][x0 / 40] == 0) {
                return false;
            }

            if (x0 == x1 && y0 == y1) {
                return true;
            }
        }
        return false;
    }
}
