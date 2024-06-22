package ai.state;

import ai.AITransition;
import core.Movement;
import core.Position;
import entity.GameObject;
import game.Game;
import tile.pathfinder.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState {
    private List<Position> targets;
    private pathfinder pf;
    private int currentPathIndex = 1;
    Position targetPosition;

    public Wander() {
        super();
        targets = new ArrayList<>();
        this.pf = new pathfinder();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("Stand", ((game, entity) -> arrived(entity)));
    }

    @Override
    public void update(Game game, GameObject entity) {
        if (targets.isEmpty()) {
            getRandomPosition(game, entity);
        }

        System.out.println("Current Position: " + entity.getPosition().getfX() + ", " + entity.getPosition().getfY());

        move(entity);

        if (arrived(entity)) {
            entity.movement.stop();
        }
    }

    //#region AI Movement
    private void getRandomPosition(Game game, GameObject entity) {
        int x, y;
        do {
            x = (int) (Math.random() * game.getMap().map[0].length);
            y = (int) (Math.random() * game.getMap().map.length);
        } while (game.getMap().map[y][x] != 0 || game.getMap().map[y][x] == 2);

        Position startPosition = entity.getPosition();
        targetPosition = new Position(x * 40, y * 40);
        System.out.println("Target Position: " + targetPosition.getfX() + ", " + targetPosition.getfY());

        targets = pf.findPath(startPosition, targetPosition, game.getMap());
        currentPathIndex = 1;
        System.out.println("CurrentPathIndex: " + currentPathIndex);
        System.out.println("target size: " + targets.size());
        move(entity);
    }

    private void move(GameObject entity) {
        if (currentPathIndex < targets.size()) {
            System.out.println("Moving...");
            Position start = entity.getPosition();
            Position target = targets.get(currentPathIndex);
            entity.movement.MoveTowards(start, target);
            if (entity.getPosition().getfX() == target.getfX() && entity.getPosition().getfY() == target.getfY()) {
                currentPathIndex++;
            }
        }
    }
    //#endregion

    private boolean arrived(GameObject entity) {
        if (entity.getPosition().isInRangeOf(targetPosition)) {
            System.out.println("Arrived at target");
        }
        return entity.getPosition().isInRangeOf(targetPosition);
    }
}
