package entity;

import core.Position;

import javax.swing.*;
import java.awt.*;

public class Item extends GameObject {

    private ImageIcon itemImage;
    private boolean isActive;

    public Item(Position position) {
        this.position = position; // from gameobject position, instantiate the position of the item
        itemImage = new ImageIcon("FINALTEST/images/Items/money.gif");
        this.isActive = true;
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
                System.out.println("ITEM COLLECTED");
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
