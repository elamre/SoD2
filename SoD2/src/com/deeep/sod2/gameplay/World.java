package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.graphics.*;
import com.deeep.sod2.io.Save;
import com.deeep.sod2.particle.ParticleManager;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/29/13
 * Time: 7:46 PM
 */
public class World {
    /** the player */
    private Map map;
    private Player player;
    private ParticleManager particleManager;
    /** Current level */
    private Save loadedSave;

    /** Don't pay too much attention to this. This is just to test the camera and the view port */
    public World() {
        Save.loadSaves();

        loadedSave = Save.LEVEL_1;
        player = new Player("Elmar is bad at chess!", true);
        map = new Map(loadedSave);
        EntityManager.get().setMap(map);
        particleManager = new ParticleManager();
        player.setEntityManager();
        player.setSnakeSpawnPoint(loadedSave.spawnX, loadedSave.spawnY);
        EntityManager.get().addEntitiesSinglePlayer(loadedSave.getEntities());
    }

    public void loadSave(Save save) {
        this.loadedSave = save;
        map = new Map(loadedSave);
        EntityManager.get().clear();
        EntityManager.get().setMap(map);
        player.setEntityManager();
        player.setSnakeSpawnPoint(loadedSave.spawnX, loadedSave.spawnY);
        EntityManager.get().addEntitiesSinglePlayer(loadedSave.getEntities());
    }

    public void update(float deltaT) {
        player.update(deltaT);
        map.update(deltaT);
        EntityManager.get().update(deltaT);
        particleManager.update(deltaT);
    }

    public void draw(SpriteBatch spriteBatch) {
        particleManager.draw(spriteBatch);
        map.draw(spriteBatch);
        EntityManager.get().draw(spriteBatch);
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
