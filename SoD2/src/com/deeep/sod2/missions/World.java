package com.deeep.sod2.missions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.gameplay.Map;
import com.deeep.sod2.graphics.Assets;
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
        Iterator<Integer> keySetIterator = integerAreaHashMap.keySet().iterator();
        float spaceInBetween = 0;
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            spaceInBetween += integerAreaHashMap.get(key).size();
        }
        spaceInBetween = (9 - spaceInBetween) / integerAreaHashMap.size();
        System.out.println("extra space: " + spaceInBetween);

        keySetIterator = integerAreaHashMap.keySet().iterator();
        float x = 1;
        float y = 1;

        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            integerAreaHashMap.get(key).setPosition(x, y);
            x += integerAreaHashMap.get(key).size() + spaceInBetween;
            y += integerAreaHashMap.get(key).size() / 2;
        }
    }

    public void update(float deltaT) {
        Iterator<Integer> keySetIterator = integerAreaHashMap.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            integerAreaHashMap.get(key).update(deltaT);
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
