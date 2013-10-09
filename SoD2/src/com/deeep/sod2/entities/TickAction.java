package com.deeep.sod2.entities;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/8/13
 * Time: 12:13 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TickAction {
    private float timer = 0;
    private float time = 1;
    private boolean repeat = false;

    public TickAction(float time, boolean repeat) {
        this.time = time;
        this.repeat = repeat;
    }

    public void update(float deltaT) {
        if (timer != -1) {
            timer += deltaT;
            if (timer >= time) {
                action();
                if (repeat) {
                    timer -= time;
                } else {
                    timer = -1;
                }
            }
        }
    }

    public abstract void action();

    public boolean isFinished() {
        return timer == -1;
    }
}
