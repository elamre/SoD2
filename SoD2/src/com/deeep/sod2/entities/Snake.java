package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deeep.sod2.entities.enemyentities.Laser;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.entities.pickups.TempCheckpoint;
import com.deeep.sod2.entities.projectiles.TurretBullet;
import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    private TempCheckpoint tempCheckpoint;
    /** The total amount of lives */
    private int life = 0;
    /** The previous tail size to check if the tail has changed */
    private int previousTailSize = 0;
    /** Direction list to add new actions */
    private ArrayList<Direction> directions = new ArrayList<Direction>();
    /** The head of the snake */
    private Head head;
    /** An array list containing all the tail parts */
    private ArrayList<Tail> tails = new ArrayList<Tail>();
    /** The last tail piece */
    private Tail tail;
    /** The new direction of the snake */
    private Direction newDir = Direction.EAST;
    /** The direction the snake is heading in */
    private Direction curDir = Direction.EAST;
    /** The previous direction of the snake, used for checking */
    private Direction prevDir = Direction.NORTH;
    /** If the snake has moved since previous check */
    private boolean moved = true;
    private int skinId = 0;

    /** Spawn direction */
    //public Direction spawnDirection;
    public Snake(int id) {
        super(id, 0, 0, 0);
        tail = (Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), -1, 0, 0));
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
        tail.setSkin(skinId);
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
        tail.setX(tails.get(i).getX());
        tail.setY(tails.get(i).getY());
        while (i > 0) {
            tails.get(i).setX(tails.get(i - 1).getX());
            tails.get(i).setY(tails.get(i - 1).getY());
            tails.get(i).setDirection(tails.get(i - 1).getDirection());
            i--;
        }
        if (tails.size() > 0) {
            tails.get(0).setDirection(head.getDirection().getOpposite());
            tails.get(0).setX(getX());
            tails.get(0).setY(getY());
        }

        calculateDirection();

        x += curDir.getVector().x;
        y += curDir.getVector().y;

        head.setX(getX());
        head.setY(getY());

        tailAngleCalculate();
        moved = true;
        System.out.println("prevDir: " + prevDir + " curDir: " + curDir);
        prevDir = curDir;
        tail.setDirection(tails.get(tails.size()-1).getDirection().getOpposite());
    }

    public Rectangle getHitBox() {
        return head.getHitBox();
    }

    public boolean overlaps(Rectangle rectangle) {
        return head.overlaps(rectangle);
    }

    public Direction getSnakeDirection() {
        return curDir;
    }

    /**
     * new direction of the snake
     *
     * @param dir new direction
     */
    public void setDirection(Direction dir) {
        this.newDir = dir;
        Logger.getInstance().debug(this.getClass(), "Prev dir: " + prevDir + " Current dir: " + curDir + " next dir: " + newDir);
    }

    /**
     * Calculates the new x and y position for the client. This will make sure that the head wont move to the previous
     * position resulting in an instant death.
     */
    public void calculateDirection() {
        if (newDir != curDir) {
            switch (newDir) {
                case NORTH:
                    if (curDir != Direction.SOUTH)
                        curDir = Direction.NORTH;
                    break;
                case EAST:
                    if (curDir != Direction.WEST)
                        curDir = Direction.EAST;
                    break;
                case SOUTH:
                    if (curDir != Direction.NORTH)
                        curDir = Direction.SOUTH;
                    break;
                case WEST:
                    if (curDir != Direction.EAST)
                        curDir = Direction.WEST;
                    break;
            }
        }
        head.setDirection(curDir);
    }

    public void setSkin(int skin) {
        this.skinId = skin;
        head.setSkin(skin);
        for (Tail tail : tails) {
            tail.setSkin(skin);
        }
        tail.setSkin(skin);
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
        } else if (entity instanceof Laser) {
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
        Collections.sort(tails, new Comparator<Tail>() {
            @Override
            public int compare(Tail o1, Tail o2) {
                if (o1.getPriority() > o2.getPriority())
                    return -1;
                if (o1.getPriority() < o2.getPriority())
                    return 1;
                return 0;
            }
        });
    }

    public void loseLife() {
/*        for (int i = tails.size() - 1; i > -1; i--) {
            if (tails.get(i).getPickup() instanceof HearthPickup) {
                shiftTail(i);
                break;
            }
        }
        sortTails();
        calculateLives();*/
    }

    private void calculateLives() {
        life = 0;
        for (Tail tail : tails) {
            if (tail.getPickup() instanceof HearthPickup) {
                life++;
            }
        }
        if (life <= 0)
            die();
    }

    public ArrayList<Tail> getTails() {
        return tails;
    }

    public void shiftTail(int position) {
        tail.setX(tails.get(tails.size() - 1).getX());
        tail.setY(tails.get(tails.size() - 1).getY());
        tail.setAngle(tails.get(tails.size() - 1).getAngle() + 180);

        for (int i = position; i < tails.size() - 1; i++) {
            tails.get(i).setPickup(tails.get(i + 1).getPickup());
        }
        tails.get(tails.size() - 1).die();
        tails.get(tails.size() - 1).getPickup().die();
        tails.remove(tails.size() - 1);

    }

    private void tailAngleCalculate() {
        tails.get(0).calculateAngled(curDir, prevDir);
        for (int i = 1; i < tails.size(); i++) {
           tails.get(i).calculateAngled(tails.get(i).getDirection(), tails.get(i - 1).getDirection());
        }
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
        this.curDir = checkPointDirection;
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
        return curDir;
    }

}
