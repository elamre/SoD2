package com.deeep.sod2.entities.pickups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.ShapeRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 4:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TempCheckpoint extends Entity {
    Color color;
    private Snake.Direction direction;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public TempCheckpoint() {
    }

    public TempCheckpoint(int id, int owner, int x, int y, Snake.Direction direction) {
        super(id, owner, x, y);
        this.direction = direction;
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        color = new Color(0.05f, 0.7f, 0.1f, 0.3f);
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(color);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, 1, 1, true);
    }

    public Snake.Direction getDirection() {
        return direction;
    }

}
