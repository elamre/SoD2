package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Input;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Head;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.entities.Tail;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.utility.Camera;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    /** If it is a network player, or rather a local player */
    private boolean selfControlled = false;
    /** Player name */
    private String name;
    /** The current selected skin */
    private int skinId;
    /** The controller TODO make an encapsulated class which contains PC an Android controls */
    private Controller controller;
    /** The snake the player owns */
    private Snake snake;
    /** Reference to the entity manager */
    private EntityManager entityManager;

    public Player(String name, boolean selfControlled) {
        this.selfControlled = selfControlled;
        if (selfControlled) {
            controller = new Controller();
            controller.registerKey(Input.Keys.W, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Snake.Direction.NORTH);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.S, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Snake.Direction.SOUTH);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.A, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Snake.Direction.WEST);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.D, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Snake.Direction.EAST);
                }
            }, InputReactListener.Event.PRESSED);
        }
        this.name = name;
    }

    public void setSkin(int skinId) {
        snake.setSkin(skinId);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = new EntityManager();

        snake = new Snake();
        snake.setHead((Head) entityManager.addEntitySinglePlayer(new Head(entityManager.getNextSinglePlayerId(), 0, 1, 1)));
        snake.addTail((Tail) entityManager.addEntitySinglePlayer(new Tail(entityManager.getNextSinglePlayerId(), 0, 1, 1)),new HearthPickup(entityManager.getNextSinglePlayerId(), 1, 1));
        snake.addTail((Tail) entityManager.addEntitySinglePlayer(new Tail(entityManager.getNextSinglePlayerId(), 0, 1, 1)),new HearthPickup(entityManager.getNextSinglePlayerId(), 1, 1));
        snake.addTail((Tail) entityManager.addEntitySinglePlayer(new Tail(entityManager.getNextSinglePlayerId(), 0, 1, 1)),new HearthPickup(entityManager.getNextSinglePlayerId(), 1, 1));
        setSkin(1);
        Camera.getInstance().setFocus(snake, .5f, .5f);
    }

    public void update(float deltaT) {
        if (selfControlled && snake != null) {
            controller.update();
            snake.update(deltaT);
        }
    }
}
