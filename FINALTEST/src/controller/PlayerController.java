package controller;

import java.awt.event.KeyEvent;
import input.KeyInputs;

public class PlayerController implements Controller {

    private KeyInputs input;

    public PlayerController(KeyInputs input) {
        this.input = input;
    }

    // Methods para sa controller, nirreturn kung anong key napindot
    @Override
    public boolean isDefault() {
        return !input.isPressed(KeyEvent.VK_W) && !input.isPressed(KeyEvent.VK_S) && !input.isPressed(KeyEvent.VK_A) && !input.isPressed(KeyEvent.VK_D);
    }

    @Override
    public boolean isRequestingUp() {
        return input.isPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean isRequestingDown() {
        return input.isPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean isRequestingLeft() {
        return input.isPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean isRequestingRight() {
        return input.isPressed(KeyEvent.VK_D);
    }
}