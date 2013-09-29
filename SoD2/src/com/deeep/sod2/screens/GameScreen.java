package com.deeep.sod2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.Core;
import com.deeep.sod2.gameplay.Controller;
import com.deeep.sod2.gameplay.InputReactListener;
import com.deeep.sod2.utility.Assets;
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

    /** The camera to draw everything */
    private OrthographicCamera orthographicCamera;
    /** The spritebatch to draw everything to */
    private SpriteBatch spriteBatch;
    /** Logger instance to log all events to. Please don't use system.out.print */
    private Logger logger = Logger.getInstance();
    /** The core system. Use this to switch screens */
    private Core core;
    //----------------------TODO TESTING USE ONLY TODO-----------------------//
    private TextureRegion texture;
    private TextureRegion background;
    private Controller controller;
    private int x = 0, y = 0;
    //-----------------------------------------------------------------------//

    public GameScreen(Core core) {
        this.core = core;
        spriteBatch = new SpriteBatch(5);           //TODO tune this
        texture = Assets.getAssets().getRegion("snakes/snake_1_head");
        background = new TextureRegion(new Texture(Gdx.files.internal("data/background.png")), 944, 961);
        orthographicCamera = new OrthographicCamera(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT);
        controller = new Controller();
        controller.registerKey(Input.Keys.W, new InputReactListener() {
            @Override
            public void inputReact() {
                y++;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.S, new InputReactListener() {
            @Override
            public void inputReact() {
                y--;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.A, new InputReactListener() {
            @Override
            public void inputReact() {
                x--;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.D, new InputReactListener() {
            @Override
            public void inputReact() {
                x++;
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.UP, new InputReactListener() {
            @Override
            public void inputReact() {
                orthographicCamera.translate(0, 1);
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.DOWN, new InputReactListener() {
            @Override
            public void inputReact() {
                orthographicCamera.translate(0, -1);
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.LEFT, new InputReactListener() {
            @Override
            public void inputReact() {
                orthographicCamera.translate(-1, 0);
            }
        }, InputReactListener.Event.HOLD);
        controller.registerKey(Input.Keys.RIGHT, new InputReactListener() {
            @Override
            public void inputReact() {
                orthographicCamera.translate(1, 0);
            }
        }, InputReactListener.Event.HOLD);

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        controller.update();

        orthographicCamera.update();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        spriteBatch.begin();

        spriteBatch.disableBlending();
        //TODO draw background
        spriteBatch.draw(background, 0, 0);
        spriteBatch.enableBlending();

        spriteBatch.draw(texture, x, y);

        spriteBatch.end();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** @see com.badlogic.gdx.ApplicationListener#resize(int, int) */
    @Override
    public void resize(int width, int height) {
        float tempRatio = width / height;
        float tempWidth = width, tempHeight = height;
        float tempScale = 1f;
        if (tempRatio > Constants.VIRTUAL_ASPECT) {
            tempScale = (float) height / (float) Constants.VIRTUAL_HEIGHT;
            tempWidth = (width - Constants.VIRTUAL_WIDTH * tempScale) / 2;
        } else if (tempRatio < Constants.VIRTUAL_ASPECT) {
            tempScale = (float) width / (float) Constants.VIRTUAL_WIDTH;
            tempWidth = (height - Constants.VIRTUAL_HEIGHT * tempScale) / 2;
        } else {
            tempScale = (float) width / (float) Constants.VIRTUAL_WIDTH;
        }
        orthographicCamera.setToOrtho(false, tempWidth, tempHeight);
        logger.debug(this.getClass(), "WxH: " + width + " x " + height + " new: " + tempWidth + " x " + tempHeight + " Scale: " + tempScale);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Called when this screen becomes the current screen for a {@link com.badlogic.gdx.Game}. */
    @Override
    public void show() {
        logger.debug(this.getClass(), "Showing");
    }

    /** Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}. */
    @Override
    public void hide() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** @see com.badlogic.gdx.ApplicationListener#pause() */
    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** @see com.badlogic.gdx.ApplicationListener#resume() */
    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Called when this screen should release all resources. */
    @Override
    public void dispose() {
        logger.debug(this.getClass(), "Disposing");
    }
}
