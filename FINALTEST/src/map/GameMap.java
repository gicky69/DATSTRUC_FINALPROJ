package map;

import core.Position;
import game.Game;
import tile.Tile;
import tile.TileManager;
import tile.pathfinder.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    TileManager tileManager;

    private List<Tile> gridMap;
    private pathfinder pf;
    private List<Position> pathToFollow;

    // Game
    private Game game;

    public int map[][];

    public GameMap(TileManager TM) {
        this.tileManager = TM;
        this.pf = new pathfinder();
        map = tileManager.getMap();
        setGameTiles();
    }

    // update map
    public void update() {

    }

    //# region Set Game Map Attributes

    public void setGameTiles() {
        gridMap = new ArrayList<>(); // Initialize the gridMap
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                // set the size to be a grid
                Tile tile = new Tile();
                tile.setGridX(j*40);
                tile.setGridY(i*40);
                gridMap.add(tile);
            }
        }
    }

    public void isPathable(int x, int y) {
        if (map[y][x] == 1) {
            System.out.println("Pathable");
        } else {
            System.out.println("Not Pathable");
        }

    }

    //# endregion
}