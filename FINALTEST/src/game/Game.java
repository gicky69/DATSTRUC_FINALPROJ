package game;

import controller.PlayerController;
import core.Position;
import core.collision.RayCast;
import entity.Enemy;
import entity.GameObject;
import entity.Player;
import input.KeyInputs;

import display.GamePanel;
import tile.TileManager;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {

    //#region Back-end Init

    private GamePanel frame;
    private List<GameObject> gameObjects;
    private KeyInputs input;
    private TileManager tilemanager;

    //#endregion

    public Game(int width, int height) {
        input = new KeyInputs();
        frame = new GamePanel(width, height, input);
        gameObjects = new ArrayList<>();
        AddPlayer(new Position(50, 50)); // This adds a player
        AddEnemy(new Position(500, 500)); // This adds an enemy
    }

    //#region Entity Management

    // Getters para mabigay ng class nato yung gameObjects na list.
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Graphics getGraphics() {
        return frame.getGraphics();
    }

    // Getters ng player

    // Adds player
    public void AddPlayer(Position pos) {
        Player player = new Player(pos, new PlayerController(input));
        gameObjects.add(player);
        player.game = this; // Connect the player to the game master
    }

    // Adds enemy
    public void AddEnemy(Position pos) {
        Enemy enemy = new Enemy(pos);
        gameObjects.add(enemy);
        enemy.game = this; // Connect the enemy to the game master
    }

    //#endregion

    //#region Raycast

    float dist = -1;
    List<GameObject> linecasts;

    public List<GameObject> LineCastObjects(Position start, Position end, int layerMask) {
        gameObjects.forEach((object) -> {
            object.getCollision().getCollisionLines().forEach( (LC) -> {
                float getdist = RayCast.getLineCast(start.getfX(), start.getfY(), end.getfX(), end.getfY(),
                        LC.getPoint1().getfX() + object.getPosition().getfX(), LC.getPoint1().getfY() + object.getPosition().getfY(),
                        LC.getPoint2().getfX() + object.getPosition().getfX(), LC.getPoint2().getfY() + object.getPosition().getfY());
                if (getdist<dist || dist==-1) {
                    dist = getdist;
                }
            });
        });
        return linecasts;
    }

    //#endregion

    //#region Back-end

    // Updates each elements ng gameObjects list.
    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    // Renders yung frame which is yung GamePanel.
    public void render() {
        frame.render(this);

    }

    //#endregion
}
