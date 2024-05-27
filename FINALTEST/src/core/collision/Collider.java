package core.collision;

import entity.GameObject;

public class Collider {
    public GameObject gameObject;
    public float distance;

    public Collider(GameObject gameObject, float dist) {
        this.gameObject = gameObject;
        this.distance = dist;
    }
}
