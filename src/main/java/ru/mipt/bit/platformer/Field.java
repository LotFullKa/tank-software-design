package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Field {
    private TiledMap map;
    private MapRenderer renderer;

    public Field(TiledMap map, MapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
    }

    public void render() {
        renderer.render();
    }

    public TiledMap getMap() {
        return map;
    }
}
