package controller;

import input.KeyInput;
import input.MouseInput;

public class PlayerController implements Controller {
    private KeyInput keyInput;

    public PlayerController(KeyInput keyInput) {
        this.keyInput = keyInput;
    }

    @Override
    public boolean isClickedUp() {
        return keyInput.isPressed(87);
    }

    @Override
    public boolean isClickedDown() {
        return keyInput.isPressed(83);
    }

    @Override
    public boolean isClickedLeft() {
        return keyInput.isPressed(65);
    }

    @Override
    public boolean isClickedRight() {
        return keyInput.isPressed(68);
    }
}
