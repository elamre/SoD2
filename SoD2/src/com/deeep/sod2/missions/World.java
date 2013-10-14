package com.deeep.sod2.missions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.gameplay.Map;
import com.deeep.sod2.utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/12/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class World {
    private HashMap<Integer, Area> integerAreaHashMap = new HashMap<>();
    private String name;

    public World(String name) {
        this.name = name;
    }

    public void addArea(Integer id, Area area) {
        integerAreaHashMap.put(id, area);
    }

    public void finishAdding() {
        int previousKey = -1;
        Iterator<Integer> keySetIterator = integerAreaHashMap.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            int x = 0;
            int y = 0;
            if (previousKey != -1) {
                integerAreaHashMap.get(key).setPosition(
                        integerAreaHashMap.get(previousKey).getX() - (2 * (float) Math.cos(45) * integerAreaHashMap.get(previousKey).size())+ integerAreaHashMap.get(previousKey).size()/2,// ,
                        integerAreaHashMap.get(previousKey).getY() + ((float) Math.cos(45) * integerAreaHashMap.get(previousKey).size() + integerAreaHashMap.get(previousKey).size() / 8)
                );
            } else {
                integerAreaHashMap.get(key).setPosition(9, 1);
            }
            previousKey = key;
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        Iterator<Integer> keySetIterator = integerAreaHashMap.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            integerAreaHashMap.get(key).draw(spriteBatch);
        }
    }
}
