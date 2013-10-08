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

    /** 0 <= Health points >= 255*/
    protected float health;

    /** Level of the entity*/
    protected int level;

    private ArrayList<SpeedUp> speedUps = new ArrayList<SpeedUp>();
    private ArrayList<SpeedUp> removeSpeedUps = new ArrayList<SpeedUp>();

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {

    }

    public void increaseSpeed(float amount, float duration, boolean permanent) {
        if (!permanent) {
            speedUps.add(new SpeedUp(duration, amount));
        }
        tickTime -= amount;
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

    class SpeedUp {
        private float duration = 1f;
        private float amount = 1f;
        private float counter = 0;

        SpeedUp(float duration, float amount) {
            this.duration = duration;
            this.amount = amount;
        }

        public boolean isFinished() {
            return counter >= duration;
        }

        public float getAmount() {
            return amount;
        }

        public void update(float deltaT) {
            counter += deltaT;
        }
    }
    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        if(health<0 || health>255) return;
        this.health = health;
    }

    public void damage(float d){
        if(d>health || health-d<1e-6) health=0;
        else health-=d;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level<0 || level>255) return;
        this.level = level;
    }
}
