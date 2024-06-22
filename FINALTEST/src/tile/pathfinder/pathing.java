package tile.pathfinder;

import core.Position;
import map.GameMap;

import java.sql.Array;
import java.util.*;

public class pathing {

    private static final int DIAGONAL_COST = 14;
    private static final int V_H_COST = 10;
    private static int printInfo = 0;

    private static class Node {
        int moveCost;
        int totalCost;
        private Node parent;
        private Position position;
        int gridX;
        int gridY;
        int heuristic;

        public Position getPosition() {
            return new Position(gridX, gridY);
        }

        Node(int gridX, int gridY, int moveCost, int heuristic) {
            this.gridX = gridX;
            this.gridY = gridY;
            this.moveCost = moveCost;
            this.heuristic = heuristic;

        }

        private static class NodeComparator implements Comparator<Node> {
            public int compare(Node node1, Node node2) {
                return Integer.compare(node1.totalCost, node2.totalCost);
            }
        }
    }

    public static List<Position> findPath(Position start, Position end, GameMap gameMap) {
        final PriorityQueue<Node> open = new PriorityQueue<>(new Node.NodeComparator());
        final Set<Node> closed = new HashSet<>();
        final Node[][] nodeMap = new Node[gameMap.map.length][gameMap.map[0].length];
        Node current;

        // initialize nodeMap
        for (int i = 0; i< gameMap.map.length;i++) {
            for (int j = 0;j < gameMap.map[i].length;j++) {
                int heuristic = Math.abs(i - end.getX()) + Math.abs(j - end.getY());
                Node node = new Node(i, j, 10, heuristic);

                if (gameMap.map[i][j] == 0 || gameMap.map[i][j] == 2) {
                    closed.add(node);
                }

                nodeMap[i][j] = node;
            }
        }

        // add start node to open
        Node startNode = nodeMap[(int)start.gridX()][(int)start.gridY()];
        Node targetNode = nodeMap[(int)end.gridX()][(int)end.gridY()];

        open.add(startNode);

        do {
            current = open.poll();
            closed.add(current);

            if (current == targetNode) {
                return extractPath(current);
            }

            for (int x = current.gridX - 1;  x < current.gridX + 2; x++) {
                for (int y = current.gridY - 1; y < current.gridY + 2; y++) {
                    if (x == current.gridX && y == current.gridY) {
                        continue;
                    }

                    if (x < 0 || x >= gameMap.map.length || y < 0 || y >= gameMap.map[0].length) {
                        continue;
                    }

                    Node neighbor = nodeMap[x][y];
                    if (closed.contains(neighbor)) {
                        continue;
                    }

                    int calculatedCost = neighbor.heuristic + neighbor.moveCost + current.totalCost;

                    if (calculatedCost < neighbor.totalCost || !open.contains(neighbor)) {
                        neighbor.totalCost = calculatedCost;
                        neighbor.parent = current;

                        if (!open.contains(neighbor)) {
                            open.add(neighbor);
                        }
                    }

                }
            }
        }while (!open.isEmpty());


        return(null);
    }


    private static List<Position> extractPath(Node current) {
        List<Position> path = new ArrayList<>();
        while (current.parent != null) {
            path.add(current.getPosition());
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }


    static int getHeuristic (Position current, Position end) {
        int cx = current.getX();
        int cy = current.getY();
        int ex = end.getX();
        int ey = end.getY();
        int tx = cx - ex;
        int ty = cy - ey;

        int adiag = Math.abs(tx - ty);
        int diag = 0;
        if (tx>ty) {
            diag = tx - adiag;
        }
        else if (ty>tx) {
            diag = ty - adiag;
        }
        int heuristic = (diag*14) + (adiag*10);
        return heuristic;
    }
}
