package com.deeep.sod2.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.Head;
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
    private static Map instance;
    private ArrayList<Entity>[][] map;
    private int width, height;
    private Grid grid;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new ArrayList[height][width];
        grid = new Grid(0.2f, width, height, Color.BLUE, Save.LEVEL_1);
    }

    public Map(Map map) {
        this.map = map.getMap();
    }

    public Map() {
        map = new ArrayList[10][10];
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                map[y][x] = new ArrayList<Entity>(1);
            }
        }
    }

    public static Map getInstance() {
        if (instance == null)
            instance = new Map();
        return instance;
    }

    public void update(float deltaT) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int i = 0; i < map[width][height].size(); i++) {

                }
            }
        }
    }

    public void moveEntity(Entity entity, int newY, int newX) {
        for (int i = 0; i < map[(int) entity.getX()][(int) entity.getY()].size(); i++) {
            if (entity.getId() == map[(int) entity.getX()][(int) entity.getY()].get(i).getId()) {
                map[(int) entity.getX()][(int) entity.getY()].remove(i);
                break;
            }
        }
        map[newX][newY].add(entity);
        if (entity instanceof Head)
            System.out.println(toString());
    }

    public void addEntity(Entity entity, int x, int y) {
        map[x][y].add(entity);
    }

    public void draw(SpriteBatch spriteBatch) {
        grid.draw(spriteBatch);
    }

    public ArrayList<Entity>[][] getMap() {
        return map;
    }

    public String toString() {
        String string = "\n[";
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                string += map[x][y].size() + ", ";

            }
            string += "\n";

        }
        string += "]";
        return string;
    }

}
