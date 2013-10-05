package com.deeep.sod2.particle;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/5/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class SequencerTest {
    public static void main(String[] args) throws InterruptedException {
        Sequencer sequencer = new Sequencer(false);
        sequencer.addSequence(new Sequence(new FormulaTypes.Linear(1, 5)));
        float time = 0f;
        while (time < 1f) {
            time += 0.01f;
            Thread.sleep(100);
        }
    }
}
