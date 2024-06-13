package entity;

import display.GamePanel;
import entity.Player;

public class EntityCollision {

    private GamePanel gamePanel;
    private GameObject entity;
    private Player player;

    public EntityCollision(GamePanel gamePanel, GameObject entity, Player player) {
        this.gamePanel = gamePanel;
        this.entity = entity;
        this.player = player;
    }


    // checker
    public void tileChecker(GameObject gameObject) {

        // this will check the leftX, rightX, topY, bottomY of the entity (this is the solid area we're talking about)
//        int entityLeftWorldX = entity.getPosition().getX();
//        int entityRightWorldX = entity.getPosition().getX();
//        int entityTopWorldY = entity.getPosition().getY();
//        int entityBottomWorldY = entity.getPosition().getY();
//
//        // this will get the tile position of the entity (left, right, top, bottom)
//        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
//        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
//        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
//        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        // will check two sides only when entity hits something
        int tileSide1, tileSide2;

        // Calculate new position based on direction
        int newPosX = entity.getPosition().getX();
        int newPosY = entity.getPosition().getY();
        switch (entity.direction) {
            case "up":
                newPosY -= entity.entitySpeed;
                break;
            case "down":
                newPosY += entity.entitySpeed;
                break;
            case "left":
                newPosX -= entity.entitySpeed;
                break;
            case "right":
                newPosX += entity.entitySpeed;
                break;
        }

        // Calculate tile positions
        int newCol = newPosX / gamePanel.tileSize;
        int newRow = newPosY / gamePanel.tileSize;

        // Check for collisions
        tileSide1 = gamePanel.tileManager.tileMap[newRow][newCol];
        if (gamePanel.tileManager.tile[tileSide1].collision) {
            entity.collisionOn = true;
        } else {
            entity.collisionOn = false;
        }
    }
}
