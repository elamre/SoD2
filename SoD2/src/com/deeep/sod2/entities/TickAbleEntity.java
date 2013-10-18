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
    protected float tickTime = 0.5f;
    /** This is the tick time the entity will return to after all the tick changes */
    protected float defaultTickTime = 1;
    /**
     * 0 NORTH
     * 1 EAST
     * 2 SOUTH
     * 3 WEST
     */
    protected float direction;
    /** 0 <= Health points >= 255 */
    protected float health;
    /** Level of the entity */
    protected int level;
    /** For multiple tick action usage */
    private ArrayList<TickAction> tickActions = new ArrayList<TickAction>();
    /** This arraylist contains all the not repeatable actions which are due to */
    private ArrayList<TickAction> removeTickActions = new ArrayList<TickAction>();
    /** This arraylist will contain all the speedups */
    private ArrayList<SpeedUp> speedUps = new ArrayList<SpeedUp>();
    /** This arraylist contains all the due to speedups, so we can remove them */
    private ArrayList<SpeedUp> removeSpeedUps = new ArrayList<SpeedUp>();




    protected TickAbleEntity(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    public void increaseSpeed(float amount, float duration, boolean permanent) {
        if (!permanent) {
            speedUps.add(new SpeedUp(duration, amount));
        }
        tickTime -= amount;
    }

    public void addTickAction(TickAction tickAction) {

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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        if (health < 0 || health > 255) return;
        this.health = health;
    }

    public void damage(float d) {
        if (d > health || health - d < 1e-6) health = 0;
        else health -= d;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 0 || level > 255) return;
        this.level = level;
    }

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
