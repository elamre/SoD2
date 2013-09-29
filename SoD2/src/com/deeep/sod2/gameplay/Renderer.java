package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.deeep.sod2.utility.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Renderer {
    /** The frustrum width and height */
    private final int FRUSTUM_WIDTH = 10;
    private final int FRUSTUM_HEIGHT = 10;
    ShapeRenderer shapeRenderer;
    private TextureRegion texture;
    private OrthographicCamera cam;
    private SpriteBatch spriteBatch;
    private TextureRegion background;
    private World world;

    public Renderer(SpriteBatch spriteBatch, World world) {
        this.world = world;
        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        this.spriteBatch = spriteBatch;
        shapeRenderer = new ShapeRenderer();

        texture = Assets.getAssets().getRegion("snakes/snake_1_head");
        background = new TextureRegion(new Texture(Gdx.files.internal("data/background.png")), 944, 961);
    }

    public void render() {
        cam.update();
        cam.position.set(FRUSTUM_WIDTH / 2 + world.camera.x, FRUSTUM_HEIGHT / 2 + world.camera.y, 0);
        spriteBatch.setProjectionMatrix(cam.combined);
        shapeRenderer.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    private void renderBackground() {
        spriteBatch.disableBlending();
        spriteBatch.begin();
        //spriteBatch.draw(background, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        spriteBatch.enableBlending();
        spriteBatch.end();
    }

    private void renderObjects() {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 0, 15, 15);
        shapeRenderer.end();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                shapeRenderer.line(x, 0, x, y);
                shapeRenderer.line(0, y, x, y);
            }
        }
        shapeRenderer.end();
        spriteBatch.begin();
        spriteBatch.draw(texture, world.block.x, world.block.y, 1, 1);
        spriteBatch.end();
    }
}
