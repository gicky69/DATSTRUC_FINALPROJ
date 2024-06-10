package game;

import controller.PlayerController;
import core.Position;
import core.Size;
import core.physics2d.Physics2D;
import core.physics2d.Collider;
import display.Camera;
import entity.*;
import input.KeyInputs;

import display.GamePanel;

import javax.swing.text.html.parser.Entity;
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
    public EntityCollision entityCollision;

    //#endregion

    public Game(Size windowsSize, int width, int height) {
        input = new KeyInputs();
        camera = new Camera(windowsSize);
        frame = new GamePanel(width, height, input, camera);
        gameObjects = new ArrayList<>();
        p2d = new Physics2D();
        p2d.game = this;


        AddPlayer(new Position(1000, 1000)); // This adds a player
        AddEnemy(new Position(600, 500)); // This adds an enemy
        AddObject(2, new Position(600, 500)); // This creates an object called wall (this is to test the linecast collision)
        AddItem(new Position(500, 1500)); // This creates an item
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
        entityCollision = new EntityCollision(frame, player, player);
    }

    // Adds enemy
    public void AddEnemy(Position pos) {
        Enemy enemy = new Enemy(pos);
        gameObjects.add(enemy);
        enemy.game = this; // Connect the enemy to the game master
        enemy.name = "Enemy";
    }

    // Adds an object
    public void AddObject(int itemid, Position pos) {
        Wall wall = new Wall(pos);
        gameObjects.add(wall);
        wall.game = this; // Connect the enemy to the game master
        wall.name = "Wall";
        wall.getCollision().setLayerMask(0, true);
    }

    public void AddItem(Position pos) {
        Item item = new Item(pos);
        gameObjects.add(item);
        item.game = this; // Connect the enemy to the game master
        item.name = "Item";
        item.getCollision().setLayerMask(0, true);
    }

    //#endregion

    //#region Back-end

    // Updates each elements ng gameObjects list.
    public void update() {
        camera.update(this, frame);
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    // Renders yung frame which is yung GamePanel.
    public void render() {
        frame.render(this);
    }

    //#endregion
}
