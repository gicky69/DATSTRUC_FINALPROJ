package ai;

import entity.GameObject;
import game.Game;

public interface AICondition {
    boolean isMet(Game game, GameObject entity);
}
