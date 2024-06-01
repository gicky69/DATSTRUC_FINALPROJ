package paint;

import entity.Player;
import game.Game;
import input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import display.Renderer;
import map.Map;

public class PaintPanel extends JPanel {
    public Player player;

    public final int screenWidth = 1600;
    public final int screenHeight = 900;

    public PaintPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(1600, 900));
        setBackground(Color.GRAY);
        setVisible(true);
    }

    public void update() {

    }
}