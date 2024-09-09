package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Tree {
    private TextureRegion texture;
    private Rectangle rectangle;

    public Tree(TextureRegion texture, Rectangle rectangle) {
        this.texture = texture;
        this.rectangle = rectangle;
    }

    public void render(Batch batch) {
        batch.draw(texture, rectangle.x, rectangle.y);
    }
}
