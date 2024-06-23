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
        float tileSizeX = 40;
        float tileSizeY = 40;

        List<Position> path = new ArrayList<>();

        //-1 means its blocked
        //0 means its free
        int width = gameMap.map[0].length;
        int height = gameMap.map.length;
        int[][] moveCosts = new int[width][height];
        int[][] totalCosts = new int[width][height];
        List<Position> openedNodes = new ArrayList<>();

        Position tgpos = new Position (start.gridX(), start.gridY()); //start grid pos
        Position sgpos = new Position (target.gridX(), target.gridY()); //target grid pos
        System.out.println("tgpos: " + tgpos.getX() + ", " + tgpos.getY());

        // Get the walls
        for (int i=0;i<width;i++) {
            for (int j=0;j<height;j++) {
                if (gameMap.map[j][i] == 0 || gameMap.map[j][i] == 2) {
                    totalCosts[i][j] = -1;
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
            path.add(new Position (currentpos.getfX() * tileSizeX + tileSizeX / 2, currentpos.getfY()*tileSizeY + tileSizeY / 2));
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

}