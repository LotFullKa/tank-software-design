package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

import Tank.Tank;


public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;

    private Batch batch;

    private Field field;
    private Tank playerTank;
    private Tree tree;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        TiledMap level = new TmxMapLoader().load("level.tmx");
        MapRenderer levelRenderer = createSingleLayerMapRenderer(level, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(level);
        TileMovement tileMovement = new TileMovement(groundLayer, Interpolation.smooth);

        field = new Field(level, levelRenderer)

        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        Texture blueTankTexture = new Texture("images/tank_blue.png");
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        TextureRegion playerGraphics = new TextureRegion(blueTankTexture);
        Rectangle playerRectangle = createBoundingRectangle(playerGraphics);

        // set player initial position
        // playerDestinationCoordinates = new GridPoint2(1, 1);
        GridPoint2 playerCoordinates = new GridPoint2(1, 1);
        // playerRotation = 0f;

        playerTank = new Tank(playerGraphics, playerRectangle, playerCoordinates)

        Texture greenTreeTexture = new Texture("images/greenTree.png");
        TextureRegion treeObstacleGraphics = new TextureRegion(greenTreeTexture);
        GridPoint2 treeObstacleCoordinates = new GridPoint2(1, 3);
        Rectangle treeObstacleRectangle = createBoundingRectangle(treeObstacleGraphics);
        moveRectangleAtTileCenter(groundLayer, treeObstacleRectangle, treeObstacleCoordinates);

        tree = new Tree(treeObstacleGraphics, treeObstacleRectangle)
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

         // Handle input for player movement
        if (Gdx.input.isKeyPressed(UP)) {
            playerTank.moveUp(MOVEMENT_SPEED);
        }
        if (Gdx.input.isKeyPressed(DOWN)) {
            playerTank.moveDown(MOVEMENT_SPEED);
        }
        if (Gdx.input.isKeyPressed(LEFT)) {
            playerTank.moveLeft(MOVEMENT_SPEED);
        }
        if (Gdx.input.isKeyPressed(RIGHT)) {
            playerTank.moveRight(MOVEMENT_SPEED);
        }

        // if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
        //     if (isEqual(playerMovementProgress, 1f)) {
        //         // check potential player destination for collision with obstacles
        //         if (!treeObstacleCoordinates.equals(incrementedY(playerCoordinates))) {
        //             playerDestinationCoordinates.y++;
        //             playerMovementProgress = 0f;
        //         }
        //         playerRotation = 90f;
        //     }
        // }
        // if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
        //     if (isEqual(playerMovementProgress, 1f)) {
        //         if (!treeObstacleCoordinates.equals(decrementedX(playerCoordinates))) {
        //             playerDestinationCoordinates.x--;
        //             playerMovementProgress = 0f;
        //         }
        //         playerRotation = -180f;
        //     }
        // }
        // if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
        //     if (isEqual(playerMovementProgress, 1f)) {
        //         if (!treeObstacleCoordinates.equals(decrementedY(playerCoordinates))) {
        //             playerDestinationCoordinates.y--;
        //             playerMovementProgress = 0f;
        //         }
        //         playerRotation = -90f;
        //     }
        // }
        // if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
        //     if (isEqual(playerMovementProgress, 1f)) {
        //         if (!treeObstacleCoordinates.equals(incrementedX(playerCoordinates))) {
        //             playerDestinationCoordinates.x++;
        //             playerMovementProgress = 0f;
        //         }
        //         playerRotation = 0f;
        //     }
        // }

        // calculate interpolated player screen coordinates
        tileMovement.moveRectangleBetweenTileCenters(playerRectangle, playerCoordinates, playerDestinationCoordinates, playerMovementProgress);

        playerMovementProgress = continueProgress(playerMovementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(playerMovementProgress, 1f)) {
            // record that the player has reached his/her destination
            playerCoordinates.set(playerDestinationCoordinates);
        }

        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render player
        // drawTextureRegionUnscaled(batch, playerGraphics, playerRectangle, playerRotation);
        playerTank.render(batch)

        // render tree obstacle
        // drawTextureRegionUnscaled(batch, treeObstacleGraphics, treeObstacleRectangle, 0f);
        Tree.render(batch)

        // submit all drawing requests
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
