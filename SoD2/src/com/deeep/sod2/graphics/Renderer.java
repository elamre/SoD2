package com.deeep.sod2.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.gameplay.World;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.utility.Camera;
import com.deeep.sod2.utility.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Renderer {
    /** The frustum width and height. These are the blocks showed on screen */
    private final float FRUSTUM_WIDTH = Constants.VIRTUAL_WIDTH / (Constants.BLOCK_SIZE * Constants.SCALE);
    private final float FRUSTUM_HEIGHT = Constants.VIRTUAL_HEIGHT / (Constants.BLOCK_SIZE * Constants.SCALE);
    /** The camera controlling the viewing */
    private OrthographicCamera cam;
    /** The camera for the hud stuff */
    private OrthographicCamera hudCam;
    /** The sprite batch to draw everything on */
    private SpriteBatch spriteBatch;

    public Renderer(SpriteBatch spriteBatch){
        hudCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2, FRUSTUM_HEIGHT / 2, 0);
        Camera.getInstance().setFrustum(cam.frustum);
        this.spriteBatch = spriteBatch;
        //TODO remove testing purposes only */
    }

    public void render() {
        cam.update();
        hudCam.update();
        Camera.getInstance().update(Gdx.graphics.getDeltaTime());
        cam.position.set( Camera.getInstance().getX(), Camera.getInstance().getY(), 0);
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        renderBackground(spriteBatch);
        renderObjects(spriteBatch);
        spriteBatch.end();
        spriteBatch.setProjectionMatrix(hudCam.combined);
        spriteBatch.begin();
        renderHUD(spriteBatch);
        spriteBatch.end();
    }

    private void renderBackground() {
        spriteBatch.disableBlending();
        spriteBatch.begin();
        //spriteBatch.draw(background, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        spriteBatch.enableBlending();
        spriteBatch.end();
    }

    public abstract void renderBackground(SpriteBatch spriteBatch);
    public abstract void renderObjects(SpriteBatch spriteBatch);
    public abstract void renderHUD(SpriteBatch spriteBatch);
}
