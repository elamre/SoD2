package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.particle.FormulaTypes;
import com.deeep.sod2.particle.Sequence;
import com.deeep.sod2.particle.Sequencer;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/5/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Pickup extends Entity {
    private float rotationSpeed = 80;
    private float size = 1;
    private Sequencer sequencer;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    protected Pickup() {
    }

    protected Pickup(int id, int x, int y, TextureRegion textureRegion) {
        super(id, -1, x, y);
        sequencer = new Sequencer(true);
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(2, 1.1f)));
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(2, 0.8f)));
        setTextureRegion(textureRegion);

    }

    @Override
    public void implementUpdate_1(float deltaT) {
        angle += deltaT * rotationSpeed;
        sequencer.update(deltaT);
        size = sequencer.getValue();
        setWidth(size);
        setHeight(size);
    }

    public abstract void action();

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
