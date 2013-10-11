package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.*;
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
public abstract class Pickup extends Entity implements CollideAble {
    private boolean pickupAble = true;
    private float rotationSpeed = 80;
    private float size = 1;
    private Sequencer sequencer;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    protected Pickup() {
    }

    protected Pickup(int id, int x, int y, TextureRegion textureRegion) {
        super(id, -1, x, y);
        sequencer = new Sequencer(true);
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(2, 0.9f)));
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(2, 0.5f)));
        setTextureRegion(textureRegion);

    }

    @Override
    public void implementUpdate_1(float deltaT) {
        angle += deltaT * rotationSpeed;
        sequencer.update(deltaT);
        size = sequencer.getValue();
        if (!isAlive())
            size = .7f;
        setWidth(size);
        setHeight(size);
    }

    /**
     * the action to be executed when the pickup is used. If nothing should happen, and the pick up should not removed,
     * return false;
     *
     * @param owner         use this for origin and such
     * @return true if the pickup has been used
     */
    public abstract boolean action(Snake owner);

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {

    }

    @Override
    public void Collide(Entity entity) {
        if (entity instanceof Snake) {
            die();
            ((Snake) entity).addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), entity.getId(), 0, 0)), this);
        }
    }


}
