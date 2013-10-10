package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.gameplay.Map;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: andreaskruhlmann
 * Date: 10/1/13
 * Time: 10:29 AM
 */
public class EntityManager {

    /** The HashMap, that contains entities and their respective ids */
    public HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();

    public EntityManager() {
    }

    /** Clears the entire entity HashMap */
    public void clearEntities() {
        entities.clear();
    }

    /**
     * Add a new Entity to the HashMap for single player
     *
     * @param e the entity to be added
     */
    public Entity addEntitySinglePlayer(Entity e) {
        e.setDebug(false);
        entities.put(entities.size(), e);
        return e;
    }

    public int getNextSinglePlayerId() {
        return entities.size();
    }

    /**
     * Add a new Entity to the HashMap for multi player
     *
     * @param e the entity to be added
     */
    public void addEntityMultiPlayer(Entity e) {
        e.setDebug(false);
        entities.put(e.getId(), e);
    }

    /**
     * Remove an Entity from the HashMap
     *
     * @param key the index of the entity
     */
    public void removeEntity(int key) {
        entities.remove(key);
    }

    /**
     * Removes an Entity from the HashMap
     *
     * @param e the entity to be removed (must be contained in )
     */
    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    /**
     * @param key the index of desired entity
     * @return the entity with index "key"
     */
    public Entity getEntity(int key) {
        return entities.get(key);
    }

    /**
     * Add a new Entity to the HashMap
     *
     * @param delta the delta value to update the entities with
     */
    public void update(float delta) {
        Iterator<Integer> keySetIterator = entities.keySet().iterator();
        Map.getInstance().update(delta);
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            entities.get(key).update(delta);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        Iterator<Integer> keySetIterator = entities.keySet().iterator();
        int i = 0;
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            entities.get(key).draw(spriteBatch);
        }
    }

    public void addEntitiesSinglePlayer(Entity[] ent) {
        if (ent == null) return;
        for (Entity e : ent) {
            if (e == null) continue;
            e.setDebug(false);
            this.entities.put(this.entities.size(), e);
        }
    }
}
