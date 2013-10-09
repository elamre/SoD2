package com.deeep.sod2.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Snake extends TickAbleEntity {
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

    public Snake() {
        super(0, 0, 0, 0);
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
        tails.add(tail);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementUpdate_2(float deltaT) {
        //TODO send the positions
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * new direction of the snake
     *
     * @param dir new direction
     */
    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    /** Moves the snake and with the tail */
    public void move() {
        int i = tails.size() - 1;
        while (i > 0) {
            tails.get(i).setX(tails.get(i - 1).getX());
            tails.get(i).setY(tails.get(i - 1).getY());
            tails.get(i).setAngle(tails.get(i - 1).getAngle());
            i--;
        }
        if (tails.size() > 0) {
            tails.get(i).setAngle(head.getAngle() + 180);
            tails.get(0).setX(getX());
            tails.get(0).setY(getY());
        }
        calculatePos();
        head.setX(getX());
        head.setY(getY());
        Logger.getInstance().debug(this.getClass(), "FPS: " + Gdx.graphics.getFramesPerSecond());
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

    @Override
    public void collide(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            if (entity instanceof Tail) {
                Logger.getInstance().debug(this.getClass(), "WOIJFWOEIJFWOEIJF");
            }
        }
    }

    public void setSkin(int skin) {
        head.setSkin(skin);
        for (int i = 0; i < tails.size(); i++) {
            tails.get(i).setSkin(skin);
        }
    }

    public void fireAction() {
        if (tails.get(tails.size() - 1).action(this)) {
            tails.remove(tails.size() - 1);
            //TODO Send it to server
        }
    }

    /** the direction of the snake */
    public enum Direction {
        NORTH(90), EAST(0), SOUTH(270), WEST(180);
        int dir;

        Direction(int dir) {
            this.dir = dir;
        }
    }

}
