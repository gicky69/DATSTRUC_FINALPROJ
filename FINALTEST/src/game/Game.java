package game;

import controller.PlayerController;
import core.Position;
import core.collision.RayCast;
import core.collision.RayCastHit;
import entity.Enemy;
import entity.GameObject;
import entity.Player;
import input.KeyInputs;

import display.GamePanel;
import tile.TileManager;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
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
        player.getCollision().setLayerMask(0, true);
        player.name = "Player";
    }

    // Adds enemy
    public void AddEnemy(Position pos) {
        Enemy enemy = new Enemy(pos);
        gameObjects.add(enemy);
        enemy.game = this; // Connect the enemy to the game master
    }

    //#endregion

    //#region Raycasting, Linecasting

    // Work in progress

    float dist = -1;
    List<GameObject> linecasts = new ArrayList<>();
    List<RayCastHit> RayCastHits = new ArrayList<>();

    public List<GameObject> LineCastObjects(Position start, Position end, int layerMask) {
        linecasts.clear();
        RayCastHits.clear();
        gameObjects.forEach((object) -> {
            dist = -1;
            object.getCollision().getCollisionLines().forEach( (LC) -> {
                float getdist = RayCast.getLineCast(start.getfX(), start.getfY(), end.getfX(), end.getfY(),
                        LC.getPoint1().getfX() + object.getPosition().getfX(), LC.getPoint1().getfY() + object.getPosition().getfY(),
                        LC.getPoint2().getfX() + object.getPosition().getfX(), LC.getPoint2().getfY() + object.getPosition().getfY());
                if (getdist<dist || dist<0) {
                    dist = getdist;
                }
                System.out.println("object x: " + (float)(LC.getPoint1().getfX() + object.getPosition().getfX()) + "/ object y: " + (float)(LC.getPoint1().getfY() + object.getPosition().getfY()));
                System.out.println("start x: " + start.getfX() + "/ start y: " + start.getfY());
                System.out.println("end x: " + end.getfX() + "/ end y: " + end.getfY());
            });
            if (dist!=-1) {
                RayCastHits.add(new RayCastHit(object, dist));
            }
            System.out.println("that was, name: " + object.name + "/ distance: " + dist);
        });
        RayCastHits.sort((RayCastHit a1, RayCastHit a2) -> (int) ((a1.distance) - (a2.distance)));
        RayCastHits.forEach((hit) -> {
            if (layerMask == -1) {
                linecasts.add(hit.gameObject);
            }
            else {
                if (hit.gameObject.getCollision().getLayerMask(layerMask)) {
                    linecasts.add(hit.gameObject);
                }
            }
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
