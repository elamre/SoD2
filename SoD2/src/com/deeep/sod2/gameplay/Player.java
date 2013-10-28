package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.*;
import com.deeep.sod2.entities.enemyentities.Laser;
import com.deeep.sod2.entities.enemyentities.Turret;
import com.deeep.sod2.entities.pickups.*;
import com.deeep.sod2.hud.HUD;
import com.deeep.sod2.utility.Camera;
import com.deeep.sod2.utility.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    public static int coins;
    /** If it is a network player, or rather a local player */
    private boolean selfControlled = false;
    /** Player name */
    private String name;
    /** The current selected skin */
    private int skinId;
    /** The controller TODO make an encapsulated class which contains PC an Android controls */
    private TouchScreenController touchControl;
    private Controller controller;
    /** The snake the player owns */
    private Snake snake;
    /** Spawn point for snake */
    private int spawnX, spawnY;

    public Player(String name, boolean selfControlled) {
        this.selfControlled = selfControlled;
        if (selfControlled) {
            controller = new Controller();
            touchControl = new TouchScreenController();
            touchControl.registerArea(new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.NORTH);
                }
            }, 80, 80, 80, 80, InputReactListener.Event.HOLD);
            touchControl.registerArea(new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.EAST);
                }
            }, 160, 0, 80, 80, InputReactListener.Event.HOLD);
            touchControl.registerArea(new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.SOUTH);
                }
            }, 80, 0, 80, 80, InputReactListener.Event.HOLD);
            touchControl.registerArea(new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.WEST);
                }
            }, 0, 0, 80, 80, InputReactListener.Event.HOLD);
            touchControl.registerArea(new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.fireAction();
                }
            }, Gdx.graphics.getWidth() - 240, 0, 240, 80, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.W, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.NORTH);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.S, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.SOUTH);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.A, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.WEST);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.D, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.setDirection(Direction.EAST);
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.SPACE, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.fireAction();
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.O, new InputReactListener() {
                @Override
                public void inputReact() {
                    snake.loseLife();
                }
            }, InputReactListener.Event.PRESSED);
            controller.registerKey(Input.Keys.C, new InputReactListener() {
                @Override
                public void inputReact() {
                    EntityManager.get().clear();
                }
            }, InputReactListener.Event.PRESSED);
        }
        this.name = name;
    }

    public void setSkin(int skinId) {
        snake.setSkin(skinId);
    }

    public void setSnakeSpawnPoint(int x, int y) {
        System.out.println("x,y: " + x + ", " + y);
        spawnX = x;
        spawnY = y;
    }

    public void setEntityManager() {
        System.out.println("- - - -" + spawnX + ":" + spawnY + "- - - -");
        snake = new Snake(spawnX, spawnY, EntityManager.get().getNextSinglePlayerId());
        snake.setCheckpoint(spawnX, spawnY, Direction.NORTH);
        EntityManager.get().addEntitySinglePlayer(snake);
        snake.setHead((Head) EntityManager.get().addEntitySinglePlayer(new Head(EntityManager.get().getNextSinglePlayerId(), 0, 0, 0)));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new HearthPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new HearthPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        snake.addTail((Tail) EntityManager.get().addEntitySinglePlayer(new Tail(EntityManager.get().getNextSinglePlayerId(), 0, spawnX, spawnY)), new BulletPickup(EntityManager.get().getNextSinglePlayerId(), spawnX, spawnY));
        setSkin(1);
        Camera.getInstance().setFocus(snake, .5f, .5f);
        HUD.getHud().setSnake(snake);
    }

    public void update(float deltaT) {
        if (selfControlled && snake != null) {
            controller.update();
            touchControl.update();
        }
        if (Gdx.input.isTouched()) {
            EntityManager.get().addEntitySinglePlayer(new Coin(EntityManager.get().getNextSinglePlayerId(), Camera.getInstance().getTouchUnitX(), Camera.getInstance().getTouchUnitY()));
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        touchControl.draw(spriteBatch);
    }
}
