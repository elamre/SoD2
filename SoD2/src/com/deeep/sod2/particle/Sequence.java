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
    /** The current value of the sequence, this will be adjusted */
    private float value = 0;
    /** The amount the value will change every ms */
    private float deltaValue;
    /** The total amount of lifetime for this sequence, it will be finish after the life time */
    private float lifeTime = 0;
    /** The to finish with ending value */
    private float endingValue = 0;
    /** To reset the lifetime with */
    private float initialLifeTime = 0;

    /**
     * Constructor for each sequence for now. TODO add more algorithms Exponential and Sinus
     * if ending value is -1, it wont change over the course TODO add a boolean for this
     *
     * @param endingValue the value the sequence should end with
     * @param lifeTime    the time in which it should reach that value
     */
    public Sequence(float endingValue, float lifeTime) {
        this.endingValue = endingValue;
        this.initialLifeTime = lifeTime;
    }

    /**
     * This function should be called once you want to start using the sequence. It will reset all the
     * values that have to be reset, and it will calculate the delta values.
     *
     * @param startValue the value it's starting with
     */
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

    /** @return the current value */
    public float getValue() {
        return value;
    }

    /**
     * Updates the life time, and calculates the new value
     *
     * @param deltaT time that has passed
     */
    public void update(float deltaT) {
        if (lifeTime > 0) {
            value += deltaValue * deltaT;
        }
        lifeTime -= deltaT;
    }

    /**
     * if the sequence is finished
     *
     * @return true if the lifetime is smaller or equal to zero, false otherwise
     */
    public boolean isFinished() {
        return lifeTime <= 0;
    }
}
