package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Head;
import com.deeep.sod2.entities.Tail;
import com.deeep.sod2.io.Save;
import com.deeep.sod2.tiles.AbstractTile;
import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/1/13
 * Time: 9:11 AM
 */
public class Map {
    private static Map instance;
    private int width, height;
    private Save save;
    private AbstractTile[] tiles;

    public Map(Save save) {;
        //TODO only for debugging
        this.save = save;
        tiles = save.getTiles();
        instance = this;
        Shop shop = new Shop(0b10_0111);
    }

    public static Map getInstance() {
        return instance;
    }

    public void update(float deltaT) {

    }

    public void draw(SpriteBatch spriteBatch) {
        for (int y = 0; y < save.height; y++) {
            for (int x = 0; x < save.width; x++) {
                try {
                    tiles[x + y * save.width].draw(spriteBatch);
                } catch (NullPointerException e) {}
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Save getSave() {
        return save;
    }

    public AbstractTile[] getTiles() {
        return tiles;
    }
}
