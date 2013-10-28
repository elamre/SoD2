package com.deeep.sod2.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.entities.Snake;
import com.deeep.sod2.entities.Tail;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 2:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class HUD {
    private static HUD hud;
    private Snake snake;

    public static HUD getHud() {
        if (hud == null) {
            hud = new HUD();
        }
        return hud;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void draw(SpriteBatch spriteBatch) {
        if (snake != null) {
            ArrayList<Tail> tails = snake.getTails();
            int startingIndex = ((tails.size() - 3) > 0) ? tails.size() - 3 : 0;
            for (int i = startingIndex; i < tails.size(); i++) {
                spriteBatch.draw(tails.get(i).getPickup().getTextureRegion(), (i - startingIndex) * 40, Gdx.graphics.getHeight() - 40, 40, 40);
            }
            for (int i = 0; i < snake.getLives(); i++) {
                spriteBatch.draw(tails.get(0).getPickup().getTextureRegion(), Gdx.graphics.getWidth() - ((i + 1) * 40), Gdx.graphics.getHeight() - 40);
            }
            if (snake.getLives() > 3) {
                //TODO draw with some text or somethings
            }
            //TODO draw life amount, coin amount, level progress possibly?
        }
    }
}
