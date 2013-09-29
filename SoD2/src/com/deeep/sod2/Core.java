package com.deeep.sod2;

import com.deeep.sod2.screens.AbstractGame;
import com.deeep.sod2.screens.GameScreen;
import com.deeep.sod2.utility.Assets;

public class Core extends AbstractGame {

    /** Called when the {@link com.badlogic.gdx.Application} is first created. */
    @Override
    public void create() {
        Assets.getAssets().load();
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.getAssets().dispose();
    }

    @Override
    public void render(float deltaTime) {
        //TODO draw background here
    }
}
