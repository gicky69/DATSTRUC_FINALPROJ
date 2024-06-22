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
        int x = (int) (Math.random() * game.getMap().map[0].length);
        System.out.println("X: " + x);
        int y = (int) (Math.random() * game.getMap().map.length);
        System.out.println("Y: " + y);


        Position startPosition = entity.getPosition();
        Position targetPosition = new Position(x * 40, y * 40);
        System.out.println("Target Position: " + targetPosition.getfX() + ", " + targetPosition.getfY());

        targets = pf.findPath(startPosition, targetPosition, game.getMap());
    }

    private void move(GameObject entity) {
        if (currentPathIndex < targets.size()) {
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
        if (entity.getPosition().isInRangeOf(targets.get(0))) {
            System.out.println("Arrived at target");
        }
        return entity.getPosition().isInRangeOf(targets.get(0));
    }
}
