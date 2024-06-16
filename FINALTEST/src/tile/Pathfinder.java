//package tile;
//
//import core.Position;
//
//import java.util.*;
//
//public class Pathfinder {
//
//    public static Position findPath(Position start, Position target, TileManager gameMap) {
//        final PriorityQueue<Node> open = new PriorityQueue<>();
//        final Set<Node> closed = new HashSet<>();
//        final Node[][] nodeMap = new Node[gameMap.getTiles().length][gameMap.getTiles()[0].length];
//        Node current;
//
//        for(int i=0;i<nodeMap.length;i++) {
//            for (int j = 0; j < nodeMap[0].length; j++) {
//                int heuristic = Math.abs(i - target.gridX()) + Math.abs(j - target.gridY());
//                Node node = new Node(10, heuristic, i, j);
//
//
//                // check if the tile is pathable or not
//                if (gameMap.getTiles()[i][j].isPathable()) {
//                    closed.add(node);
//                }
//
//                nodeMap[i][j] = node;
//            }
//        }
//
//        Node startNode = nodeMap[start.gridX()][start.gridY()];
//        Node targetNode = nodeMap[target.gridX()][target.gridY()];
//
//        open.add(startNode);
//        do {
//            current = open.poll();
//            closed.add(current);
//
//            if (current.equals(targetNode)) {
//                return extractPath(current);
//            }
//
//            for (int i = current.gridX - 1; i < current.gridX + 2; i++) {
//                for (int j = current.gridY - 1; j < current.gridY + 2; j++) {
//                    if (i < 0 || j < 0 || i >= nodeMap.length || j >= nodeMap[0].length) {
//                        continue;
//                    }
//
//                    Node neighbor = nodeMap[i][j];
//                    if (closed.contains(neighbor)) {
//                        continue;
//                    }
//
//                    int moveCost = neighbor.heuristic + neighbor.moveCost + current.totalCost;
//                    if (moveCost < neighbor.totalCost || !open.contains(neighbor)) {
//                        neighbor.totalCost = moveCost;
//                        neighbor.parent = current;
//
//                        if (!open.contains(neighbor)) {
//                            open.add(neighbor);
//                        }
//                    }
//                }
//            }
//
//        }while (!open.isEmpty());
//
//        return start;
//    }
//
//    private static Position extractPath(Node current) {
//        List<Position> path = new ArrayList<>();
//        while (current != null) {
//            path.add(current.getPosition());
//            current = current.parent;
//        }
//        Collections.reverse(path);
//        return path.isEmpty() ? null : path.get(0);
//    }
//
//    private static class Node implements Comparable<Node> {
//        private int moveCost;
//        private int heuristic;
//        private int totalCost;
//        private Node parent;
//        private int gridX;
//        private int gridY;
//
//        public Node(int moveCost, int heuristic, int gridX, int gridY) {
//            this.moveCost = moveCost;
//            this.heuristic = heuristic;
//            this.gridX = gridX;
//            this.gridY = gridY;
//        }
//
//        @Override
//        public int compareTo(Node that) {
//            return Integer.compare(this.totalCost, that.totalCost);
//        }
//
//        public Position getPosition() {
//            return Position.ofGridPosition(gridX, gridY);
//        }
//    }
//}