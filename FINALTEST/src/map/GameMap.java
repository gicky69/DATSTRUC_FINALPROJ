package map;

import tile.Tile;
import tile.TileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap {
    TileManager tileManager;
    private List<Tile> gridMap;

    public int map[][];

    public GameMap(TileManager TM) {
        this.tileManager = TM;
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
    //# endregion
}