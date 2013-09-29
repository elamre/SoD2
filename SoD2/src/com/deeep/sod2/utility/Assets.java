package com.deeep.sod2.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 10:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class Assets {
    /** instance for singleton */
    private static Assets assets;
    /** Just a check to be sure that the assets aren't loaded multiple times */
    private static boolean loaded = false;
    /** The atlas containing all the images */
    private TextureAtlas textureAtlas;
    /** Logger instance */
    private Logger logger = Logger.getInstance();

    /** Find a use for this, if there is any TODO */
    public Assets() {
    }

    /**
     * Simple singleton
     *
     * @return assets instance
     */
    public static Assets getAssets() {
        if (assets == null) {
            assets = new Assets();
        }
        return assets;
    }

    /** function to load everything. Nothing special. TODO add more resources here( sound music etc) */
    public void load() {
        if (!loaded) {
            textureAtlas = new TextureAtlas(Gdx.files.internal("images/TextureAtlas.txt"));
            logger.system(Assets.class, "All assets have been loaded");
            loaded = true;
        }
    }

    /** Dispose function. should ALWAYS be called. This will get rid of the texture atlas */
    public void dispose() {
        logger.system(Assets.class, "All assets have been disposed");
    }

    /**
     * Returns an texture region based on the name given. Get images using this function!
     *
     * @param name see TextureAtlas.txt
     * @return the texture region connected to the name
     */
    public TextureRegion getRegion(String name) {
        return textureAtlas.findRegion(name);
    }
}
