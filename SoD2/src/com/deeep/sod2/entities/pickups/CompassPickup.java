package com.deeep.sod2.entities.pickups;

import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/6/13
 * Time: 11:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompassPickup extends Pickup {
    private Pickup nearestPickup;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public CompassPickup() {
    }

    public CompassPickup(int id, int x, int y) {
        super(id, x, y, Assets.getAssets().getRegion("upgrades/compass"));
    }

    /**
     * update function
     *
     * @param deltaT time that has passed since previous update
     */
    @Override
    public void update(float deltaT) {
        super.update(deltaT);
        if (pickedUp) {
            lookForNearest();
            if (nearestPickup != null)
                calculateAngle();
        }
        //TODO look for nearest pickup
    }

    public void lookForNearest() {
        float previousDistance = 9999;
        if (nearestPickup != null)
            previousDistance = nearestPickup.getDistance(x, y);
        for (Pickup pickup : EntityManager.get().pickups) {
            if (!pickup.pickedUp) {
                if (pickup.getDistance(x, y) < previousDistance) {
                    nearestPickup = pickup;
                    previousDistance = pickup.getDistance(x, y);
                }
            }
        }
    }

    public void calculateAngle() {
        int deltaX, deltaY;
        deltaX = (int) (nearestPickup.getX() - getX());
        deltaY = (int) (nearestPickup.getY() - getY());

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                setAngle(0);
            } else {
                setAngle(180);
            }
        } else {
            if (deltaY > 0) {
                setAngle(90);
            } else {
                setAngle(270);
            }
        }
    }

    @Override
    public boolean action(Snake owner) {
        EntityManager.get().addEntitySinglePlayer(new PickupAllocator(EntityManager.get().getNextSinglePlayerId(), -1, (int) owner.getX(), (int) owner.getY()));
        return true;
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        priority = 8;
    }
}