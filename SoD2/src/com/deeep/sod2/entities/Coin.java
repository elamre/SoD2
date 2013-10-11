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
public class Coin extends CollectAble implements CollideAble {


    public Coin(int id, float x, float y) {
        super(id, "animations/coin_strip20", 5f, 2f, 1.5f, x, y);
    }

    public Coin() {
        super();
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCreate_1() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Collide(EntityManager entityManager, Entity entity) {
        if (entity instanceof Snake) {
            die();
            //TODO increment score
        }
    }
}
