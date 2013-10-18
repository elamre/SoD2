package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.gameplay.Map;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: andreaskruhlmann
 * Date: 10/1/13
 * Time: 10:29 AM
 */
public class EntityManager {

    /** Singleton instance */
    private static EntityManager instance;
    /** The HashMap, that contains entities and their respective ids */
    public HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();
    public ArrayList<Pickup> pickups = new ArrayList<Pickup>();
    private int id = 1;
    /** Should contain all the collect ables */
    private ArrayList<Entity> addToList = new ArrayList<Entity>();
    private ArrayList<Entity> collideAbles = new ArrayList<Entity>();
    private ArrayList<CollectAble> collectAbles = new ArrayList<CollectAble>();
    private ArrayList<Snake> snakes = new ArrayList<Snake>();
    private ArrayList<Entity> removeList = new ArrayList<Entity>();
    private Map map;

    public EntityManager() {
    }

    /** Returns the singleton instantiation */
    public static EntityManager get() {
        if (instance == null) instance = new EntityManager();
        return instance;
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
        addToList.add(e);

        return e;
    }

    private void addAllEntities() {
        for (Entity entity : addToList) {
            entity.setDebug(false);
            entities.put(entity.getId(), entity);
            if (entity instanceof CollectAble) {
                collectAbles.add((CollectAble) entity);
            }
            if (entity instanceof CollideAble) {
                collideAbles.add(entity);
            }
            if (entity instanceof Snake) {
                snakes.add((Snake) entity);
            }
            if (entity instanceof Pickup) {
                pickups.add((Pickup) entity);
            }
            entity.onCreate();
        }
        addToList.clear();
    }

    public int getNextSinglePlayerId() {
        id++;
        return id;
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
        System.out.println("removing key: " + key);
    }

    /**
     * Removes an Entity from the HashMap
     *
     * @param e the entity to be removed (must be contained in )
     */
    public void removeEntity(Entity e) {
        if (collectAbles.contains(e)) {
            collectAbles.remove(e);
        }
        if (collideAbles.contains(e)) {
            collideAbles.remove(e);
        }
        if (snakes.contains(e)) {
            snakes.remove(e);
        }
        if (pickups.contains(e)) {
            pickups.remove(e);
        }
        removeEntity(e.getId());
    }

    /**
     * @param key the index of desired entity
     * @return the entity with index "key"
     */
    public Entity getEntity(int key) {
        return entities.get(key);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Add a new Entity to the HashMap
     *
     * @param delta the delta value to update the entities with
     */
    public void update(float delta) {
        Map.getInstance().update(delta);
        for (Entity collideAble : collideAbles) {
            if (collideAble.isAlive()) {
                try {
                    for (Entity check : collideAbles) {
                        if (check != collideAble) {
                            if (collideAble.overlaps(check.getHitBox())) {
                                ((CollideAble) collideAble).Collide(check);
                            }
                        }
                    }
                } catch (ConcurrentModificationException e) {
                    System.out.println(e);
                    System.out.println(collideAble.getClass().toString());
                }
            }
        }
        Iterator<Integer> keySetIterator = entities.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            entities.get(key).update(delta);

            float w = map.getSave().width + 3;
            /** Alive and within bounds check */
            if (!entities.get(key).isAlive()
                    || entities.get(key).getX() < -3
                    || entities.get(key).getY() < -3
                    || entities.get(key).getX() > map.getSave().width + 8
                    || entities.get(key).getY() > map.getSave().height + 16)
                removeList.add(entities.get(key));
        }
        for (CollectAble collectAble : collectAbles) {
            for (Snake snake : snakes) {
                collectAble.checkDistance(snake);
            }
        }
        boolean moved;
        for (Snake snake : snakes) {
            moved = snake.moved();
            if (moved) {
                if (map.getTile((int) snake.getX(), (int) snake.getY()) != null) {
                    map.getTile((int) snake.getX(), (int) snake.getY()).onStep(snake);
                } else {
                    snake.die();
                }
            }
        }
        for (Entity entity : removeList) {
            removeEntity(entity);
        }
        addAllEntities();
        removeList.clear();
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
            addEntitySinglePlayer(e);
        }
    }
}
