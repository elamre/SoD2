package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/21/13
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AnimatedEntity extends Entity {
    private float speed = 1;
    private boolean pingPong = false;
    private String name;
    private Animation animation;
    private float animationTimer = 0f;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    protected AnimatedEntity() {
    }

    protected AnimatedEntity(int id, int owner, int x, int y, String name, float speed, boolean pingPong) {
        super(id, owner, x, y);
        this.speed = speed;
        this.pingPong = pingPong;
        this.name = name;
    }

    protected AnimatedEntity(int id, int owner, float x, float y, String name, float speed, boolean pingPong) {
        super(id, owner, x, y);
        this.speed = speed;
        this.pingPong = pingPong;
        this.name = name;
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        TextureRegion[][] temp;
        temp = Assets.getAssets().getRegion(name).split(40, 40);
        TextureRegion[] frames = new TextureRegion[temp[0].length - 1];
        System.arraycopy(temp[0], 0, frames, 0, temp[0].length - 1);
        animation = new Animation(speed, frames);
        animation.setPlayMode(Animation.LOOP);
        if (pingPong)
            animation.setPlayMode(Animation.LOOP_PINGPONG);
        onCreate_1();
    }

    public abstract void onCreate_1();

    @Override
    public void implementUpdate_1(float deltaT) {
        animationTimer += deltaT;
        setTextureRegion(animation.getKeyFrame(animationTimer, true));
        implementUpdate_2(deltaT);
    }

    public abstract void implementUpdate_2(float deltaT);

    public Animation getAnimation() {
        return animation;
    }
}
