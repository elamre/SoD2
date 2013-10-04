package com.deeep.sod2.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    /** The atlases containing all the images */
    private TextureAtlas textureAtlas, planetTextureAtlas, STD_BTNAtlas;
    /** Logger instance */
    private Logger logger = Logger.getInstance();
    /** The texture region for the shape renderer */
    private Sprite blankSprite;
    /** Standard font*/
    private BitmapFont font;

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
            font = loadBitmapFont();

            textureAtlas = new TextureAtlas(Gdx.files.internal("images/TextureAtlas.txt"));
            planetTextureAtlas = new TextureAtlas(Gdx.files.internal("images/PlanetTextureAtlas.txt"));
            STD_BTNAtlas = new TextureAtlas(Gdx.files.internal("images/gui/STD_BTNAtlas.txt"));

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

    /**
     * Returns a planet texture
     *
     * @param planetId id of desired planet
     * @return the planet texture connected to the id
     */
    public TextureRegion getPlanetTexture(int planetId) {
        return planetTextureAtlas.findRegion("planets/planet"+planetId);
    }

    /**
     * Loads the bitmap font as BitmapFont object
     * @return null or the font
     */
    public BitmapFont loadBitmapFont(){
        Texture texture = null;
        try{texture = new Texture(Gdx.files.internal("font/font.png"));}
        catch (NullPointerException e){Logger.getInstance().error(this.getClass(), e.getStackTrace());}

        texture.setFilter(Texture.TextureFilter.MipMapNearestNearest, Texture.TextureFilter.MipMapNearestNearest);
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(texture), false);
        if(font != null) return font;
        Logger.getInstance().error(this.getClass(), "Couldn't find specified font!");
        return null;
    }

    /**
     * Returns the bitmap font as BitmapFont object
     * @return null or the font
     */
    public BitmapFont getBitmapFont(){
        return font;
    }

    /**
     * Returns a button texture
     * @param active if TRUE return the active button
     * @return the button texture
     */
    public TextureRegion getButton(boolean active){
        return active ? STD_BTNAtlas.findRegion("active") : STD_BTNAtlas.findRegion("inactive");
    }
}
