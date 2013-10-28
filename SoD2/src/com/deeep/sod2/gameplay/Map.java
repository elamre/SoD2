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

    public Map(Save save) {
        //TODO only for debugging
        this.save = save;
        tiles = save.getTiles();
        instance = this;
    }

    public static Map getInstance() {
        return instance;
    }

    public void update(float deltaT) {

    }

    public AbstractTile getTile(int x, int y) {
        if (x < 0 || y < 0 || x > save.width || y > save.height)
            return null;
        return tiles[x + y * save.width];
    }

    public void draw(SpriteBatch spriteBatch) {
        for (int y = 0; y < save.height; y++) {
            for (int x = 0; x < save.width; x++) {
                if (tiles[x + y * save.width] != null)
                    tiles[x + y * save.width].draw(spriteBatch);

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
