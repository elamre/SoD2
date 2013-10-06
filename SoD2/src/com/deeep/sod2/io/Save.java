package com.deeep.sod2.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.deeep.sod2.tiles.AbstractTile;
import com.deeep.sod2.tiles.EmptyTile;
import com.deeep.sod2.tiles.ObstacleTile;
import com.deeep.sod2.tiles.RegularTile;
import com.deeep.sod2.utility.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
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

    public static Save LEVEL_1;

    public static void loadSaves(){
        LEVEL_1 = new Save(1);
    }

    public Save(int level) {
        try{loadLevel(level);}catch (IOException e){Logger.getInstance().error(this.getClass(), e.getStackTrace());}
    }

    /**
     * Loads level data from PNG file
     * @param level level number
     * @return byte array of level data
     */
    public void loadLevel(int level) throws IOException {
        BufferedImage img = ImageIO.read(Gdx.files.internal("data/save/level" + level + ".png").file());
        int w = img.getWidth();
        int h = img.getHeight()-1;
        tiles = new AbstractTile[w*h];
        for(int y=0; y<h; y++){
            for(int x=0; x<w; x++){
                int rgb = img.getRGB(x, y);
                switch (rgb){
                    case 0xff808080: tiles[x+y*w] = new RegularTile(x, y); break;
                    default: tiles[x+y*w] = new EmptyTile(x, y); break;
                }
            }
        }
    }


    public AbstractTile[] getTiles() {
        return tiles;
    }

}
