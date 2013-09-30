package com.deeep.sod2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.deeep.sod2.Core;
import com.deeep.sod2.gameplay.Renderer;
import com.deeep.sod2.gameplay.World;
import com.deeep.sod2.utility.Constants;
import com.deeep.sod2.utility.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 10:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen {
    /** The spritebatch to draw everything to */
    private SpriteBatch spriteBatch;
    /** Logger instance to log all events to. Please don't use system.out.print */
    private Logger logger = Logger.getInstance();
    /** The core system. Use this to switch screens */
    private Core core;
    /** The world which should contain all the game play and entities */
    private World world;
    /** The renderer which draws all the entities in the world */
    private Renderer renderer;
    /** The viewport for the game. Should handle all the resizing */
    private Rectangle viewport;

    /**
     * Constructor
     *
     * @param core the game Core
     */
    public GameScreen(Core core) {
        this.core = core;
        spriteBatch = new SpriteBatch(5);           //TODO tune this
        world = new World();
        renderer = new Renderer(spriteBatch, world);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        update(delta);
        draw();
    }

    /**
     * All the updating should go in here
     *
     * @param deltaT The time in seconds since the last render.
     */
    public void update(float deltaT) {
        world.update(deltaT);
    }

    /** Drawing happens here */
    public void draw() {
        renderer.render();
    }

    /** @see com.badlogic.gdx.ApplicationListener#resize(int, int) */
    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale = 1f;

        Vector2 crop = new Vector2(0f, 0f);
        if (aspectRatio > Constants.VIRTUAL_ASPECT) {
            scale = (float) height / (float) Constants.VIRTUAL_HEIGHT;
            crop.x = (width - Constants.VIRTUAL_WIDTH * scale) / 2f;
        } else if (aspectRatio < Constants.VIRTUAL_ASPECT) {
            scale = (float) width / (float) Constants.VIRTUAL_WIDTH;
            crop.y = (height - Constants.VIRTUAL_HEIGHT * scale) / 2f;
        } else {
            scale = (float) width / (float) Constants.VIRTUAL_WIDTH;
        }

        float w = (float) Constants.VIRTUAL_WIDTH * scale;
        float h = (float) Constants.VIRTUAL_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }

    /** Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}. */
    @Override
    public void show() {
        logger.debug(this.getClass(), "Showing");
    }

    /** Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}. */
    @Override
    public void hide() {
    }

    /** @see com.badlogic.gdx.ApplicationListener#pause() */
    @Override
    public void pause() {
    }

    /** @see com.badlogic.gdx.ApplicationListener#resume() */
    @Override
    public void resume() {
    }

    /** Called when this screen should release all resources. */
    @Override
    public void dispose() {
        logger.debug(this.getClass(), "Disposing");
    }
}
