package com.deeep.sod2.missions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/12/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mission {
    private String entityMap;
    private String map;

    public Mission(String entityMap, String map) {
        this.entityMap = entityMap;
        this.map = map;
    }

    public void draw(SpriteBatch spriteBatch) {
    }

    public String toString() {
        return "Mission: " + entityMap + " map: " + map;
    }
}
