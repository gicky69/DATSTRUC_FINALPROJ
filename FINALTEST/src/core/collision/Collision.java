package core.collision;

import core.Lines;
import core.Position;

import java.util.ArrayList;
import java.util.List;

public class Collision {
    private Position positionOffset;
    private List<Lines> collisionLines;
    private boolean[] layerMask = {false,false,false,false,false,false,false,false};

    public void BoxCollision(int width, int height) {
        collisionLines = new ArrayList<>();

        // Create points
        Position topleft = new Position(-(width / 2f), -(height / 2f));
        Position topright = new Position((width / 2f), -(height / 2f));
        Position bottomright = new Position((width / 2f), (height / 2f));
        Position bottomleft = new Position(-(width / 2f), (height / 2f));

        // Create CollisionLines, this will be used to detect BoxCollisions
        collisionLines.add(new Lines(topleft, topright)); //top left to top right
        collisionLines.add(new Lines(topright, bottomright)); //top right to bottom right
        collisionLines.add(new Lines(bottomright, bottomleft)); //bottom right to bottom left
        collisionLines.add(new Lines(bottomleft, topleft)); //bottom left to top left
    }

    public void BoxCollision(int width, int height, Position offset) {
        collisionLines = new ArrayList<>();

        // Create points
        Position topleft = new Position(-(width / 2f)+offset.getfX(), -(height / 2f)+offset.getfY());
        Position topright = new Position((width / 2f)+offset.getfX(), -(height / 2f)+offset.getfY());
        Position bottomright = new Position((width / 2f)+offset.getfX(), (height / 2f)+offset.getfY());
        Position bottomleft = new Position(-(width / 2f)+offset.getfX(), (height / 2f)+offset.getfY());

        // Create CollisionLines, this will be used to detect BoxCollisions
        collisionLines.add(new Lines(topleft, topright)); //top left to top right
        collisionLines.add(new Lines(topright, bottomright)); //top right to bottom right
        collisionLines.add(new Lines(bottomright, bottomleft)); //bottom right to bottom left
        collisionLines.add(new Lines(bottomleft, topleft)); //bottem left to top left
    }

    public void setLayerMask(int layer, boolean isTrue) {
        layerMask[layer] = isTrue;
    }

    public boolean getLayerMask(int layer) {
        return layerMask[layer];
    }

    public List<Lines> getCollisionLines() {
        return collisionLines;
    }
}
