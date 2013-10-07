package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Tail;
import com.deeep.sod2.io.Save;

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
        grid = new Grid(0.2f, width, height, Color.BLUE, Save.LEVEL_1);
    }

    public Map(Map map) {

    }

    public Map() {

    }

    public void update(float deltaT) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int i = 0; i < map[width][height].size(); i++) {
                    //map[y][x].collision(map[width][height]);
                }
            }
        }
    }

    public void collision(ArrayList<Entity> map) {
        if (map != null) {
            for (int i = 0; i < map.size(); i++) {
                //if (map.get(i) != this) {
                if (map.get(i) instanceof Tail) {
                    //Do something
                }
                //}
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        grid.draw(spriteBatch);
    }

}
