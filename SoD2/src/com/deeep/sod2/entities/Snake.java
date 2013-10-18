package com.deeep.sod2.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.entities.pickups.TempCheckpoint;
import com.deeep.sod2.entities.projectiles.TurretBullet;
import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 */
public class Snake extends TickAbleEntity implements CollideAble {
    /** X and Y coordinate of latest checkpoint */
    public float checkPointX, checkPointY;
    /** Direction of the latest checkpoint */
    public Direction checkPointDirection = Direction.SOUTH;
    /** Spawn X and Y coordinate */
    public float spawnX;
    public float spawnY;
    private int life = 0;
    private int previousTailSize = 0;
    /** Direction list to add new actions */
    private ArrayList<Direction> directions = new ArrayList<Direction>();
    /** The head of the snake */
    private Head head;
    /** An array list containing all the tail parts */
    private ArrayList<Tail> tails = new ArrayList<Tail>();
    /** The direction the snake is heading in */
    private Direction dir = Direction.EAST;
    /** The previous direction of the snake, used for checking */
    private Direction prevDir = Direction.EAST;
    /** If the snake has moved since previous check */
    private boolean moved = true;
    private TempCheckpoint tempCheckpoint;

    /** Spawn direction */
    //public Direction spawnDirection;
    public Snake(int id) {
        super(id, 0, 0, 0);
    }

    public void setCheckpoint(int x, int y, Direction dir) {
        if (this.tempCheckpoint != null) {
            this.tempCheckpoint.die();
        }
        this.checkPointX = x;
        this.checkPointY = y;
        this.checkPointDirection = dir;
    }

    public void setCheckpoint(TempCheckpoint tempCheckpoint) {
        if (this.tempCheckpoint != null) {
            this.tempCheckpoint.die();
        }
        this.checkPointX = tempCheckpoint.x;
        this.checkPointY = tempCheckpoint.y;
        this.checkPointDirection = tempCheckpoint.getDirection();
        this.tempCheckpoint = tempCheckpoint;
    }

    /**
     * Sets the head of the snake.
     *
     * @param head the head of a snake
     */
    public void setHead(Head head) {
        this.head = head;
    }

    /**
     * Adds a new tail to the snake
     *
     * @param tail the tail to add
     */
    public void addTail(Tail tail, Pickup pickUp) {
        pickUp.onCreate();
        tail.setPickup(pickUp);
        tail.setSkin(3);
        tails.add(tail);
        pickUp.pickedUp = true;
    }

    public void tailChange() {
        sortTails();
        calculateLives();
    }

    public int getLives() {
        return life;
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
    }

    @Override
    public void implementUpdate_2(float deltaT) {
        if (previousTailSize != tails.size()) {
            tailChange();
            previousTailSize = tails.size();
            calculateLives();
        }
        //TODO send the positions
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
    }

    /** Moves the snake and with the tail */
    public void move() {
        int i = tails.size() - 1;
        while (i > 0) {
            tails.get(i).setX(tails.get(i - 1).getX());
            tails.get(i).setY(tails.get(i - 1).getY());
            //tails.get(i).setAngle(tails.get(i - 1).getAngle());
            i--;
        }
        if (tails.size() > 0) {
            //tails.get(i).setAngle(head.getAngle() + 180);
            tails.get(0).setX(getX());
            tails.get(0).setY(getY());
        }
        calculatePos();
        head.setX(getX());
        head.setY(getY());
        moved = true;
        Logger.getInstance().debug(this.getClass(), "FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    public Rectangle getHitBox() {
        return head.getHitBox();
    }

    public boolean overlaps(Rectangle rectangle) {
        return head.overlaps(rectangle);
    }

    public Direction getSnakeDirection() {
        return dir;
    }

    /**
     * new direction of the snake
     *
     * @param dir new direction
     */
    public void setDirection(Direction dir) {
        System.out.println("Current dir: " + this.dir + " next dir: " + dir);
        this.dir = dir;
        prevDir = dir;
    }

    /**
     * Calculates the new x and y position for the client. This will make sure that the head wont move to the previous
     * position resulting in an instant death.
     */
    public void calculatePos() {
        switch (dir) {
            case NORTH:
                if (prevDir != Direction.SOUTH) {
                    setY(getY() + 1);
                    prevDir = dir;
                } else {
                    dir = prevDir;
                    calculatePos();
                }
                break;
            case EAST:
                if (prevDir != Direction.WEST) {
                    setX(getX() + 1);
                    prevDir = dir;
                } else {
                    dir = prevDir;
                    calculatePos();
                }
                break;
            case SOUTH:
                if (prevDir != Direction.NORTH) {
                    setY(getY() - 1);
                    prevDir = dir;
                } else {
                    dir = prevDir;
                    calculatePos();
                }
                break;
            case WEST:
                if (prevDir != Direction.EAST) {
                    setX(getX() - 1);
                    prevDir = dir;
                } else {
                    dir = prevDir;
                    calculatePos();
                }
                break;
        }
        head.setAngle(dir.dir);
    }

    public void setSkin(int skin) {
        head.setSkin(skin);
        for (int i = 0; i < tails.size(); i++) {
            tails.get(i).setSkin(skin);
        }
    }

    public void fireAction() {
        if (tails.size() > 0)
            if (tails.get(tails.size() - 1).action(this)) {
                tails.get(tails.size() - 1).die();
                tails.remove(tails.size() - 1);
                //TODO Send it to server
            }
    }

    public boolean moved() {
        boolean temp = moved;
        moved = false;
        return temp;
    }

    @Override
    public void Collide(Entity entity) {
        if (entity instanceof TurretBullet) {
            //TODO check for hearths
            if (life > 0) {
                loseLife();
                entity.die();
            } else {
                die();
                //TODO die
            }
        } else if (entity instanceof Obstacle) {
            if (life > 0) {
                loseLife();
                entity.die();
            } else {
                die();
                //TODO die
            }
        }
    }

    private void sortTails() {

    }

    public void loseLife() {
        for (int i = tails.size() - 1; i > -1; i--) {
            if (tails.get(i).getPickup() instanceof HearthPickup) {
                shiftTail(i);
                break;
            }
        }
        sortTails();
    }

    private void calculateLives() {
        life = 0;
        for (int i = 0; i < tails.size(); i++) {
            if (tails.get(i).getPickup() instanceof HearthPickup) {
                life++;
            }
        }
        if (life <= 0)
            die();
    }

    public ArrayList<Tail> getLastTails(int amount) {
        ArrayList<Tail> tailParts = new ArrayList<>(amount);
        int i = tails.size();
        while (i > 0 || (tails.size() - i) >= amount) {
            tailParts.add(tails.get(i));
        }
        return tailParts;
    }

    public void shiftTail(int position) {
        for (int i = position; i < tails.size() - 1; i++) {
            tails.get(i).setPickup(tails.get(i + 1).getPickup());
        }
        tails.get(tails.size() - 1).die();
        tails.get(tails.size() - 1).getPickup().die();
        tails.remove(tails.size() - 1);
    }

    public void die() {
        for (Tail tail : tails) {
            tail.die();
        }
        tails.clear();
        onDeath();
    }

    /** Happens once the snake runs out of lives */
    public void onDeath() {
        x = checkPointX;
        y = checkPointY;
        head.setX(x);
        head.setY(y);
        this.dir = checkPointDirection;
        addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), this.getId(), -1, -1)), new HearthPickup(EntityManager.get().getNextSinglePlayerId(), -1, -1));
        addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), this.getId(), -1, -1)), new HearthPickup(EntityManager.get().getNextSinglePlayerId(), -1, -1));
        addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), this.getId(), -1, -1)), new HearthPickup(EntityManager.get().getNextSinglePlayerId(), -1, -1));


    }

    @Override
    public void setY(float y) {
        head.setX(y);
        super.setY(y);
    }

    @Override
    public void setX(float x) {
        head.setX(x);
        super.setX(x);
    }

    public Direction getDir() {
        return dir;
    }

    /** the direction of the snake */
    public enum Direction {
        NORTH(90), EAST(0), SOUTH(270), WEST(180);
        int dir;
        float radians;

        Direction(int dir) {
            this.dir = dir;
            this.radians = (float) Math.toRadians(dir);
        }

        public Direction getOpposite() {
            if (this == NORTH)
                return SOUTH;
            if (this == EAST)
                return WEST;
            if (this == SOUTH)
                return NORTH;
            if (this == WEST)
                return EAST;
            return NORTH;
        }

        public int getValue() {
            return dir;
        }

        public float getRadians() {
            return radians;
        }
    }

}
