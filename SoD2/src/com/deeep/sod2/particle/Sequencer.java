package com.deeep.sod2.particle;

import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/1/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sequencer {
    private ArrayList<Sequence> sequences = new ArrayList<Sequence>();
    private float value = 0;
    private int index = 0;
    private boolean repeat = false;
    private float initialValue;

    public Sequencer(boolean repeat) {
        this.repeat = repeat;
    }

    public void addSequence(Sequence sequence) {
        sequences.add(sequence);
        sequence.activate(sequences.size() - 1);
    }

    public void clearSequence() {
        sequences.clear();
    }

    public void update(float deltaT) {
        if (sequences.get(index).isFinished()) {
            if (repeat) {
                index = (index < sequences.size() - 1) ? index + 1 : 0;
                sequences.get(index).activate(value);
            } else {
                index = -1;
            }
        }
        if (index != -1) {
            value = sequences.get(index).getValue();
            sequences.get(index).update(deltaT);
        }
    }

    public float getValue() {

        return value;
    }

    public boolean isFinished() {
        if (index == -1) {
            return true;
        }
        return false;
    }
}
