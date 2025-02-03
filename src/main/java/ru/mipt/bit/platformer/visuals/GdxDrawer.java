package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.logics.LevelListener;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Level;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualObjectFactoryRegistry;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;


import java.util.HashMap;
import java.util.Map;

public class GdxDrawer implements Drawer, LevelListener {
    private Level level;
    private TiledMap map;

    private static TiledMapTileLayer groundLayer;
    private TileMovement tileMovement;

    private final MapRenderer renderer;
    private final SpriteBatch batch;
    private final Map<GameObject, VisualObject> visualObjects;

    public GdxDrawer(Level level, HealthBarSettings healthBarSettings ) {
        map = new TmxMapLoader().load("level.tmx");
        this.level = level;
        this.renderer = createSingleLayerMapRenderer(map, new SpriteBatch());
        this.batch = new SpriteBatch();
        this.visualObjects = new HashMap<>();
        groundLayer = getSingleLayer(map);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    // Реализация LevelListener
    @Override
    public void onNewObject(GameObject object) {
        VisualObject visual = VisualObjectFactoryRegistry.createVisualObject(object);
        visualObjects.put(object, visual);
    }

    @Override
    public void onDeleteObject(GameObject object) {
        VisualObject visual = visualObjects.remove(object);
        if (visual != null) {
            visual.dispose();
        }
    }

    // Реализация Drawer
    @Override
    public void drawVisuals(Level level) {
        renderer.setView(batch.getProjectionMatrix(), 0, 0, level.getWidth(), level.getHeight());
        renderer.render();

        batch.begin();
        visualObjects.values().forEach(visual -> visual.draw(batch));
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        visualObjects.values().forEach(VisualObject::dispose);
    }
}
