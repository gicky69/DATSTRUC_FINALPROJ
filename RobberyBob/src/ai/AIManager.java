package ai;

import ai.state.AIState;
import ai.state.Pursue;
import ai.state.Stand;
import ai.state.Wander;
import entity.GameObject;
import game.Game;

public class AIManager {

    private AIState currentAIState;

    public AIManager() {
        transitionTo("Stand");
    }

    public void update(Game game, GameObject entity) {
        currentAIState.update(game, entity);

        if (currentAIState.shouldTransition(game, entity)) {
            transitionTo(currentAIState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
        System.out.println("Transitioning to " + nextState);
        switch (nextState) {
            case "Pursue":
                currentAIState = new Pursue();
                break;
            case "Wander":
                currentAIState = new Wander();
                break;
            default:
                currentAIState = new Stand();
        }

    }

}
