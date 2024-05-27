package game;

import controller.PlayerController;
import core.Position;
<<<<<<< Updated upstream
=======
import core.collision.Physics2D;
import core.collision.Collider;
>>>>>>> Stashed changes
import entity.Enemy;
import entity.GameObject;
import entity.Player;
import input.KeyInputs;

import display.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {
    private GamePanel frame;
    private List<GameObject> gameObjects;
    private KeyInputs input;
<<<<<<< Updated upstream
=======
    private TileManager tilemanager;
    private Physics2D p2d;

    //#endregion
>>>>>>> Stashed changes

    public Game(int width, int height) {
        input = new KeyInputs();
        frame = new GamePanel(width, height, input);
        gameObjects = new ArrayList<>();
<<<<<<< Updated upstream
        gameObjects.add(new Player(new PlayerController(input)));
        gameObjects.add(new Enemy(new Position(500, 500)));
=======
        p2d = new Physics2D();
        p2d.game = this;
        AddPlayer(new Position(50, 50)); // This adds a player
        AddEnemy(new Position(500, 500)); // This adds an enemy
>>>>>>> Stashed changes
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

<<<<<<< Updated upstream
=======
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
        enemy.name = "Enemy";
    }

    //#endregion

    //#region Raycasting, Linecasting

    // Work in progress

    float dist = -1;
    List<GameObject> linecasts = new ArrayList<>();
    List<Collider> colliders = new ArrayList<>();

    public List<GameObject> LineCastObjects(Position start, Position end, int layerMask) {
        linecasts.clear();
        colliders = p2d.getLineCast(start, end, layerMask);
        colliders.forEach((object) -> {
            linecasts.add(object.gameObject);
        });
        return linecasts;
    }

    //#endregion

    //#region Back-end

    // Updates each elements ng gameObjects list.
>>>>>>> Stashed changes
    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    public void render() {
        frame.render(this);
    }
}
