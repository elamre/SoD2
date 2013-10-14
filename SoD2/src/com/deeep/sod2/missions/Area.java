package com.deeep.sod2.missions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.utility.Constants;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/12/13
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Area {
    private ArrayList<Mission> missions;
    private TextureRegion textureRegion;
    private float x, y;
    private String shopData;
    private String name;

    public Area(String name, String world, String shopData, int levelAmount) {
        textureRegion = Assets.getAssets().getRegion("planets/missions/" + name);
        //textureRegion.flip(true, true);
        this.shopData = shopData;
        this.name = name;
        missions = new ArrayList<Mission>();
        for (int i = 1; i < levelAmount + 1; i++) {
            // missions.add(new Mission(world + "/" + name + "/" + name + "_" + i, world + "/" + name + "/" + name + "_" + i + "_ent"));
        }
        for (Mission mission : missions) {
            // System.out.println(mission);
        }
    }

    public void update(float deltaT) {
        for (Mission mission : missions) {

        }
    }

    public float size() {
        System.out.println("Region: " + textureRegion.getRegionWidth() + " : " + textureRegion.getRegionWidth() / (Constants.BLOCK_SIZE * Constants.SCALE));
        return textureRegion.getRegionWidth() / (Constants.BLOCK_SIZE * Constants.SCALE);
    }

    public void setPosition(float x, float y) {
        System.out.println("new pos: " + x + ", " + y);
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, x, y, size(), size());
        for (Mission mission : missions) {
            //mission.draw(spriteBatch);
        }
    }
}
