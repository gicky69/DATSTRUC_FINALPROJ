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
        int entityLeftWorldX = entity.getPosition().getX();
        int entityRightWorldX = entity.getPosition().getX();
        int entityTopWorldY = entity.getPosition().getY();
        int entityBottomWorldY = entity.getPosition().getY();

        // this will get the tile position of the entity (left, right, top, bottom)
        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        // will check two sides only when entity hits something
        int tileSide1, tileSide2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY-entity.entitySpeed) / gamePanel.tileSize;
                tileSide1 = gamePanel.tileManager.tileMap[entityTopRow][entityLeftCol];
                tileSide2 = gamePanel.tileManager.tileMap[entityTopRow][entityRightCol];

                // if the conditions are true, push the entity back to its previous position
                if (gamePanel.tileManager.tile[tileSide1].collision || gamePanel.tileManager.tile[tileSide2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY+entity.entitySpeed) / gamePanel.tileSize;
                tileSide1 = gamePanel.tileManager.tileMap[entityBottomRow][entityLeftCol];
                tileSide2 = gamePanel.tileManager.tileMap[entityBottomRow][entityRightCol];

                if (gamePanel.tileManager.tile[tileSide1].collision || gamePanel.tileManager.tile[tileSide2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case "left":
                entityLeftCol = (entityLeftWorldX-entity.entitySpeed) / gamePanel.tileSize;
                tileSide1 = gamePanel.tileManager.tileMap[entityTopRow][entityLeftCol];
                tileSide2 = gamePanel.tileManager.tileMap[entityBottomRow][entityLeftCol];

                if (gamePanel.tileManager.tile[tileSide1].collision || gamePanel.tileManager.tile[tileSide2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case "right":
                entityRightCol = (entityRightWorldX+entity.entitySpeed) / gamePanel.tileSize;
                tileSide1 = gamePanel.tileManager.tileMap[entityTopRow][entityRightCol];
                tileSide2 = gamePanel.tileManager.tileMap[entityBottomRow][entityRightCol];

                if (gamePanel.tileManager.tile[tileSide1].collision || gamePanel.tileManager.tile[tileSide2].collision) {
                    entity.collisionOn = true;
                }
                break;

        }

    }
}