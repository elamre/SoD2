package com.deeep.sod2.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.pickups.BulletPickup;
import com.deeep.sod2.entities.pickups.CompassPickup;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.SpeedPickup;
import com.deeep.sod2.tiles.AbstractTile;
import com.deeep.sod2.tiles.EmptyTile;
import com.deeep.sod2.tiles.ObstacleTile;
import com.deeep.sod2.tiles.RegularTile;
import com.deeep.sod2.utility.Logger;

import java.io.IOException;

/**
*Name: Save
*Pack: com.deeep.sod2.io
*User: andreaskruhlmann
*Date: 10/4/13
 */
public class Save {

    public static Save LEVEL_1;
    public int width, height;
    /**
    *0 : empty tile   : BLACK
    *1 : regular tile : GRAY
    *2 : obstacle     : ORANGE
    *3 : turret       : RED
    *4 : checkpoint   : GREEN
    *5 : goal         : YELLOW
    *6 : spawn point  : BLUE
    *META
    *direction :
    *NORTH   :RED
    *SOUTH   :BLUE
    *EAST    :GREEN
    *WEST    YELLOW
     */
    private AbstractTile[] tiles;
    /**
    *Loaded entities from image
    *<p/>
    *0x(FF)FF0000 SPEED
    *0x(FF)00FF00 HEART
    *0x(FF)0000FF COMPASS
    *0x(FF)000000 BULLET
     */
    private Entity[] entities;

    /** Spawn coordinate for the snake*/
    public int spawnX, spawnY;
    
    public Save(int level, EntityManager entityManager) {
        try {
            loadLevel(level, entityManager);
        } catch (IOException e) {
            Logger.getInstance().error(this.getClass(), e.getStackTrace());
        }
    }

    /** Loads all the saves from files */
    public static void loadSaves(EntityManager entityManager) {
        LEVEL_1 = new Save(1, entityManager);
    }

    /**
    *Loads level data from PNG file
     *
    *@param level level number
    *@return byte array of level data
     */
    public void loadLevel(int level, EntityManager entityManager) throws IOException {
        Pixmap image = new Pixmap(Gdx.files.internal("data/save/level"+level+".png"));
        Pixmap entImg = new Pixmap(Gdx.files.internal("data/save/level"+level+"ent.png"));
        width = image.getWidth();
        height = image.getHeight();
        Logger.getInstance().debug(this.getClass(), "Width: "+width+" height: "+height);
        tiles = new AbstractTile[width*height];
        entities = new Entity[width*height];
        for (int y = height-1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getPixel(x, y);
                System.out.println(rgb);
                switch (rgb) {
                    case 0x808080ff: tiles[x+y*width] = new RegularTile(x, y); break;
                    case 0xff6a00ff: tiles[x+y*width] = new ObstacleTile(x, y); break;
                    case 0x0000ffff:
                        tiles[x+y*width] = new RegularTile(x, y);
                        spawnX = x;
                        spawnY = y;
                        break;
                }
            }
        }

        for (int y = 0; y < height-1; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = entImg.getPixel(x, y);
                int r = (rgb>>24)&0xff;
                int g = (rgb>>16)&0xff;
                int b = (rgb>>8)&0xff;
                int a = (rgb)&0xff;
                switch (r) {
                    case 255: entities[x+y*width] = new HearthPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                    case 254: entities[x+y*width] = new SpeedPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                    case 253: entities[x+y*width] = new BulletPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                    case 252: entities[x+y*width] = new CompassPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                }
            }
        }
    }

    /**
    *Returns the tiles of a specific save
     *
    *@return tiles array
     */
    public AbstractTile[] getTiles() {
        return tiles;
    }

    /**
    *Returns the entities of a specific save
     *
    *@return entity array
     */
    public Entity[] getEntities() {
        return entities;
    }

}
