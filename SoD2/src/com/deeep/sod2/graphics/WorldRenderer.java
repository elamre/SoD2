package com.deeep.sod2.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.gameplay.World;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/12/13
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorldRenderer extends Renderer {
    /** The world to draw all the entities of */
    private World world;

    public WorldRenderer(SpriteBatch spriteBatch, World world) {
        super(spriteBatch);
        this.world = world;
    }

    @Override
    public void renderBackground(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void renderObjects(SpriteBatch spriteBatch) {
        world.draw(spriteBatch);
    }

    @Override
    public void renderHUD(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
