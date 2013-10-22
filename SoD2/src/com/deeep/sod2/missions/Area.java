package com.deeep.sod2.missions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.deeep.sod2.graphics.Assets;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.particle.FormulaTypes;
import com.deeep.sod2.particle.Sequence;
import com.deeep.sod2.particle.Sequencer;
import com.deeep.sod2.utility.Camera;
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
    private Sequencer upDown;
    private Shop shop;
    private ArrayList<Mission> missions;
    private TextureRegion textureRegion;
    private float x, y;
    private float initialY;
    private Rectangle hitbox;
    private Rectangle missionBar;
    private boolean selected = false;
    private String shopData;
    private String name;

    public Area(String name, String world, String shopData, int levelAmount) {
        textureRegion = Assets.getAssets().getRegion("planets/missions/" + name);
        textureRegion.flip(true, false);
        this.shopData = shopData;
        this.name = name;
        missions = new ArrayList<Mission>();
        Mission mission;
        float positionX = 0;
        shop = new Shop();
        upDown = new Sequencer(true);
        shop.setPosition(10 - 1 - 0.2f, 0.2f);
        for (int i = 1; i < levelAmount + 1; i++) {
            mission = new Mission("data/" + world + "/" + name + "/" + name + "_" + i + ".png", "data/" + world + "/" + name + "/" + name + "_" + i + "_ent" + ".png");
            positionX = 10 - (i + 1) - ((i + 1) * 0.2f);
            mission.setPosition(positionX, 0.2f);
            missions.add(mission);
        }
        hitbox = new Rectangle(x, y, size(), size());
        missionBar = new Rectangle(9.8f - (missions.size() + 1 + ((missions.size() + 1) * 0.2f)), 0, 0.2f + (missions.size() + 1 + ((missions.size() + 1) * 0.2f)), 1.4f);
    }

    public void update(float deltaT) {
        if (selected) {
            upDown.update(deltaT);
            y = upDown.getValue();
        } else {
            y = initialY;
        }
        if (Gdx.input.isTouched()) {
            if (!missionBar.contains(Camera.getInstance().getTouchUnitX(), Camera.getInstance().getTouchUnitY())) {
                if (hitbox.contains(Camera.getInstance().getTouchUnitX(), Camera.getInstance().getTouchUnitY())) {
                    selected = true;
                } else {
                    selected = false;
                }
            } else {
                if (selected) {
                    for (Mission mission : missions) {
                        mission.checkPressed();
                    }
                }
            }
        }
    }

    public float size() {
        return (float) textureRegion.getRegionWidth() / (Constants.BLOCK_SIZE * Constants.SCALE);
    }

    public void setPosition(float x, float y) {
        System.out.println("new pos: " + x + ", " + y + "with size: " + size());
        this.x = x;
        this.y = y;
        this.initialY = y;
        upDown.clearSequence();
        upDown.addSequence(new Sequence(new FormulaTypes.Linear(2, y + size() / 8)));
        upDown.addSequence(new Sequence(new FormulaTypes.Sleep(0.3f)));
        upDown.addSequence(new Sequence(new FormulaTypes.Linear(2, y - size() / 8)));
        upDown.addSequence(new Sequence(new FormulaTypes.Sleep(0.3f)));
        upDown.start(y);
        hitbox = new Rectangle(x, y, size(), size());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(textureRegion, x, y, size(), size());
        if (selected) {
            ShapeRenderer.setColor(Color.WHITE);
            ShapeRenderer.drawRectangle(spriteBatch, missionBar, true);
            for (Mission mission : missions) {
                mission.draw(spriteBatch);
            }
            shop.draw(spriteBatch);
        }
    }

    class Shop {
        private float x, y;
        private TextureRegion textureRegion;
        private TextureRegion background;

        Shop() {
            textureRegion = Assets.getAssets().getRegion("gui/shop");
            background = Assets.getAssets().getRegion("gui/missionbutton");
        }

        public void setPosition(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void draw(SpriteBatch spriteBatch) {
            spriteBatch.draw(background, x, y, 1, 1);
            spriteBatch.draw(textureRegion, x, y, 1, 1);
        }
    }

}
