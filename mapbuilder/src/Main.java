import core.Size;
import game.Game;
import game.Loop;

import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Thread(new Loop(new Game(new Size(1600, 1000), 1600, 1000))).start();
    }
}