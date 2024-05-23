package game;

import controller.PlayerController;
import core.Position;
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

    public Game(int width, int height) {
        input = new KeyInputs();
        frame = new GamePanel(width, height, input);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Player(new PlayerController(input)));
        gameObjects.add(new Enemy(new Position(500, 500)));
    }

    // Getters para mabigay ng class nato yung gameObjects na list.
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    // Updates each elements ng gameObjects list.
    public void update() {
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    // Renders yung frame which is yung GamePanel.
    public void render() {
        frame.render(this);
    }
}
