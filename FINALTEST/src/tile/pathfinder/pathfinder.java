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

    /**
     * <p>
     * printInfo,
     * 1 = will show path count
     * 2 = will show path count and position by position pathing
     * <p>
     */
    public static int printInfo = 0;
    public static List<Position> findPath(Position start, Position target, GameMap gameMap) {

        /*
        final PriorityQueue<Node> open = new PriorityQueue<>();
        final Set<Node> closed = new HashSet<>();
        final Node[][] nodeMap = new Node[gameMap.map.length][gameMap.map[0].length];
        Node current;

        for(int i=0;i<nodeMap.length;i++) {
            for (int j = 0; j < nodeMap[0].length; j++) {
                int heuristic = Math.abs(i - target.gridX()) + Math.abs(j - target.gridY());
                Node node = new Node(10, heuristic, i, j);


                // check if the tile is pathable or not
                if (gameMap.map[i][j] == 0) {
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

         */

        float tileSizeX = 40;
        float tileSizeY = 40;

        List<Position> path = new ArrayList<>();

        //-1 means its blocked
        //0 means its free
        int width = gameMap.map.length;
        int height = gameMap.map[0].length;
        int[][] moveCosts = new int[height][width];
        int[][] totalCosts = new int[height][width];
        List<Position> openedNodes = new ArrayList<>();

        Position tgpos = new Position (start.gridX(), start.gridY()); //start grid pos
        Position sgpos = new Position (target.gridX(), target.gridY()); //target grid pos

        // Get the walls
        for(int i=0;i<totalCosts[0].length;i++) {
            for (int j = 0; j < totalCosts.length; j++) {

                // check if the tile is pathable or not
                if (gameMap.map[i][j] == 0 || gameMap.map[i][j] == 2) {
                    totalCosts[j][i] = -1;
                }
            }
        }

        //System.out.println("size (" + width + ", " + height + ")");

        //System.out.println("start: (" + sgpos.getX() + ", " + sgpos.getY() + ")");
        //System.out.println("target: (" + tgpos.getX() + ", " + tgpos.getY() + ")");
        int pathc = 0;

        openedNodes.add(sgpos);
        totalCosts[tgpos.getX()][tgpos.getY()] = -2; //Set the identifier for target node

        // Scan to create pathfinding
        while (true) {

            //To find the node with the lowest cost
            int currentNodeCost = -1; //Total Cost
            int currentMoveCost = 0; //Move Cost
            Position currentNode = new Position(0,0);

            //Find the lowest cost node among the opened nodes
            for (Position openedNode : openedNodes) {
                int nodex = openedNode.getX();
                int nodey = openedNode.getY();
                if (currentNodeCost == -1) { //First
                    currentNodeCost = totalCosts[nodex][nodey];
                    currentNode = openedNode;
                    currentMoveCost = moveCosts[nodex][nodey];
                } else { // Compare if lower
                    if (currentNodeCost > totalCosts[nodex][nodey]) {
                        currentNodeCost = totalCosts[nodex][nodey];
                        currentNode = openedNode;
                        currentMoveCost = moveCosts[nodex][nodey];
                    }
                }
            }

            int nodex = currentNode.getX();
            int nodey = currentNode.getY();

            int cnodex;
            int cnodey;

            int heuristic;
            int moveCost;
            int totalCost;

            //region Check for target node

            // Non-diagonal
            // Up
            cnodex = nodex;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == -2) {
                    break;
                }
            }
            // Left
            cnodex = nodex-1;
            cnodey = nodey;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == -2) {
                    break;
                }
            }
            // Right
            cnodex = nodex+1;
            cnodey = nodey;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == -2) {
                    break;
                }
            }
            // Down
            cnodex = nodex;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == -2) {
                    break;
                }
            }


            // Diagonal
            // Up Left
            cnodex = nodex-1;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == -2) {
                        break;
                    }
                }
            }
            // Up Right
            cnodex = nodex+1;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == -2) {
                        break;
                    }
                }
            }
            // Down Left
            cnodex = nodex-1;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == -2) {
                        break;
                    }
                }
            }
            // Down Right
            cnodex = nodex+1;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == -2) {
                        break;
                    }
                }
            }

            //endregion

            //region Open the nodes

            // Non-diagonal
            // Up
            cnodex = nodex;
            cnodey = nodey-1;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 10; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                    totalCosts[cnodex][cnodey] = totalCost;
                    moveCosts[cnodex][cnodey] = moveCost;
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }
            // Left
            cnodex = nodex-1;
            cnodey = nodey;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 10; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                    totalCosts[cnodex][cnodey] = totalCost;
                    moveCosts[cnodex][cnodey] = moveCost;
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }
            // Right
            cnodex = nodex+1;
            cnodey = nodey;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 10; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                    totalCosts[cnodex][cnodey] = totalCost;
                    moveCosts[cnodex][cnodey] = moveCost;
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }
            // Down
            cnodex = nodex;
            cnodey = nodey+1;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 10; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                    totalCosts[cnodex][cnodey] = totalCost;
                    moveCosts[cnodex][cnodey] = moveCost;
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }


            // Diagonal
            // Up Left
            cnodex = nodex-1;
            cnodey = nodey-1;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 14; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                        totalCosts[cnodex][cnodey] = totalCost;
                        moveCosts[cnodex][cnodey] = moveCost;
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }
            // Up Right
            cnodex = nodex+1;
            cnodey = nodey-1;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 14; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                        totalCosts[cnodex][cnodey] = totalCost;
                        moveCosts[cnodex][cnodey] = moveCost;
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }
            // Down Left
            cnodex = nodex-1;
            cnodey = nodey+1;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 14; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                        totalCosts[cnodex][cnodey] = totalCost;
                        moveCosts[cnodex][cnodey] = moveCost;
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }
            // Down Right
            cnodex = nodex+1;
            cnodey = nodey+1;
            heuristic = getHeuristic(new Position(cnodex, cnodey), tgpos); // Get Heuristic
            moveCost = currentMoveCost + 14; // Get Move Cost: Current Move Cost + New Move
            totalCost = heuristic + moveCost; // Get Total Cost: New Heuristic + Move Cost
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (totalCosts[cnodex][cnodey] == 0 || ((totalCosts[cnodex][cnodey] > totalCost) && totalCost>0)) {
                        totalCosts[cnodex][cnodey] = totalCost;
                        moveCosts[cnodex][cnodey] = moveCost;
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }

            //endregion

            openedNodes.remove(currentNode);
            //pathc++;
        }
        //System.out.println("iterations: " + pathc);
        //pathc = 0;

        moveCosts[sgpos.getX()][sgpos.getY()] = -2; //Set the identifier for start node

        openedNodes.clear();
        Position currentpos = tgpos;

        int countup = 0;
        // Draw a path from the end to start
        while (true) {
            openedNodes.clear();

            int nodex = currentpos.getX();
            int nodey = currentpos.getY();

            int cnodex;
            int cnodey;

            //region Check start node

            // Non-diagonal
            // Up
            cnodex = nodex;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] == -2) {
                    path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                    break;
                }
            }
            // Left
            cnodex = nodex-1;
            cnodey = nodey;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] == -2) {
                    path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                    break;
                }
            }
            // Right
            cnodex = nodex+1;
            cnodey = nodey;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] == -2) {
                    path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                    break;
                }
            }
            // Down
            cnodex = nodex;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] == -2) {
                    path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                    break;
                }
            }


            // Diagonal
            // Up Left
            cnodex = nodex-1;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] == -2) {
                        path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                        break;
                    }
                }
            }
            // Up Right
            cnodex = nodex+1;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] == -2) {
                        path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                        break;
                    }
                }
            }
            // Down Left
            cnodex = nodex-1;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] == -2) {
                        path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                        break;
                    }
                }
            }
            // Down Right
            cnodex = nodex+1;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] == -2) {
                        path.add(new Position(cnodex*tileSizeX, cnodey*tileSizeX));
                        break;
                    }
                }
            }

            //endregion

            //region Check for available paths

            // Non-diagonal
            // Up
            cnodex = nodex;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] > 0) {
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }
            // Left
            cnodex = nodex-1;
            cnodey = nodey;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] > 0) {
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }
            // Right
            cnodex = nodex+1;
            cnodey = nodey;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] > 0) {
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }
            // Down
            cnodex = nodex;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (moveCosts[cnodex][cnodey] > 0) {
                    openedNodes.add(new Position(cnodex, cnodey));
                }
            }


            // Diagonal
            // Up Left
            cnodex = nodex-1;
            cnodey = nodey-1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] > 0) {
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }
            // Up Right
            cnodex = nodex+1;
            cnodey = nodey-1;
            if (totalCosts[cnodex][nodey]!=-1 && totalCosts[nodex][cnodey]!=-1) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] > 0) {
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }
            // Down Left
            cnodex = nodex-1;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] > 0) {
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }
            // Down Right
            cnodex = nodex+1;
            cnodey = nodey+1;
            if (cnodex>=0 && cnodey>=0 && cnodex<width && cnodey<height) {
                if (totalCosts[cnodex][nodey] != -1 && totalCosts[nodex][cnodey] != -1) {
                    if (moveCosts[cnodex][cnodey] > 0) {
                        openedNodes.add(new Position(cnodex, cnodey));
                    }
                }
            }

            //endregion

            int currentMoveCost = -1; //Move Cost
            Position currentNode = new Position(0,0);

            //Find the lowest movement cost node among the opened nodes
            for (Position openedNode : openedNodes) {
                nodex = openedNode.getX();
                nodey = openedNode.getY();
                if (currentMoveCost == -1) { //First
                    currentMoveCost = moveCosts[nodex][nodey];
                    currentNode = openedNode;
                } else { // Compare if lower
                    if (currentMoveCost > moveCosts[nodex][nodey]) {
                        currentMoveCost = moveCosts[nodex][nodey];
                        currentNode = openedNode;
                    }
                }
            }
            currentpos = currentNode;
            path.add(new Position (currentpos.getfX()*tileSizeX, currentpos.getfY()*tileSizeY));
            pathc++;
            //System.out.println("path cost: " + currentMoveCost);
            if (currentMoveCost==-1) {
                break;
            }
        }

        if (printInfo > 0) {
            System.out.println("Path count: " + pathc);
            System.out.println("Path: ");
            if (printInfo > 1) {
                for (Position pat : path) {
                    System.out.print("(" + pat.getX() + ", " + pat.getY() + ")>");
                }
            }

//            System.out.println("");
//            for(int i=0;i< 20;i++) {
//                System.out.println("");
//                for (int j = 0; j < 20; j++) {
//
//                    int relx = j+(int)(path.getFirst().getX()/40f)-10;
//                    int rely = i+(int)(path.getFirst().getY()/40f)-10;
//                    //System.out.println("relx: (" + relx + ")");
//
//                    // check if the tile is pathable or not
//                    if (relx>0 && rely>0 && relx<width && rely<height) {
//                        if (totalCosts[relx][rely] == -1) {
//                            System.out.print("1");
//                        } else {
//                            System.out.print("0");
//                        }
//                    }
//                }
//            }
        }

//        for(int i=0;i<totalCosts.length;i++) {
//            System.out.println("");
//            for (int j = 0; j < totalCosts[0].length; j++) {
//
//                // check if the tile is pathable or not
//                if (totalCosts[i][j] == -1) {
//                    System.out.print("1");
//                } else { System.out.print("0"); }
//            }
//        }

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

    /*
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
     */
}