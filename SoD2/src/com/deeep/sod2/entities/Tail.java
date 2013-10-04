package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tail extends Entity {
    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Tail() {
    }

    public void setSkin(int skin){
        setTextureRegion(Assets.getAssets().getRegion("snakes/snake_1_tail"));
    }

    public Tail(int id, int owner, int x, int y) {
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
