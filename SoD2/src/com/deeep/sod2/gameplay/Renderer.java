package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
public class Renderer {
    /** The frustum width and height. These are the blocks showed on screen */
    private final float FRUSTUM_WIDTH = Constants.VIRTUAL_WIDTH / (Constants.BLOCK_SIZE * Constants.SCALE);
    private final float FRUSTUM_HEIGHT = Constants.VIRTUAL_HEIGHT / (Constants.BLOCK_SIZE * Constants.SCALE);
    /** The camera controlling the viewing */
    private OrthographicCamera cam;
    /** The camera for the hud stuff */
    private OrthographicCamera hudCam;
    /** The sprite batch to draw everything on */
    private SpriteBatch spriteBatch;
    /** The world to draw all the entities of */
    private World world;
    //--------------------Debugging stuff TODO remove -------------//
    //-------------------------------------------------------------//

    public Renderer(SpriteBatch spriteBatch, World world) {
        this.world = world;
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
        renderBackground();
        renderObjects();
        spriteBatch.setProjectionMatrix(hudCam.combined);
        renderHud();
    }

    private void renderBackground() {
        spriteBatch.disableBlending();
        spriteBatch.begin();
        //spriteBatch.draw(background, cam.position.x - FRUSTUM_WIDTH / 2, cam.position.y - FRUSTUM_HEIGHT / 2, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        spriteBatch.enableBlending();
        spriteBatch.end();
    }

    private void renderObjects() {
        spriteBatch.begin();                       // ShapeRenderer.drawRectangle(spriteBatch,0,0,15,15,true);
        world.draw(spriteBatch);
        spriteBatch.end();

    }

    private void renderHud() {
        spriteBatch.setProjectionMatrix(hudCam.combined);
        spriteBatch.begin();
        spriteBatch.setColor(Color.WHITE);
        ShapeRenderer.setColor(Color.WHITE);
        ShapeRenderer.drawRectangle(spriteBatch, 2, 2, 5, 10, true);
        // Assets.getAssets().getBitmapFont().setColor(Color.WHITE);
        //world.drawString(spriteBatch, "hello, World!", 20, 20);
        spriteBatch.end();
    }


}
