package core.collision;

import entity.GameObject;

public class RayCastHit {
    public GameObject gameObject;
    public float distance;

    public RayCastHit(GameObject gameObject, float dist) {
        this.gameObject = gameObject;
        this.distance = dist;
    }
}
