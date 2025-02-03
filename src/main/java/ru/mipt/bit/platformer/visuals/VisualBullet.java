package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.springframework.beans.factory.annotation.Value;
import ru.mipt.bit.platformer.logics.models.Bullet;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class VisualBullet implements VisualObject{
    private final Texture texture;
    private final TextureRegion graphics;
    private final Rectangle rectangle;
    @Value("${texture.bullet}")
    private String texturePath;
    private final Bullet logicalBullet;

    public VisualBullet(Bullet logicalBullet) {
        texture = new Texture(texturePath);
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.logicalBullet = logicalBullet;
    }

    public VisualBullet(VisualBullet deepCopy, Bullet logicalBullet){
        texture = new Texture(deepCopy.texturePath);
        this.logicalBullet = logicalBullet;
        graphics = new TextureRegion(texture);
        rectangle = createBoundingRectangle(graphics);
        this.texturePath = deepCopy.texturePath;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public float getRotation() {
        return logicalBullet.getRotation();
    }

    @Override
    public void draw(Batch batch) {
        drawTextureRegionUnscaled(batch, graphics, rectangle, getRotation());
    }

    @Override
    public void dispose(){
        texture.dispose();
    }

    @Override
    public void processMotion(TileMovement tileMovement){
        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(getRectangle(), logicalBullet.getCoordinates().toGridPoint2(),
                logicalBullet.getDestCoordinates().toGridPoint2(), logicalBullet.getMotionProgress());
    }

    @Override
    public GameObject getLogicalEntity() {
        return logicalBullet;
    }
}

