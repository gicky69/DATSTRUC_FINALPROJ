package entity;

import display.GamePanel;
import entity.Player;

import java.util.List;

public class EntityCollision {

    private GamePanel gamePanel;
    private List<GameObject> entity;

    public EntityCollision(GamePanel gamePanel, List<GameObject> entity) {
        this.gamePanel = gamePanel;
        this.entity = entity;
    }


    // checker
    public void tileChecker(List<GameObject> gameObjects) {
        for (GameObject entity : gameObjects) {
            // Calculate new position
            int newPosX = entity.getPosition().getX();
            int newPosY = entity.getPosition().getY();

            // Adjust position based on direction
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
            boolean isWithinBounds = newRow >= 0 && newRow < gamePanel.tileManager.tileMap.length
                    && newCol >= 0 && newCol < gamePanel.tileManager.tileMap[0].length;

            if (isWithinBounds) {
                int tileSide1 = gamePanel.tileManager.tileMap[newRow][newCol];
                boolean isCollision = gamePanel.tileManager.tile[tileSide1].collision;

                entity.collisionOn = isCollision;
            } else {
                System.out.println("Invalid entity position: (" + newPosX + ", " + newPosY + ")");
            }
        }
    }
}
