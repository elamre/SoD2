package com.deeep.sod2.particle;

import com.deeep.sod2.entities.CollideAble;
import com.deeep.sod2.entities.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/5/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class SequencerTest implements CollideAble {
    public static void main(String[] args) throws InterruptedException {
        SequencerTest sequencerTest = new SequencerTest();
        if(sequencerTest instanceof CollideAble)
            sequencerTest.Collide(null);

    }

    @Override
    public void Collide(Entity entity) {
        System.out.println("Collide");
    }
}
