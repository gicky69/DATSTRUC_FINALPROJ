package core.collision;

import core.Position;

import java.util.ArrayList;
import java.util.List;

public class Collision {
    private Position positionOffset;
    private List<CollisionLine> collisionLines;
    private boolean[] layerMask = {false,false,false,false,false,false,false,false};

    public void BoxCollision(int width, int height) {
        collisionLines = new ArrayList<>();

        // Create points
        Position topleft = new Position(-(width / 2f), -(height / 2f));
        Position topright = new Position((width / 2f), -(height / 2f));
        Position bottomright = new Position((width / 2f), (height / 2f));
        Position bottomleft = new Position(-(width / 2f), (height / 2f));

        // Create CollisionLines, this will be used to detect BoxCollisions
        collisionLines.add(new CollisionLine(topleft, topright)); //top left to top right
        collisionLines.add(new CollisionLine(topright, bottomright)); //top right to bottom right
        collisionLines.add(new CollisionLine(bottomright, bottomleft)); //bottom right to bottom left
        collisionLines.add(new CollisionLine(bottomleft, topleft)); //bottem left to top left
    }

    public void setLayerMask(int layer, boolean isTrue) {
        layerMask[layer] = isTrue;
    }

    public boolean getLayerMask(int layer) {
        return layerMask[layer];
    }

    public List<CollisionLine> getCollisionLines() {
        return collisionLines;
    }
}
