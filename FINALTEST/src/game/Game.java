package game;

import controller.PlayerController;
import core.Position;
import core.Size;
import core.physics2d.Physics2D;
import core.physics2d.Collider;
import display.Camera;
import entity.Enemy;
import entity.GameObject;
import entity.Player;
import input.KeyInputs;

import display.GamePanel;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {


    //#region Back-end Init

    private GamePanel frame;
    private List<GameObject> gameObjects;
    private KeyInputs input;
    public Physics2D p2d;
    private Camera camera;

    //#endregion

    public Game(Size windowsSize, int width, int height) {
        input = new KeyInputs();
        frame = new GamePanel(width, height, input);
        gameObjects = new ArrayList<>();
        p2d = new Physics2D();
        p2d.game = this;

        camera = new Camera(windowsSize);

        AddPlayer(new Position(100, 100)); // This adds a player
        AddEnemy(new Position(800, 500)); // This adds an enemy
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

    public Camera getCamera() {
        return camera;
    }

    // Adds player as an object
    public void AddPlayer(Position pos) {
        Player player = new Player(pos, new PlayerController(input), frame);
        gameObjects.add(player);
        camera.focusOn(player);
        player.game = this; // Connect the player to the game master
        player.getCollision().setLayerMask(0, true);
        player.name = "Player";
        frame.setPlayer(player);
    }

    // Adds enemy
    public void AddEnemy(Position pos) {
        Enemy enemy = new Enemy(pos);
        gameObjects.add(enemy);
        enemy.game = this; // Connect the enemy to the game master
        enemy.name = "Enemy";
    }

    //#endregion

    //#region Back-end

    // Updates each elements ng gameObjects list.
    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
        camera.update(this);
    }

    // Renders yung frame which is yung GamePanel.
    public void render() {
        frame.render(this);

    }

    //#endregion
}
