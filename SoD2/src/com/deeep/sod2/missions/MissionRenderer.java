package com.deeep.sod2.missions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.graphics.Renderer;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/13/13
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MissionRenderer extends Renderer {
    public MissionRenderer(SpriteBatch spriteBatch) {
        super(spriteBatch);
        setCameraPosition(5, 3);
    }

    @Override
    public void renderBackground(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void renderObjects(SpriteBatch spriteBatch) {
        Iterator<Integer> keySetIterator = MissionParser.getMissionParser().getWorlds().keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            MissionParser.getMissionParser().getWorlds().get(key).draw(spriteBatch);
        }
    }

    @Override
    public void renderHUD(SpriteBatch spriteBatch) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
