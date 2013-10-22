package com.deeep.sod2.entities.enemyentities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.graphics.Assets;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/20/13
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class LaserTurret extends Entity {
    private ArrayList<Laser> lasers = new ArrayList<>();
    /** The direction the laser is aiming at */
    private Snake.Direction direction = Snake.Direction.NORTH;
    /** The duration the turret will shoot for */
    private float onDuration = 0;
    private float offDuration = 0;
    private boolean shooting = false;
    private float shootTimer = 0;
    private float timer = 0;
    private Entity stickyEntity;

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public LaserTurret() {
    }

    public LaserTurret(int id, int x, int y, int direction, int onTime, int offTime) {
        super(id, -1, x, y);
        onDuration = onTime;
        offDuration = offTime;
        this.direction = this.direction.setDirection(direction);
    }

    /** Use this function instead of the constructor */
    @Override
    public void onCreate() {
        setAngle(direction.getValue() + 90);
        setTextureRegion(Assets.getAssets().getRegion("Tiles/laser"));
        setZ(2);
        stickyEntity = EntityManager.get().getEntity((int) (x + direction.getOpposite().getVector().x), (int) (y + direction.getOpposite().getVector().y));
        System.out.println("own x,y: " + this + " stickentity: " + (int) direction.getOpposite().getVector().x + ", " + (int) direction.getOpposite().getVector().y);


        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void implementUpdate_1(float deltaT) {
        if (timer < onDuration) {
            if (!shooting) {
                int i = 0;
                Entity entity = null;
                lasers.add((Laser) EntityManager.get().addEntitySinglePlayer(new Laser(EntityManager.get().getNextSinglePlayerId(), -1, (int) (x), (int) (y), direction)));
                while (entity == null) {
                    if (y - i < 0)//TODO add more checks here. dont want it to go out of bounds!
                        break;
                    lasers.add((Laser) EntityManager.get().addEntitySinglePlayer(new Laser(EntityManager.get().getNextSinglePlayerId(), -1, (int) (x + (direction.getVector().x * i)), (int) (y + (direction.getVector().y * i)), direction)));
                    i++;
                    entity = EntityManager.get().getEntity((int) (x + (direction.getVector().x * i)), (int) (y + (direction.getVector().y * i)));
                }
            }
            shooting = true;
        } else {
            for (Laser laser : lasers)
                laser.die();
            lasers.clear();
            shooting = false;
        }
        if (timer > onDuration + offDuration) {
            timer = 0;
        }
        if (stickyEntity == null) {
            die();
            for (Laser laser : lasers)
                laser.die();
        } else {
            if (!stickyEntity.isAlive()) {
                die();
                for (Laser laser : lasers)
                    laser.die();
            }
        }
        timer += deltaT;
    }

    @Override
    public void implementDraw_1(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
