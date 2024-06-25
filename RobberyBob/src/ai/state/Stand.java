package ai.state;

import ai.AITransition;
import entity.GameObject;
import game.Game;

public class Stand extends AIState {

    private int updateAlive;

    @Override
    protected AITransition initializeTransition() {
        return new AITransition("Wander", ((game, entity) -> updateAlive >= game.getTime().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(Game game, GameObject entity) {
        updateAlive++;
    }
}
