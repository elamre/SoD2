package com.deeep.sod2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.deeep.sod2.Core;
import com.deeep.sod2.missions.Area;
import com.deeep.sod2.missions.MissionParser;
import com.deeep.sod2.missions.MissionRenderer;
import com.deeep.sod2.utility.Constants;
import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/11/13
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MissionScreen implements Screen {
    /** The spritebatch to draw everything to */
    private SpriteBatch spriteBatch;
    /** Logger instance to log all events to. Please don't use system.out.print */
    private Logger logger = Logger.getInstance();
    /** The renderer */
    private MissionRenderer missionRenderer;
    /** The viewport for the game. Should handle all the resizing */
    private Rectangle viewport;

    public MissionScreen() {
        spriteBatch = new SpriteBatch(5);
        missionRenderer = new MissionRenderer(spriteBatch);
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
        missionRenderer.render();
        Iterator<Integer> keySetIterator = MissionParser.getMissionParser().getWorlds().keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            MissionParser.getMissionParser().getWorlds().get(key).update(delta);
        }

    }

    /** @see com.badlogic.gdx.ApplicationListener#resize(int, int) */
    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        float scale;

        Vector2 crop = new Vector2(0f, 0f);
        if (aspectRatio > Constants.VIRTUAL_ASPECT) {
            scale = (float) height / Constants.VIRTUAL_HEIGHT;
            crop.x = (width - Constants.VIRTUAL_WIDTH * scale) / 2f;
        } else if (aspectRatio < Constants.VIRTUAL_ASPECT) {
            scale = (float) width / Constants.VIRTUAL_WIDTH;
            crop.y = (height - Constants.VIRTUAL_HEIGHT * scale) / 2f;
        } else {
            scale = (float) width / Constants.VIRTUAL_WIDTH;
        }

        float w = Constants.VIRTUAL_WIDTH * scale;
        float h = Constants.VIRTUAL_HEIGHT * scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }

    /** Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}. */
    @Override
    public void show() {
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
    }
}
