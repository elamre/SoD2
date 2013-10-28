package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.pickups.CompassPickup;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.Pickup;
import com.deeep.sod2.graphics.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/3/13
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tail extends Entity implements CollideAble {
    protected int priority = 0;
    private int skinId = 0;
    private Pickup pickup;
    private boolean angled = false;
    private boolean angled2 = false;
    private Direction direction = Direction.NORTH;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Tail() {
    }

    public Tail(int id, int owner, int x, int y) {
        super(id, owner, x, y);
    }

    public void setSkin(int skin) {
        this.skinId = skin;
        if (pickup == null) {
            setTextureRegion(Assets.getAssets().getRegion("snakes/snake_" + skin + "_tail"));
        } else {
            if (angled) {
                setTextureRegion(Assets.getAssets().getRegion("snakes/snake_" + skin + "_body_angled"));
            } else {
                setTextureRegion(Assets.getAssets().getRegion("snakes/snake_" + skin + "_body"));
            }
        }
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {

    }

    @Override
    public void implementUpdate_1(float deltaT) {
        if (pickup != null) {
            pickup.setX(getX());
            pickup.setY(getY());
        }
        if (pickup != null) {
            pickup.update(deltaT);
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAngled(boolean angled) {
        this.angled = angled;
        if (angled) {
            setTextureRegion(Assets.getAssets().getRegion("snakes/snake_" + skinId + "_body_angled"));
        } else {
            setTextureRegion(Assets.getAssets().getRegion("snakes/snake_" + skinId + "_body"));
        }
    }

    public void calculateAngled(Direction newDir, Direction prevDir) {
        if (prevDir == newDir) {
            setAngled(false);
            return;
        } else {
            setAngled(true);
            switch (newDir) {
                case NORTH:
                    if (prevDir == Direction.EAST) {
                        setAngle(180);
                    } else {
                        setAngle(90);
                    }
                    break;
                case EAST:
                    if (prevDir == Direction.NORTH) {
                        setAngle(0);
                    } else {
                        setAngle(90);
                    }
                    break;
                case SOUTH:
                    if (prevDir == Direction.EAST) {
                        setAngle(270);
                    } else {
                        setAngle(0);
                    }
                    break;
                case WEST:
                    if (prevDir == Direction.NORTH) {
                       setAngle(270);
                    } else {
                        setAngle(180);
                    }
                    break;
            }
            angled2 = true;
        }

    }

    public void setAngle(float angle) {
        this.angle = angle;/*
        if (pickup != null)
            pickup.setAngle(angle);*/
    }

    public boolean action(Snake snake) {
        if (pickup != null) {
            return pickup.action(snake);
        }
        return false;
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        if (pickup != null) {
            pickup.draw(spriteBatch);
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void Collide(Entity entity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
        pickup.setWidth(0.5f);
        pickup.setHeight(0.5f);
        pickup.setAngle(270);
    }

    public int getPriority() {
        return pickup.getPriority();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.angle = direction.getValue();
        this.direction = direction;/*
        if (pickup != null)
            pickup.setAngle(angle);*/
    }
}
