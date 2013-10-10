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
 * Date: 10/9/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coin extends Entity {
    float dy, dx;
    Sequencer dySequencer;
    Sequencer transparencySequence;
    Sequencer dxSequencer;
    private TextureRegion[] frames;
    private float transparencyTimer = 1f;
    private Animation animation;
    private float animationTimer = 0f;


    public Coin(int id, float x, float y) {
        super(id, -1, x, y);
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
        if (!dxSequencer.isFinished()) {
            dxSequencer.update(deltaT);
            x += dxSequencer.getValue();
        }
        if (!dySequencer.isFinished()) {
            dySequencer.update(deltaT);
            y += dySequencer.getValue();
        }
        transparencySequence.update(deltaT);

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
        transparencySequence.addSequence(new Sequence(new FormulaTypes.Sleep(2.5f + random.nextFloat() * 2.5f)));
        transparencySequence.addSequence(new Sequence(new FormulaTypes.Linear(0.5f + random.nextFloat() * 0.5f, 0)));
        transparencySequence.start(1);
        frames = new TextureRegion[20];
        TextureRegion[][] temp;
        temp = Assets.getAssets().getRegion("animations/coin_strip20").split(40, 40);
        for (int i = 0; i < 20; i++) {
            frames[i] = temp[0][i];
        }
        animation = new Animation(0.05f, frames);
        animation.setPlayMode(Animation.LOOP_PINGPONG);
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

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
