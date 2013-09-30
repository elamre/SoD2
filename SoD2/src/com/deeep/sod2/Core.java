package com.deeep.sod2;

import com.badlogic.gdx.Gdx;
import com.deeep.sod2.screens.AbstractGame;
import com.deeep.sod2.screens.GameScreen;
import com.deeep.sod2.graphics.Assets;

/** This class is the entry point to the game */
public class Core extends AbstractGame {

    /** Called when the {@link com.badlogic.gdx.Application} is first created. */
    @Override
    public void create() {
        Assets.getAssets().load();
        setScreen(new GameScreen(this));
    }

    /** This will get rid of all the assets to prevent a memory leak */
    @Override
    public void dispose() {
        super.dispose();
        Assets.getAssets().dispose();
    }

    /** This should in the future render a background */
    @Override
    public void render(float deltaTime) {
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
        //TODO draw background here
        //TODO non game related stuff
    }
}
