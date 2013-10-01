package com.deeep.sod2.particle;

import com.deeep.sod2.utility.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/1/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sequence {
    private float value = 0;
    private float deltaValue;
    private float lifeTime = 0;
    private float endingValue = 0;
    private float initialLifeTime = 0;

    public Sequence(float endingValue, float lifeTime) {
        this.endingValue = endingValue;
        this.initialLifeTime = lifeTime;
    }

    public void activate(float startValue) {
        lifeTime = initialLifeTime;
        this.value = startValue;
        if (endingValue != -1) {
            float delta = endingValue - startValue;
            this.deltaValue = delta / lifeTime;
        } else {
            deltaValue = 0;
        }

    }

    public float getValue() {
        return value;
    }

    public void update(float deltaT) {
        if (lifeTime > 0) {
            value += deltaValue * deltaT;
        }
        lifeTime -= deltaT;
    }

    public boolean isFinished() {
        return lifeTime <= 0;
    }
}
