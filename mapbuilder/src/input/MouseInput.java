package input;

import core.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    boolean[] mkeys;
    private Position position;

    public MouseInput() {
        mkeys = new boolean[3];
    }

    public boolean isClicked(int keyCode) {
        return mkeys[keyCode];
    }

    public boolean isHold(int keyCode) {
        return mkeys[keyCode];
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mkeys[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mkeys[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        position = new Position(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}