package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Position;
import entity.GameObject;
import game.Game;
import tile.pathfinder.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AIState {
    private List<Position> targets;

    public Wander() {
        super();
        targets = new ArrayList<>();
    }

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("Stand", ((game, entity) -> arrived(entity)));
    }

    @Override
    public void update(Game game, GameObject entity) {
        if (targets.isEmpty()) {
            targets.add(game.getRandomPosition());
        }

        NPCController controller = (NPCController) entity.getController();
        controller.moveToTarget(targets.get(0), entity.getPosition());

        if (arrived(entity)) {
            controller.stop();
        }
    }

    private boolean arrived(GameObject entity) {
        return entity.getPosition().isInRangeOf(targets.get(0));
    }
}
