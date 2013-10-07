package com.deeep.sod2.io;

import com.badlogic.gdx.Gdx;
import com.deeep.sod2.entities.Entity;
import com.deeep.sod2.entities.EntityManager;
import com.deeep.sod2.entities.pickups.BulletPickup;
import com.deeep.sod2.entities.pickups.CompassPickup;
import com.deeep.sod2.entities.pickups.HearthPickup;
import com.deeep.sod2.entities.pickups.SpeedPickup;
import com.deeep.sod2.tiles.AbstractTile;
import com.deeep.sod2.tiles.EmptyTile;
import com.deeep.sod2.tiles.RegularTile;
import com.deeep.sod2.utility.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Name: Save
 * Pack: com.deeep.sod2.io
 * User: andreaskruhlmann
 * Date: 10/4/13
 */
public class Save {

    /**
     * 0 : empty tile   : BLACK
     * 1 : regular tile : GRAY
     * 2 : obstacle     : ORANGE
     * 3 : turret       : RED
     * 4 : checkpoint   : GREEN
     * 5 : goal         : YELLOW
     * 6 : spawn point  : BLUE
     *  META
     *      direction :
     *          NORTH   :RED
     *          SOUTH   :BLUE
     *          EAST    :GREEN
     *          WEST    YELLOW
     */
    private AbstractTile[] tiles;

    /**Loaded entities from image
     *
     * 0x(FF)FF0000 SPEED
     * 0x(FF)00FF00 HEART
     * 0x(FF)0000FF COMPASS
     * 0x(FF)000000 BULLET
    */
    private Entity[] entities;

    public int width, height;

    public static Save LEVEL_1;

    /**
     * Loads all the saves from files
     */
    public static void loadSaves(EntityManager entityManager){
        LEVEL_1 = new Save(1, entityManager);
    }

    public Save(int level, EntityManager entityManager) {
        try{loadLevel(level, entityManager);}catch (IOException e){Logger.getInstance().error(this.getClass(), e.getStackTrace());}
    }

    /**
     * Loads level data from PNG file
     * @param level level number
     * @return byte array of level data
     */
    public void loadLevel(int level, EntityManager entityManager) throws IOException {
        BufferedImage img = ImageIO.read(Gdx.files.internal("data/save/level" + level + ".png").file());
        BufferedImage entImg = ImageIO.read(Gdx.files.internal("data/save/level" + level + "ent.png").file());
        width = img.getWidth();
        height = img.getHeight();
        tiles = new AbstractTile[width*height];
        entities = new Entity[width*height];
        for(int y=height-1; y>=0; y--){
            for(int x=0; x<width; x++){
                int rgb = img.getRGB(x, y);
                switch (rgb){
                    case 0xff808080: tiles[x+y*width] = new RegularTile(x, y); break;
                    default: tiles[x+y*width] = new EmptyTile(x, y); break;
                }
            }
        }

        for(int y=0; y<height-1; y++){
            for(int x=0; x<width; x++){
                int rgb = entImg.getRGB(x, y);
                switch (rgb){
                    case 0xff808080: break;
                    case 0xffff0000: entities[x+y*width] = new SpeedPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                    case 0xff00ff00: entities[x+y*width] = new HearthPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                    case 0xff0000ff: entities[x+y*width] = new CompassPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                    case 0xff000000: entities[x+y*width] = new BulletPickup(entityManager.getNextSinglePlayerId(), x, height-y-2); break;
                }
            }
        }
    }

    /**
     * Returns the tiles of a specific save
     * @return tiles array
     */
    public AbstractTile[] getTiles() {
        return tiles;
    }

    /**
     * Returns the entities of a specific save
     * @return entity array
     */
    public Entity[] getEntities(){
        return entities;
    }

}
