package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.logic.models.GameObject;
import ru.mipt.bit.platformer.logic.models.Level;
import ru.mipt.bit.platformer.logic.models.Tank;
import ru.mipt.bit.platformer.logic.models.Tree;
import ru.mipt.bit.platformer.util.TileMovement;
import ru.mipt.bit.platformer.util.Vector2D;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualObjectFactoryRegistry;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualTankFactory;
import ru.mipt.bit.platformer.visuals.visualobj_factory.VisualTreeFactory;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GdxDrawer implements Drawer {
    private TiledMap gdxLevel;
    private VisualTank gdxTank;
    private VisualTree gdxTree;
    private ArrayList<VisualObject> visualObjects;

    private MapRenderer levelRenderer;
    private TileMovement tileMovement;
    private Batch batch;
    private static TiledMapTileLayer groundLayer;

    public GdxDrawer(Level level) {
        createVisuals(level);
        visualObjects = new ArrayList<>();
        batch = new SpriteBatch();
        levelRenderer = createSingleLayerMapRenderer(gdxLevel, batch);
        groundLayer = getSingleLayer(gdxLevel);
        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        VisualObjectFactoryRegistry registry = new VisualObjectFactoryRegistry();
        registry.registerFactory(Tank.class, new VisualTankFactory(gdxTank));
        registry.registerFactory(Tree.class, new VisualTreeFactory(gdxTree));

        for (GameObject gameObject : level.getObjects()) {
            VisualObject visualObject = new VisualObjectHealthDecorator(registry.createVisualObject(gameObject));
            visualObjects.add(visualObject);
            moveRectangleAtTileCenter(groundLayer, visualObject.getRectangle(), gameObject.getCoordinates().toGridPoint2());
        }

    }


    @Override
    public void drawVisuals(Level level) {
        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render game objects
        for (VisualObject visualObject : visualObjects) {
            visualObject.processMotion(tileMovement);
            visualObject.draw(batch);
        }

        // submit all drawing requests
        batch.end();


    }

    public void dispose(){
        // dispose visualObjects' textures
        for (VisualObject visualObject : visualObjects) {
            visualObject.dispose();
        }

        // dispose levelTile
        gdxLevel.dispose();

        batch.dispose();
    }

    private void createVisuals(Level level){
        gdxTank = new VisualTank("images/tank_blue.png", level.getPlayerTank());
        gdxTree = new VisualTree("images/greenTree.png", new Tree(new Vector2D()));
        gdxLevel = new TmxMapLoader().load("level.tmx");
    }



}
