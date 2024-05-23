package game;

import controller.PlayerController;
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

    public Game(int width, int height) {
        input = new KeyInputs();
        frame = new GamePanel(width, height, input);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Player(new PlayerController(input)));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    public void render() {
        frame.render(this);
    }
}
