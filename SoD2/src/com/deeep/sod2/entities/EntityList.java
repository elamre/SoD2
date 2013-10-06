package com.deeep.sod2.entities;

import com.deeep.sod2.entities.pickups.BulletPickup;
import com.deeep.sod2.entities.pickups.CompassPickup;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.SpeedPickup;
import com.deeep.sod2.utility.Logger;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/30/13
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class EntityList {/* The packet id to assign to packets. Will increment after each use */
    private static int entityType = 1;
    /* Simple hashMap to obtain an ID from a packet object */
    private static HashMap<Class<? extends Entity>, Integer> entityToType = new HashMap<Class<? extends Entity>, Integer>();
    /* Simple hashMap to obtain an empty object from ID */
    private static HashMap<Integer, Class<? extends Entity>> typeToEntity = new HashMap<Integer, Class<? extends Entity>>();
    /** Logger instance just to make everything easier */
    private static Logger logger = Logger.getInstance();
    /** To make sure all the entities are registered */
    private static boolean registered = false;

    /**
     * This function should register all the existing entities
     * TODO add all the units here. Maybe load them from XML?
     */
    public static void register() {
        registerEntity(new Block());
        registerEntity(new Tail());
        registerEntity(new Head());
        registerEntity(new HearthPickup());
        registerEntity(new CompassPickup());
        registerEntity(new SpeedPickup());
        registerEntity(new BulletPickup());
        registered = true;
    }

    /**
     * This function registers a single entity and populates the list.
     *
     * @param entity the entity to register
     */
    public static void registerEntity(Entity entity) {
        if (!entityToType.containsKey(entity)) {
            logger.system(EntityList.class, "Registered entity: " + entity.getClass().toString() + " id: " + entityType);
            typeToEntity.put(entityType, entity.getClass());
            entityToType.put(entity.getClass(), entityType);
            entityType++;
        }
    }

    /**
     * Removes an entity. Not sure why'd you want to do that either way.
     *
     * @param type the type you want to be removed
     */
    public static void removeEntity(int type) {
        typeToEntity.remove(entityToType.get(type));
        entityToType.remove(type);
    }

    /**
     * This function can be used to assign an entity id to an entity. It returns the type
     * unique to the class.
     *
     * @param entity the entity to get the type from
     * @return the type assigned to the class
     */
    public static int getEntityType(Entity entity) {
        if (!registered)
            register();
        if (!entityToType.containsKey(entity.getClass())) {
            logger.warn(EntityList.class, "Entity isn't registered");
            return 0;
        }
        return entityToType.get(entity.getClass());
    }

    /**
     * This function returns the class bound to the entity type. Can be used to instantiate a new object
     *
     * @param type the type to get the class from
     * @return a class bound to the entity type
     */
    public static Class<? extends Entity> getEntity(int type) {
        return typeToEntity.get(type);
    }
}