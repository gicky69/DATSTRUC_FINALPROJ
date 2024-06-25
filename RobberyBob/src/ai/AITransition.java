package ai;

import entity.GameObject;
import game.Game;

public class AITransition {
    private String nextState;
    private AICondition condition;

    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    public boolean shouldTransition(Game game, GameObject entity) {
        return condition.isMet(game, entity);
    }

    public String getNextState() {
        return nextState;
    }
}
