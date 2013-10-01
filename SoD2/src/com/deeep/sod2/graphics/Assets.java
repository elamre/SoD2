package com.deeep.sod2.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.utility.Logger;

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
    /** The texture region for the shape renderer */
    private Sprite blankSprite;

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
            Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.WHITE);
            pixmap.fillRectangle(0, 0, 64, 64);
            blankSprite = new Sprite(new Texture(pixmap));
            //pixmap.dispose();
            textureAtlas = new TextureAtlas(Gdx.files.internal("images/TextureAtlas.txt"));
            logger.system(Assets.class, "All assets have been loaded");
            loaded = true;
        }
    }

    public Sprite getBlankSprite() {
        return blankSprite;
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
