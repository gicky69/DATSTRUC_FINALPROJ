package ai.state;

import ai.AITransition;
import entity.GameObject;
import game.Game;
import core.Position;
import tile.pathfinder.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Pursue extends AIState {

    private List<Position> paths;
    private final pathfinder pf;
    private int currentPathIndex = 1;

    public Pursue() {
        super();
        paths = new ArrayList<>();
        this.pf = new pathfinder();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("Stand", ((game, entity) -> arrived(entity)));
    }

    @Override
    public void update(Game game, GameObject entity) {
        move(game, entity);
    }


    public void move(Game game, GameObject entity) {
        System.out.println("Moving");
        // Get the player's position
        Position target = game.getPlayer().getPosition();
        // Get the enemy's current position
        Position start = entity.getPosition();

        // If the path is empty, find a new path to the player
        if (paths.isEmpty()) {
            paths = pf.findPath(start, target, game.getMap());
        }

        // If there are positions in the path, move the enemy towards the next position
        if (currentPathIndex < paths.size()) {
            Position targetPos = paths.get(currentPathIndex);
            entity.movement.MoveTowards(entity.getPosition(), targetPos);
            // If the enemy has reached the target position, move to the next position in the path
            if (entity.getPosition().getfX() == targetPos.getfX() && entity.getPosition().getfY() == targetPos.getfY()) {
                currentPathIndex++;
            }
        }
    }


    private boolean arrived(GameObject entity) {
        return true;
    }
}
