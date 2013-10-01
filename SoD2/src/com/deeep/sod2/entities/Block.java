package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Block extends Entity {
    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Block() {
    }

    public Block(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
