package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Tank {
    private TextureRegion texture;
    private Rectangle rectangle;
    private GridPoint2 coordinates;

    public Tank(TextureRegion texture, Rectangle rectangle, GridPoint2 coordinates) {
        this.texture = texture;
        this.rectangle = rectangle;
        this.coordinates = coordinates;
    }

    public void moveUp(float speed) {
        rectangle.y += speed;
        coordinates.y += 1;
    }

    public void moveDown(float speed) {
        rectangle.y -= speed;
        coordinates.y -= 1;
    }

    public void moveLeft(float speed) {
        rectangle.x -= speed;
        coordinates.x -= 1;
    }

    public void moveRight(float speed) {
        rectangle.x += speed;
        coordinates.x += 1;
    }

    public void render(Batch batch) {
        batch.draw(texture, rectangle.x, rectangle.y);
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
}