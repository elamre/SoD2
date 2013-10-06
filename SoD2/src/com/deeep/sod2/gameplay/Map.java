package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/1/13
 * Time: 9:11 AM
 */
public class Map {
    private ArrayList<Entity>[][] map = new ArrayList[10][10];
    private int width, height;
    private Grid grid;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new ArrayList[height][width];
        grid = new Grid(0.2f, width, height, Color.BLUE);
    }

    public Map(Map map) {

    }

    public Map() {

    }

    public void update(float deltaT) {
        for (int y = 0; y<height; y++){
            //for ()
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        grid.draw(spriteBatch);
    }

}
