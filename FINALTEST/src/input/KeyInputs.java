package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
    boolean[] keys;

    public KeyInputs() {
        keys = new boolean[256];
    }

    public boolean isPressed(int keyCode){
        return keys[keyCode];
    }

    public boolean isReleased(int keyCode){
        return !keys[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }




}