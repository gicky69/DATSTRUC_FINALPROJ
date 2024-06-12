package buttons;

import map.Map;

import javax.swing.*;
import java.awt.*;

public class buttonsFrame extends JFrame {
    JButton saveButton;
    JButton loadButton;
    JButton createMapButton;
    JButton loadMapButton;
    JButton exitButton;

    private Map map;

    public buttonsFrame(Map map) {
        this.map = map;
        setTitle("Buttons");
        setLayout(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        saveButton = new JButton("Save");
        saveButton.setBounds(50, 50, 100, 50);
        add(saveButton);

        saveButton.addActionListener(e -> {
            System.out.println("Save button clicked");
            map.saveMap();
        });

        loadButton = new JButton("Load");
        loadButton.setBounds(50, 150, 100, 50);
        add(loadButton);

        loadButton.addActionListener(e -> {
            System.out.println("Load button clicked");
            map.loadMap();
        });

        createMapButton = new JButton("Create Map");
        createMapButton.setBounds(50, 250, 100, 50);
        add(createMapButton);

        createMapButton.addActionListener(e -> {
            map.createMap();
        });

        exitButton = new JButton("Exit");
        exitButton.setBounds(200, 150, 100, 50);
        add(exitButton);

        exitButton.addActionListener(e -> {
            System.out.println("Exit button clicked");
            System.exit(0);
        });

        setVisible(true);
        setResizable(false);
    }
}
