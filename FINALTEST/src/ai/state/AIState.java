package ai.state;

import ai.AITransition;
import entity.GameObject;
import game.Game;

public abstract class AIState {
    private AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }

    protected abstract AITransition initializeTransition();
    public abstract void update(Game game, GameObject entity);

    public boolean shouldTransition(Game game, GameObject entity) {
        return transition.shouldTransition(game, entity);
    }

    public String getNextState() {
        return transition.getNextState();
    }

}

