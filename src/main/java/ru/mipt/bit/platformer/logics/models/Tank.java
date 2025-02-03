package ru.mipt.bit.platformer.logics.models;

import ru.mipt.bit.platformer.util.Vector2D;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static com.badlogic.gdx.math.MathUtils.random;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank implements GameObject, Livable {
    private final float motionStarted = 0f;
    private final float motionFinished = 1f;

    private Direction direction;
    private Vector2D coordinates;
    private Vector2D destCoordinates;

    private float motionProgress;

    // TODO: move to constructor parameters
    private static final float MOVEMENT_SPEED = 0.4f;

    private float curHealth;
    private final float fullHealth;

    public Tank(Vector2D location, Direction direction) {
        coordinates = new Vector2D(location);
        destCoordinates = new Vector2D(coordinates);
        this.direction = direction;
        motionProgress = motionFinished;

        // TODO: move to constructor parameters
        fullHealth = random(50, 100);
        //currentHealth = fullHealth;
        curHealth = random(1, fullHealth);
    }

    public void move(Direction direction, Level level){
        if (isEqual(getMotionProgress(), motionFinished)) {
            Vector2D predict = predictCoordinates(direction);
            if (level.freeCoordinates(predict)) {
                startMotion(direction);
            }
            makeTurn(direction);
        }

    }

    public void startMotion(Direction direction) {
        destCoordinates = destCoordinates.add(direction.getVector());
        motionProgress = motionStarted;
    }

    public Vector2D predictCoordinates(Direction direction) {
        Vector2D predict = new Vector2D(coordinates);
        return predict.add(direction.getVector());
    }

    public void makeTurn(Direction direction) {
        this.direction = direction;
    }

    private void updateMotionProgress(float deltaTime){
        motionProgress = continueProgress(motionProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(motionProgress, motionFinished)) {
            coordinates = destCoordinates;
            stopMotion();
        }
    }

    public void stopMotion() {
        motionProgress = motionFinished;
    }

    @Override
    public Vector2D getCoordinates() {
        return coordinates;
    }

    @Override
    public Vector2D getDestCoordinates() {
        return destCoordinates;
    }

    public float getMotionProgress() {
        return motionProgress;
    }

    @Override
    public void updateProgress(float deltaTime) {
        updateMotionProgress(deltaTime);
    }

    @Override
    public float getRotation() {
        return direction.getAngle();
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public float getRelativeHealth() {
        return curHealth / fullHealth;
    }
}
