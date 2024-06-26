package game;

import ai.AIManager;
import controller.NPCController;
import controller.PlayerController;
import core.Position;
import core.Size;
import core.gameplay.los;
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

    private GamePanel gamePanel;
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

    private Player player;

    protected Timer time;
    private AIManager AIManager;
    private List<AIManager> aiManagers;
    private NPCController NPCController = new NPCController();
    //#endregion

    public Game(Size windowsSize, int width, int height, RoundPanel roundPanel) {
        this.roundPanel = roundPanel;
        subPanels = new SubPanels();
        imageLoader = new ImageLoader();
        input = new KeyInputs();
        camera = new Camera(windowsSize);
        gamePanel = new GamePanel(width, height, input, camera, this, subPanels, roundPanel, imageLoader);
        map = new GameMap(gamePanel.getTileManager());
        gameObjects = new ArrayList<>();
        p2d = new Physics2D();
        p2d.game = this;
        time = new Timer();
        aiManagers = new ArrayList<>();


        AddPlayer(new Position(1500, 1000)); // This adds a player

        // Set Enemy Position to a valid position
        int x = 0, y = 0;
        for (int i=0;i< 5;i++) {
            do {
                x = randomPositionX();
                y = randomPositionY();
                AddEnemy(new Position(x * 40, y * 40));
            }
            while ((getMap().map[y][x] == 0 || getMap().map[y][x] == 2)
                    || x > 50 && x < 3150
                    || y > 50 && y < 1500);
        }

        // Add Items
        int x1 = 0, y1 = 0;
        for (int i=0;i< 5;i++) {
            do {
                x1 = randomPositionX();
                y1 = randomPositionY();
                AddItem(new Position(x1 * 40, y1 * 40));
            }
            while ((getMap().map[y1][x1] == 0 || getMap().map[y1][x1] == 2)
                    || x1 > 50 && x1 < 3150
                    || y1 > 50 && y1 < 1500);
        }


//        AddObject(2, new Position(600, 500)); // This creates an object called wall (this is to test the linecast collision)
        addWalls();
        entityCollision = new EntityCollision(gamePanel);
    }

    private int randomPositionY() {
        return 1 + (int) (Math.random() * 38);
    }

    private int randomPositionX() {
        return 1 + (int) (Math.random() * 78);
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
        Player player = new Player(pos, new PlayerController(input), gamePanel, subPanels);
        gameObjects.add(player);
        camera.focusOn(player);
        player.game = this; // Connect the player to the game master
        player.getCollision().setLayerMask(0, true);
        player.name = "Player";
        gamePanel.setPlayer(player);
    }

    // Adds enemy
    public void AddEnemy(Position pos) {
        Enemy enemy = new Enemy(pos, gamePanel, subPanels);
        gameObjects.add(enemy);
        enemy.game = this; // Connect the enemy to the game master
        enemy.name = "Enemy";
        enemy.setController(NPCController);
        AIManager aiManager = new AIManager();
        aiManagers.add(aiManager);
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
        Player player = gamePanel.getPlayer();
        if (player.getController().isPaused()) {
            isPaused = !isPaused;
        }
        if (!isPaused) {
            camera.update(this, gamePanel);
            gameObjects.forEach(GameObject::update);
            map.update();
            // GameObjects enemy index = 1
            // Update each AIManager
            for (int i = 0; i < aiManagers.size(); i++) {
                aiManagers.get(i).update(this, gameObjects.get(i + 1)); // i + 1 because the first GameObject is the player
            }
            entityCollision.tileChecker(gameObjects);
        }
        if (subPanels.roundOver && !isPaused) {
            subPanels.roundOverPanel.setVisible(true);
            isPaused = !isPaused;
        }
    }

    // Renders yung frame which is yung GamePanel.
    public void render() {
        if (!isPaused) {
            gamePanel.render(this);
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

    public Player getPlayer() {
        return (Player) gameObjects.get(0);
    }

    public int getRoundDetail() {
        return roundPanel.roundDetail;
    }

    //#endregion

}
