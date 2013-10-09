package com.deeep.sod2.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.deeep.sod2.graphics.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/8/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class TouchScreenController {
    private Rectangle mouseRec;
    private ArrayList<Region> regions = new ArrayList<Region>();

    public TouchScreenController() {
        mouseRec = new Rectangle(1, 1, 1, 1);
    }

    public int addRegion(int x, int y, int width, int height, InputReactListener inputReactListener) {
        return 1;
    }

    public void update(float deltaT) {
        if (Gdx.input.isTouched()) {
            mouseRec.set(Gdx.input.getX(), Gdx.input.getY(), 1, 1);
            for (Region region : regions) {
                region.checkAction(mouseRec);
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (Region region : regions) {
            region.draw(spriteBatch);
        }
    }

    public class Region {
        private Rectangle rectangle;
        private Sprite sprite;
        private InputReactListener inputReactListener;
        private int id;

        public Region(int id, Sprite sprite, InputReactListener inputReactListener) {
            this.sprite = sprite;
            this.rectangle = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
            this.inputReactListener = inputReactListener;
            this.id = id;

        }

        public Region(int id, int x, int y, int width, int height, InputReactListener inputReactListener) {
            rectangle = new Rectangle(x, y, width, height);
            this.inputReactListener = inputReactListener;
            this.id = id;
        }

        public void checkAction(Rectangle rectangle) {
            if (this.rectangle.contains(rectangle)) {
                inputReactListener.inputReact();
            }
        }

        public void draw(SpriteBatch spriteBatch) {
            if (sprite == null) {
                ShapeRenderer.drawRectangle(spriteBatch, rectangle, false);
            } else {
                sprite.draw(spriteBatch);
            }
        }
    }
}
