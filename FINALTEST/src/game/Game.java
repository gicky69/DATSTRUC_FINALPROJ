package game;

import ai.AIManager;
import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import core.physics2d.Physics2D;
import display.Camera;
import display.ImageLoader;
import display.SubPanels;
import entity.*;

import input.KeyInputs;

import display.GamePanel;
import map.GameMap;
import menu.RoundPanel;

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
    private ImageLoader imageLoader;
    public EntityCollision entityCollision;
    public SubPanels subPanels;
    public RoundPanel roundPanel;
    public boolean isPaused = false;

    protected Timer time;
    private AIManager aiManager;
    private NPCController NPCController = new NPCController();
    //#endregion

    public Game(Size windowsSize, int width, int height, RoundPanel roundPanel) {
        this.roundPanel = roundPanel;
        imageLoader = new ImageLoader();
        input = new KeyInputs();
        camera = new Camera(windowsSize);
        subPanels = new SubPanels();
        frame = new GamePanel(width, height, input, camera, this, subPanels, roundPanel, imageLoader);
        map = new GameMap(frame.getTileManager());
        gameObjects = new ArrayList<>();
        p2d = new Physics2D();
        p2d.game = this;

        time = new Timer();
        aiManager = new AIManager();

        AddPlayer(new Position(1500, 1000)); // This adds a player
        AddEnemy(new Position(600, 500)); // This adds an enemy
//        AddObject(2, new Position(600, 500)); // This creates an object called wall (this is to test the linecast collision)
        addWalls();
        AddItem(new Position(500, 1500)); // This creates an item
        AddItem(new Position(1000, 1000));
        entityCollision = new EntityCollision(frame);
    }

    //#region Entity Management

    // Getters para mabigay ng class nato yung gameObjects na list.
    public List<GameObject> getGameObjects() {
        return gameObjects;
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
        enemy.setController(NPCController);
    }

    // Adds an object
    public void AddObject(int itemid, Position pos) {
        Wall wall = new Wall(pos);
        gameObjects.add(wall);
        wall.game = this; // Connect the enemy to the game master
        wall.name = "Wall";
//        wall.getCollision().setLayerMask(0, true);
    }

    public void addWalls() {
//        int[][] tiles = map.map;
//        for (int y = 0; y < tiles.length; y++) {
//            for (int x = 0; x < tiles[y].length; x++) {
//                // add each walls
//                if (tiles[y][x] == 0 || tiles[y][x] == 2) {
//                    AddObject(2, new Position(x * 40, y * 40));
//                }
//            }
//        }
    }

    public void AddItem(Position pos) {
        Item item = new Item(pos, roundPanel);
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
            // GameObjects enemy index = 1
            aiManager.update(this, gameObjects.get(1));
            entityCollision.tileChecker(gameObjects);
        }
        if (subPanels.roundOver && !isPaused) {
            subPanels.setRoundOverPanel(frame, this, roundPanel);
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


    //#region Getters
    public GameMap getMap() {
        return map;
    }

    public Timer getTime() {
        return time;
    }

    //#endregion
}
