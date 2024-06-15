package game;

import controller.PlayerController;
import core.Position;
import core.Size;
import core.physics2d.Physics2D;
import display.Camera;
import display.SubPanels;
import entity.*;
import input.KeyInputs;

import display.GamePanel;
import map.GameMap;
import menu.RoundPanel;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game {


    //#region Back-end Init

    private GamePanel frame;
    private GameMap map;
    private List<GameObject> gameObjects;
    private KeyInputs input;
    public Physics2D p2d;
    private Camera camera;
    public EntityCollision entityCollision;
    public SubPanels subPanels;
    public RoundPanel roundPanel;
    public boolean isPaused = false;

    //#endregion

    public Game(Size windowsSize, int width, int height, RoundPanel roundPanel) {
        this.roundPanel = roundPanel;
        input = new KeyInputs();
        camera = new Camera(windowsSize);
        subPanels = new SubPanels();
        frame = new GamePanel(width, height, input, camera, this, subPanels, roundPanel);
        map = new GameMap(frame.getTileManager());
        gameObjects = new ArrayList<>();
        p2d = new Physics2D();
        p2d.game = this;


        AddPlayer(new Position(1000, 1000)); // This adds a player
        AddEnemy(new Position(600, 500)); // This adds an enemy
        AddObject(2, new Position(600, 500)); // This creates an object called wall (this is to test the linecast collision)
        AddItem(new Position(500, 1500)); // This creates an item
        entityCollision = new EntityCollision(frame, gameObjects);
    }

    //#region Entity Management

    // Getters para mabigay ng class nato yung gameObjects na list.
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public TileManager getTiles() {
        return frame.getTileManager();
    }

    // Getters ng player

    public Camera getCamera() {
        return camera;
    }

    // Adds player as an object
    public void AddPlayer(Position pos) {
        Player player = new Player(pos, new PlayerController(input), frame, subPanels);
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
        Player player = frame.getPlayer();
        if (player.getController().isPaused()) {
            isPaused = !isPaused;
        }
        if (!isPaused) {
            camera.update(this, frame);
            gameObjects.forEach(GameObject::update);
            map.update();
            entityCollision.tileChecker(gameObjects);
        }
        if (subPanels.roundOver && !isPaused) {
            subPanels.setRoundOverPanel(frame, this);
            isPaused = !isPaused;
        }
    }

    // Renders yung frame which is yung GamePanel.
    public void render() {
        if (!isPaused) {
            frame.render(this);
        }
    }

    public void togglePause() {
        isPaused = !isPaused;
        subPanels.pausePanel.setVisible(true);
    }

    //#endregion
}
