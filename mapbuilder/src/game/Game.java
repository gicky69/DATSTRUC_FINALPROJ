package game;

import controller.PlayerController;
import core.Position;
import core.Size;
import display.Camera;
import display.Display;
import entity.GameObject;
import entity.Player;
import input.KeyInput;
import input.MouseInput;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Display main;
    private MouseInput mouseInput;
    private KeyInput keyInput;
    private Camera camera;

    private List<GameObject> gameObjects = new ArrayList<>();

    public Game(Size windowSize, int width, int height) {
        keyInput = new KeyInput();
        mouseInput = new MouseInput();
        main = new Display(width, height,  keyInput);

        gameObjects = new ArrayList<>();

        camera = new Camera(windowSize);

        AddPlayer(new Position(800, 450));
    }

    public BufferStrategy getBufferStrategy() {
        return main.getBufferStrategy();
    }

    public Camera getCamera() {
        return camera;
    }

    public void AddPlayer(Position pos) {
        Player player = new Player(pos, new PlayerController(keyInput));
        gameObjects.add(player);
        player.game = this;
        camera.focusOn(player);

    }

    public void update() {
        main.update();
        gameObjects.forEach(gameObjects -> gameObjects.update());
        camera.update(this);
    }

    public void render() {

    }

}
