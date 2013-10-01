package com.deeep.sod2.entities;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: andreaskruhlmann
 * Date: 10/1/13
 * Time: 10:29 AM
 */
public class EntityManager {

    //The HashMap, that contains entities and their respective ids
    public HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();

    public EntityManager() {
    }

    /**
     * Clears the entire entity HashMap
     */
    public void clearEntities(){
        entities.clear();
    }

    /**
     * Add a new Entity to the HashMap
     * @param e the entity to be added
     */
    public void addEntity(Entity e){
        e.setDebug(false);
        entities.put(entities.size(), e);
    }

    /**
     * Remove an Entity from the HashMap
     * @param key the index of the entity
     */
    public void removeEntity(int key){
        entities.remove(key);
    }

    /**
     * Removes an Entity from the HashMap
     * @param e the entity to be removed (must be contained in )
     */
    public void removeEntity(Entity e){
        entities.remove(e);
    }

    public Entity getEntity(int key){
        return entities.get(key);
    }

    /**
     * Add a new Entity to the HashMap
     * @param delta the delta value to update the entities with
     */
    public void update(float delta){
        Iterator<Integer> keySetIterator = entities.keySet().iterator();

        while(keySetIterator.hasNext()){
            Integer key = keySetIterator.next();
            entities.get(key).update(delta);
        }
    }

}
