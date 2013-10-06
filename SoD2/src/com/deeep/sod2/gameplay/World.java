package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Block;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.entities.pickups.BulletPickup;
import com.deeep.sod2.entities.pickups.CompassPickup;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.SpeedPickup;
import com.deeep.sod2.graphics.*;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:46 PM
 */
public class World {
    /** TODO Should be modified by the grid  in constructor */
    /** the player */
    private Map map;
    private EntityManager entityManager;
    private Grid grid;
    /** TODO remove */
    private Player player;
    private ParticleManager particleManager;

    /** Don't pay too much attention to this. This is just to test the camera and the view port */
    public World() {
        map = new Map();
        entityManager = new EntityManager();
        player = new Player("Elmar", true);

        grid = new Grid(1, 20, 20, Color.BLUE);
        particleManager = new ParticleManager();
        player.setEntityManager(entityManager);
        entityManager.addEntitySinglePlayer(new HearthPickup(entityManager.getNextSinglePlayerId(), 3, 3));
        entityManager.addEntitySinglePlayer(new BulletPickup(entityManager.getNextSinglePlayerId(), 4, 4));
        entityManager.addEntitySinglePlayer(new CompassPickup(entityManager.getNextSinglePlayerId(), 5, 5));
        entityManager.addEntitySinglePlayer(new SpeedPickup(entityManager.getNextSinglePlayerId(), 6, 6));
    }

    public void update(float deltaT) {
        player.update(deltaT);
        map.update();
        entityManager.update(deltaT);
        particleManager.update(deltaT);
    }

    public void draw(SpriteBatch spriteBatch) {
        particleManager.draw(spriteBatch);
        grid.draw(spriteBatch);
        entityManager.draw(spriteBatch);
    }

    public void drawString(SpriteBatch spriteBatch, String s, int x, int y) {
        spriteBatch.flush();
        Gdx.gl10.glEnable(GL10.GL_ALPHA_TEST);
        //Gdx.gl10.glAlphaFunc(GL10.GL_GREATER, 0.5f);
        Assets.getAssets().getBitmapFont().draw(spriteBatch, s, x, y);
        spriteBatch.flush();
        Gdx.gl10.glDisable(GL10.GL_ALPHA_TEST);
    }
}
