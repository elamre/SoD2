package com.deeep.sod2.gameplay;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 8/18/13
 * Time: 1:40 AM
 * To change this template use File | Settings | File Templates.
 */
public interface InputReactListener {
    public void inputReact();

    static enum Event {
        PRESSED(0), RELEASED(1), HOLD(2);
        int number;

        Event(int number) {
            this.number = number;
        }
    }
}
