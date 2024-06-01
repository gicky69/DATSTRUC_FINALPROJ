package display;

import input.KeyInput;
import map.Map;
import paint.PaintPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {
    PaintPanel paintPanel;
    private Renderer renderer;

    private Map map;

    public Display(int sheight, int swidth, KeyInput keyInput) {
        paintPanel = new PaintPanel();
        map = new Map(paintPanel);
        setTitle("MTest");
        setSize(sheight, swidth);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addKeyListener(keyInput);
        setFocusable(true);

        add(paintPanel);
    }

    public void update() {
        repaint();
    }

    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        g.dispose();
        bufferStrategy.show();
    }
}