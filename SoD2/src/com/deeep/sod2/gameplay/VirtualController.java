package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/4/13
 * Time: 9:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class VirtualController {
    public VirtualController() {

    }

    public void update(float deltaT) {

    }

    public int registerArea() {
        return 0;
    }

    public void draw(SpriteBatch spriteBatch) {

    }

    public class Areas {
        private int x, y;
        private int id = 0;
        private boolean moveAble = false;

        public void rePosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
