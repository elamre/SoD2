package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/9/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coin extends Entity {
    private TextureRegion[] frames;
    private Animation animation;
    private float animationTimer = 0f;

    public Coin(int id, float x, float y) {
        super(id, -1, x, y);
        setCenter(false);
        setX(x);
        setY(y);
    }

    public Coin() {
        super();
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        animationTimer += deltaT;
        setTextureRegion(animation.getKeyFrame(animationTimer, true));
        setWidth(0.25f);
        setHeight(0.25f);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        frames = new TextureRegion[20];
        TextureRegion[][] temp;
        temp = Assets.getAssets().getRegion("animations/coin_strip20").split(40, 40);
        for (int i = 0; i < 20; i++) {
            frames[i] = temp[0][i];
        }
        animation = new Animation(0.05f, frames);
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
