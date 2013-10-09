package com.deeep.sod2.entities;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/5/13
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class TickAbleEntity extends Entity {
    /** the tick timer */
    protected float tickTimer = 0;
    /** The tick maximum tick  time. the lower it is, the faster each tick will come */
    protected float tickTime = 1;
    /** This is the tick time the entity will return to after all the tick changes */
    protected float defaultTickTime = 1;
    /**
     * 0 NORTH
     * 1 EAST
     * 2 SOUTH
     * 3 WEST
     */
    protected float direction;
    /** For multiple tick action usage */
    private ArrayList<TickAction> tickActions = new ArrayList<TickAction>();
    /** This arraylist contains all the not repeatable actions which are due to */
    private ArrayList<TickAction> removeTickActions = new ArrayList<TickAction>();
    /** This arraylist will contain all the speedups */
    private ArrayList<SpeedUp> speedUps = new ArrayList<SpeedUp>();
    /** This arraylist contains all the due to speedups, so we can remove them */
    private ArrayList<SpeedUp> removeSpeedUps = new ArrayList<SpeedUp>();

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void increaseSpeed(float amount, float duration, boolean permanent) {
        if (!permanent) {
            speedUps.add(new SpeedUp(duration, amount));
        }
        tickTime -= amount;
    }

    public void addTickAction(TickAction tickAction){

    }

    @Override
    public void implementUpdate_1(float deltaT) {
        implementUpdate_2(deltaT);
        for (SpeedUp speedUp : speedUps) {
            speedUp.update(deltaT);
            if (speedUp.isFinished()) {
                tickTime += speedUp.getAmount();
                removeSpeedUps.add(speedUp);
            }
        }
        for (SpeedUp speedUp : removeSpeedUps) {
            speedUps.remove(speedUp);
        }
        removeSpeedUps.clear();
        tickTimer += deltaT;
        if (tickTimer >= tickTime) {
            move();
            tickTimer -= tickTime;
        }
    }

    public float getTickTime() {
        return tickTime;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public abstract void move();

    public abstract void implementUpdate_2(float deltaT);

    class SpeedUp extends TickAction {
        private float amount = 1f;

        SpeedUp(float duration, float amount) {
            super(duration, false);
            this.amount = amount;
        }

        public float getAmount() {
            return amount;
        }

        @Override
        public void action() {
        }
    }
}
