package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.particle.FormulaTypes;
import com.deeep.sod2.particle.Sequence;
import com.deeep.sod2.particle.Sequencer;
import com.deeep.sod2.utility.Camera;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/10/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class CollectAble extends Entity {
    private Sequencer transparencySequence;
    private Sequencer dySequencer;
    private Sequencer dxSequencer;
    private float existenceTime;
    private float dy, dx;
    private float speed;
    private float range;
    private Animation animation;
    private float animationTimer = 0f;
    private Entity moveTowards = null;
    private TextureRegion[] frames;
    private String name;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    protected CollectAble() {
    }

    protected CollectAble(int id, String name, float existenceTime, float speed, float range, float x, float y) {
        super(id, -1, x, y);
        this.existenceTime = existenceTime;
        this.speed = speed;
        this.range = range;
        this.name = name;
    }

    public void checkDistance(Entity entity) {
        if (entity.getDistance(x, y) <= range) {
            moveTowards = entity;
        }
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        animationTimer += deltaT;
        setTextureRegion(animation.getKeyFrame(animationTimer, true));
        if (moveTowards != null) {
            if (moveTowards.getDistance(x, y) < range) {
                float delX = moveTowards.getOriginX() - getOriginX();
                float delY = moveTowards.getOriginY() - getOriginY();
                float tempAngle = (float) Math.atan2(delX, delY);
                dx = (float) Math.sin(tempAngle);
                dy = (float) Math.cos(tempAngle);
            } else {
                moveTowards = null;
                dx = 0;
                dy = 0;
            }
        }
        if (!dxSequencer.isFinished()) {
            dxSequencer.update(deltaT);
            x += dxSequencer.getValue();
        }
        if (!dySequencer.isFinished()) {
            dySequencer.update(deltaT);
            y += dySequencer.getValue();
        }
        x += dx * deltaT * speed;
        y += dy * deltaT * speed;
        transparencySequence.update(deltaT);
        if(transparencySequence.isFinished())
            die();

        setWidth(0.25f);
        setHeight(0.25f);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        Random random = new Random();
        dy = random.nextFloat() * 2 - 1;
        dx = random.nextFloat() * 2 - 1;
        dy *= .1f;
        dx *= .1f;
        dxSequencer = new Sequencer(false);
        dxSequencer.addSequence(new Sequence(new FormulaTypes.Linear(.2f, dx)));
        dySequencer = new Sequencer(false);
        dySequencer.addSequence(new Sequence(new FormulaTypes.Linear(.2f, dy)));
        transparencySequence = new Sequencer(false);
        transparencySequence.addSequence(new Sequence(new FormulaTypes.Sleep(existenceTime)));
        transparencySequence.addSequence(new Sequence(new FormulaTypes.Linear(0.5f + random.nextFloat() * 0.5f, 0)));
        transparencySequence.start(1);
        frames = new TextureRegion[20];
        TextureRegion[][] temp;
        temp = Assets.getAssets().getRegion(name).split(40, 40);
        for (int i = 0; i < 20; i++) {
            frames[i] = temp[0][i];
        }
        animation = new Animation(0.05f, frames);
        animation.setPlayMode(Animation.LOOP_PINGPONG);
        dx = 0;
        dy = 0;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (Camera.getInstance().inVision(x, y, width, height)) {
            if (debug) {
                drawDebug(spriteBatch);
            }
            if (textureRegion != null) {
                Color tempColor = spriteBatch.getColor();
                tempColor.set(tempColor.r, tempColor.g, tempColor.b, transparencySequence.getValue());
                spriteBatch.setColor(tempColor);
                spriteBatch.draw(textureRegion, x, y, width / 2, height / 2, width, height, 1, 1, angle);
                tempColor.set(tempColor.r, tempColor.g, tempColor.b, 1);
                spriteBatch.setColor(tempColor);
            }
            implementDraw_1(spriteBatch);
        }
    }

    public abstract void onCreate_1();
}
