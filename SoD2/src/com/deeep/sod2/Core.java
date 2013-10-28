package com.deeep.sod2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.deeep.sod2.gameplay.Player;
import com.deeep.sod2.menu.MainMenu;
import com.deeep.sod2.missions.MissionParser;
import com.deeep.sod2.screens.AbstractGame;
import com.deeep.sod2.screens.GalaxyScreen;
import com.deeep.sod2.screens.GameScreen;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.screens.MissionScreen;

import java.io.*;

/** This class is the entry point to the game */
public class Core extends AbstractGame {
    private static Core core;

    public static Core getCore() {
        if (core == null) {
            core = new Core();
        }
        return core;
    }

    /** Called when the {@link com.badlogic.gdx.Application} is first created. */
    @Override
    public void create() {
        Assets.getAssets().load();
        MissionParser.getMissionParser();
        setScreen(new GameScreen());
        //setScreen(new MissionScreen());
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
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond() + " Coins: " + Player.coins);
        //TODO draw background here
        //TODO non game related stuff
    }
}
