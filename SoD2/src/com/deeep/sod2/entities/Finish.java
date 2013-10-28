package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.utility.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/20/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Finish extends Entity implements CollideAble {
    private Animation animation;
    private float animationTimer = 0f;
    private TextureRegion[] frames;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Finish() {
    }

    public Finish(int id, int x, int y) {
        super(id, -1, x, y);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        frames = new TextureRegion[4];
        TextureRegion[][] temp;
        temp = Assets.getAssets().getRegion("animations/finish_strip10").split(40, 40);
        System.arraycopy(temp[0], 0, frames, 0, temp[0].length - 1);
        animation = new Animation(0.3f, frames);
        animation.setPlayMode(Animation.LOOP_PINGPONG);
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        animationTimer += deltaT;
        setTextureRegion(animation.getKeyFrame(animationTimer, true));
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Collide(Entity entity) {
        if (entity instanceof Snake) {
            Logger.getInstance().debug(this.getClass(), "Finished, yay!?");
        }
    }
}
