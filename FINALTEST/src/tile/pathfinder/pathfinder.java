//package tile.pathfinder;
//
//import core.Position;
//import map.GameMap;
//
//import java.util.*;
//
//public class pathfinder {
//
//    public List<Node> findPath(Node start, Node target, GameMap gameMap) {
//        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparing(node -> node.cost + node.heuristic));
//        Set<Node> closedSet = new HashSet<>();
//        Node[][] nodeMap = new Node[gameMap.map.length][gameMap.map[0].length];
//        Node current;
//
//        openSet.add(start);
//
//        for (int i=0;i<nodeMap.length;i++){
//            for (int j=0;j<nodeMap[0].length; j++){
//                int heuristic = Math.abs(i - target.x) + Math.abs(j - target.y);
//                Node node = new Node(i, j, Float.MAX_VALUE, heuristic, null);
//
//                if (gameMap.map[i][j] == 1){
//                    closedSet.add(node);
//                }
//
//                nodeMap[i][j] = node;
//            }
//        }
//
//        while (!openSet.isEmpty()) {
//            current = openSet.poll();
//
//            if (current.equals(target)) {
//                return extractPath(current);
//            }
//
//            closedSet.add(current);
//
//            for (Node neighbor : getNeighbors(current, gameMap)) {
//                if (closedSet.contains(neighbor)) {
//                    continue;
//                }
//
//                float tentativeCost = current.cost + heuristic(current, neighbor);
//
//                if (tentativeCost < neighbor.cost || !openSet.contains(neighbor)) {
//                    neighbor.cost = tentativeCost;
//                    neighbor.parent = current;
//
//                    if (!openSet.contains(neighbor)) {
//                        openSet.add(neighbor);
//                    }
//                }
//            }
//        }
//
//        return new ArrayList<>(); // Return an empty list if no path is found
//    }
//
//    private List<Node> extractPath(Node target) {
//        List<Node> path = new ArrayList<>();
//
//        for (Node node = target; node != null; node = node.parent) {
//            path.add(node);
//        }
//
//        Collections.reverse(path);
//        return path;
//    }
//
//    private List<Node> getNeighbors(Node node, GameMap gameMap) {
//        List<Node> neighbors = new ArrayList<>();
//        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//
//        for (int[] dir : directions) {
//            int x = (node.x + dir[0]) / 80;
//            int y = (node.y + dir[1]) / 40;
//
//            if (x < 0 || y < 0 || x >= gameMap.map.length || y >= gameMap.map[0].length) {
//                continue;
//            }
//
//            if (gameMap.map[x][y] == 1) {
//                Node neighbor = new Node(x * 80, y * 40, Float.MAX_VALUE, 0, null); // Convert tile coordinates back to pixel coordinates for Node
//                neighbors.add(neighbor);
//            }
//        }
//
//        return neighbors;
//    }
//
//    private float heuristic(Node node, Node target) {
//        return Math.abs(node.x - target.x) + Math.abs(node.y - target.y);
//    }
//
//    public static Node positionToNode(Position position) {
//        int x = position.getX();
//        int y = position.getY();
//        return new Node(x, y, Float.MAX_VALUE, 0, null);
//    }
//
//    // Ensure Node class overrides equals and hashCode for correct comparison in sets
//    public static class Node {
//        int x, y;
//        float cost, heuristic;
//        Node parent;
//
//        public Node(int x, int y, float cost, float heuristic, Node parent) {
//            this.x = x;
//            this.y = y;
//            this.cost = cost;
//            this.heuristic = heuristic;
//            this.parent = parent;
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if (this == obj) return true;
//            if (obj == null || getClass() != obj.getClass()) return false;
//            Node node = (Node) obj;
//            return x == node.x && y == node.y;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(x, y);
//        }
//    }
//}


package tile.pathfinder;

import core.Position;
import map.GameMap;

import java.util.*;

public class pathfinder {

    public static List<Position> findPath(Position start, Position target, GameMap gameMap) {
        final PriorityQueue<Node> open = new PriorityQueue<>();
        final Set<Node> closed = new HashSet<>();
        final Node[][] nodeMap = new Node[gameMap.map.length][gameMap.map[0].length];
        Node current;

        for(int i=0;i<nodeMap.length;i++) {
            for (int j = 0; j < nodeMap[0].length; j++) {
                int heuristic = Math.abs(i - target.gridX()) + Math.abs(j - target.gridY());
                Node node = new Node(10, heuristic, i, j);


                // check if the tile is pathable or not
                if (gameMap.map[i][j] == 1) {
                    closed.add(node);
                }

                nodeMap[i][j] = node;
            }
        }

        Node startNode = nodeMap[start.gridX()][start.gridY()];
        Node targetNode = nodeMap[target.gridX()][target.gridY()];

        open.add(startNode);
        do {
            current = open.poll();
            closed.add(current);

            if (current.equals(targetNode)) {
                return extractPath(current);
            }

            for (int i = current.gridX - 1; i < current.gridX + 2; i++) {
                for (int j = current.gridY - 1; j < current.gridY + 2; j++) {
                    if (i < 0 || j < 0 || i >= nodeMap.length || j >= nodeMap[0].length) {
                        continue;
                    }

                    Node neighbor = nodeMap[i][j];
                    if (closed.contains(neighbor)) {
                        continue;
                    }

                    int moveCost = neighbor.heuristic + neighbor.moveCost + current.totalCost;
                    if (moveCost < neighbor.totalCost || !open.contains(neighbor)) {
                        neighbor.totalCost = moveCost;
                        neighbor.parent = current;

                        if (!open.contains(neighbor)) {
                            open.add(neighbor);
                        }
                    }
                }
            }
        }   while (!open.isEmpty());
        return List.of(start);
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

    public static class Node implements Comparable<Node> {
        private int moveCost;
        private int heuristic;
        private int totalCost;
        private Node parent;
        private int gridX;
        private int gridY;

        public Node(int moveCost, int heuristic, int gridX, int gridY) {
            this.moveCost = moveCost;
            this.heuristic = heuristic;
            this.gridX = gridX;
            this.gridY = gridY;
        }

        public int getGridX() {
            return gridX;
        }

        public int getGridY() {
            return gridY;
        }

        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.totalCost, that.totalCost);
        }

        public Position getPosition() {
            return Position.ofGridPosition(gridX, gridY);
        }
    }
}