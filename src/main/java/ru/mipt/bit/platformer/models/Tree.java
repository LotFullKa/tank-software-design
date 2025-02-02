package ru.mipt.bit.platformer.models;

import ru.mipt.bit.platformer.util.Vector2D;

public class Tree{
    private final Vector2D coordinates;
    private final float rotation;

    Tree(Vector2D coordinates){
        this.coordinates = new Vector2D(coordinates);
        rotation = 0f;
    }

    public float getRotation() {
        return rotation;
    }

    public Vector2D getCoordinates() {
        return coordinates;
    }

}