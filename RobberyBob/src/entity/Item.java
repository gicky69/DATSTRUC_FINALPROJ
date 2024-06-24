package entity;

import core.Position;
import menu.RoundPanel;

import javax.swing.*;
import java.awt.*;

public class Item extends GameObject {

    private ImageIcon itemImage;
    private boolean isActive;
    private RoundPanel roundPanel;

    public Item(Position position, RoundPanel roundPanel) {
        this.position = position; // from gameobject position, instantiate the position of the item
        itemImage = new ImageIcon("RobberyBob/resources/images/Items/money.gif");
        this.isActive = true;
        this.roundPanel = roundPanel;

        JLabel itemCollectedLabel = new JLabel();
        itemCollectedLabel.setBounds(10, 10, 200, 30); // Set the position and size of the JLabel

    }


    @Override
    public void update() {
        Player player = (Player) game.getGameObjects().get(0); // Get Player

        // will only run if the item is active or not collected
        if (isActive) {
            if (player.getBounds().intersects(this.getBounds())) {
                player.itemCollected = true; // set player collection status to true
                this.isActive = false; // make the item cold after collection
                itemImage = null; // remove the item image on the map
                player.itemsCollected++;

            }
        }

    }

    @Override
    public Image getSprite() {
        if (itemImage != null) {
            return itemImage.getImage();
        } else {
            return null;
        }
    }

}
