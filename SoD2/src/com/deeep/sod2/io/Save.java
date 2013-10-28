package com.deeep.sod2.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.Finish;
import com.deeep.sod2.entities.Obstacle;
import com.deeep.sod2.entities.enemyentities.LaserTurret;
import com.deeep.sod2.entities.enemyentities.Turret;
import com.deeep.sod2.entities.pickups.*;
import com.deeep.sod2.tiles.*;
import com.deeep.sod2.utility.Logger;

import java.io.IOException;

/**
 * Name: Save
 * Pack: com.deeep.sod2.io
 * User: andreaskruhlmann
 * Date: 10/4/13
 */
public class Save {

    public static Save LEVEL_1;
    public int width, height;
    /** Spawn coordinate for the snake */
    public int spawnX, spawnY;
    /**
     * 0 : empty tile   : BLACK
     * 1 : regular tile : GRAY
     * 2 : obstacle     : ORANGE
     * 3 : turret       : RED
     * 4 : checkpoint   : GREEN
     * 5 : goal         : YELLOW
     * 6 : spawn point  : BLUE
     * META
     * direction :
     * NORTH   :RED
     * SOUTH   :BLUE
     * EAST    :GREEN
     * WEST    YELLOW
     */
    private AbstractTile[] tiles;
    /**
     * Loaded entities from image
     * <p/>
     * 0xFF0000(FF) HEART
     * 0xFE0000(FF) SPEED
     * 0xFD0000(FF) BULLET
     * 0xFC0000(FF) COMPASS
     */
    private Entity[] entities;

    public Save(int level) {
        try {
            loadLevel(level);
        } catch (IOException e) {
            Logger.getInstance().error(this.getClass(), e.getStackTrace());
        }
    }

    public Save(String map, String entMap) {
        loadLevel(map, entMap);
    }

    /** Loads all the saves from files */
    public static void loadSaves() {
        LEVEL_1 = new Save(1);
        Logger.getInstance().debug(null, "Loaded all levels");
    }

    /**
     * Loads level data from PNG file
     *
     * @param level level number
     */
    public void loadLevel(int level) throws IOException {
        Pixmap image = new Pixmap(Gdx.files.internal("data/save/level" + level + ".png"));
        Pixmap entImg = new Pixmap(Gdx.files.internal("data/save/level" + level + "ent.png"));
        loadLevel(image, entImg);
    }

    public void loadLevel(String map, String entMap) {
        Pixmap image = new Pixmap(Gdx.files.internal(map));
        Pixmap entImg = new Pixmap(Gdx.files.internal(entMap));
        loadLevel(image, entImg);
    }

    private void loadLevel(Pixmap image, Pixmap entImg) {
        width = image.getWidth();
        height = image.getHeight();
        Logger.getInstance().debug(this.getClass(), "Width: " + width + " height: " + height);
        tiles = new AbstractTile[width * height];
        entities = new Entity[width * height];
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                int yy = height - 2 - y;
                int rgb = image.getPixel(x, y);
                switch (rgb) {
                    case 0x808080ff:
                        tiles[x + yy * width] = new RegularTile(x, yy);
                        break;
                    case 0xff6a00ff:
                        tiles[x + y * width] = new RegularTile(x, yy);
                        //tiles[x + yy * width] = new ObstacleTile(x, yy);
                        EntityManager.get().addEntitySinglePlayer(new Obstacle(EntityManager.get().getNextSinglePlayerId(), -1, x, yy));

                        break;
                    case 0x0000ffff:
                        /**Spawn point location*/
                        tiles[x + y * width] = new RegularTile(x, yy);
                        spawnX = x;
                        spawnY = yy;
                        break;
                    case 0x00ff00ff:
                        tiles[x + yy * width] = new CheckPointTile(x, yy);

                        break;
                }
            }
        }

        /** Data line */
        int directionPixel = image.getPixel(0, height);

        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = entImg.getPixel(x, y);
                int r = (rgb >> 24) & 0xff;
                int g = (rgb >> 16) & 0xff;
                int b = (rgb >> 8) & 0xff;
                int a = (rgb) & 0xff;
                switch (r) {
                    case Entities.HEARTH_PICKUP:
                        entities[x + y * width] = new HearthPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.SPEED_PICKUP:
                        entities[x + y * width] = new SpeedPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.BULLET_PICKUP:
                        entities[x + y * width] = new BulletPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.COMPASS_PICKUP:
                        entities[x + y * width] = new CompassPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.KEY_PICKUP:
                        entities[x + y * width] = new KeyPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.TELEPORT_PICKUP:
                        entities[x + y * width] = new TeleportPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.CHECKPOINT_PICKUP:
                        entities[x + y * width] = new CheckpointPickup(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.ENEMY_TURRET:
                        entities[x + y * width] = new Turret(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2, g, b);
                        break;
                    case Entities.FINISH:
                        entities[x + y * width] = new Finish(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2);
                        break;
                    case Entities.ENEMY_LASER_TURRET:
                        int dir = g;
                        int onTime;
                        int offTime;
                        offTime = b & 0b11110000;
                        offTime = offTime >> 4;
                        onTime = b & 0b00001111;
                        entities[x + y * width] = new LaserTurret(EntityManager.get().getNextSinglePlayerId(), x, height - y - 2, dir, onTime, offTime);
                        break;
                }
            }
        }
    }

    /**
     * Returns the tiles of a specific save
     *
     * @return tiles array
     */
    public AbstractTile[] getTiles() {
        return tiles;
    }

    /**
     * Returns the entities of a specific save
     *
     * @return entity array
     */
    public Entity[] getEntities() {
        return entities;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }
}
