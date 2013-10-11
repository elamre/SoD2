package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.deeep.sod2.entities.*;
import com.deeep.sod2.entities.enemyentities.Turret;
import com.deeep.sod2.entities.pickups.BulletPickup;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.entities.pickups.SpeedPickup;
import com.deeep.sod2.entities.projectiles.TurretBullet;
import com.deeep.sod2.io.Save;
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
    /** Spawn point for snake */
    private int spawnX, spawnY;

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
            controller.registerKey(Input.Keys.SPACE, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.fireAction();
                    EntityManager.get().addEntitySinglePlayer(new Coin(EntityManager.get().getNextSinglePlayerId(), snake.getOriginX(), snake.getOriginY()));
                    EntityManager.get().addEntitySinglePlayer(new Coin(EntityManager.get().getNextSinglePlayerId(), snake.getOriginX(), snake.getOriginY()));
                    EntityManager.get().addEntitySinglePlayer(new Coin(EntityManager.get().getNextSinglePlayerId(), snake.getOriginX(), snake.getOriginY()));
                    EntityManager.get().addEntitySinglePlayer(new Coin(EntityManager.get().getNextSinglePlayerId(), snake.getOriginX(), snake.getOriginY()));
                }
            }, InputReactListener.Event.PRESSED);
        }
        this.name = name;
    }

    public void setSkin(int skinId) {
        snake.setSkin(skinId);
    }

    public void setSnakeSpawnPoint(int x, int y) {
        snake.spawnX = x;
        snake.spawnY = y;
    }

    public void setEntityManager() {
        snake = new Snake(EntityManager.get().getNextSinglePlayerId());
        EntityManager.get().addEntitySinglePlayer(snake);
        EntityManager.get().addEntitySinglePlayer(new Turret(EntityManager.get().getNextSinglePlayerId(),5,3,100,100));
        snake.setHead((Head) EntityManager.get().addEntitySinglePlayer(new Head(EntityManager.get().getNextSinglePlayerId(), 0, 0, 0)));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new HearthPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new BulletPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new BulletPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new BulletPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        setSkin(1);
        Camera.getInstance().setFocus(snake, .5f, .5f);
    }

    public void update(float deltaT) {
        if (selfControlled && snake != null) {
            controller.update();
            //snake.update(deltaT);
        }
        if (Gdx.input.isTouched()) {
            float x = Camera.getInstance().getTouchUnitX();
            float y = Camera.getInstance().getTouchUnitY();
            float angle = (float) Math.atan2(( snake.getY()-y) , (snake.getX()-x));
            EntityManager.get().addEntitySinglePlayer(new TurretBullet(EntityManager.get().getNextSinglePlayerId(), x, y, 10, 5, angle));
            //EntityManager.get().addEntitySinglePlayer(new Coin(EntityManager.get().getNextSinglePlayerId(), x, y));
        }
    }
}
