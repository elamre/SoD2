package com.deeep.sod2.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.deeep.sod2.utility.Camera;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/2/13
 * Time: 8:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShapeRenderer {
    private static Sprite rectangle;
    private static Color color;

    static {
        rectangle = new Sprite(Assets.getAssets().getRegion("snakes/snake_2_tail"));
        setColor(Color.BLACK);
    }

    public static void setColor(Color color1) {
        color = color1;

        rectangle.setColor(color);
    }

    public static void drawRectangle(SpriteBatch batch, float x, float y, float width, float height, boolean filled) {
        if (Camera.getInstance().inVision(x, y, width, height)) {
            if (!filled) {
                drawLine(batch, x, y, x + width, y, 0.01f);
                drawLine(batch, x, y, x, y + height, 0.01f);
                drawLine(batch, x, y + height, x + width, y + height, 0.01f);
                drawLine(batch, x + width, y, x + height, y + height, 0.01f);
            } else {
                rectangle.setPosition(x, y);
                rectangle.setSize(width, height);
                rectangle.draw(batch);
            }
        }
    }

    public static void drawLine(SpriteBatch batch, float x1, float y1, float x2, float y2, float width) {
        if (Camera.getInstance().inVision(x1, y1, Math.max(1, Math.abs(x2 - x1)), Math.max(1, Math.abs(y2 - y1)))) {
            rectangle.setSize(x2 - x1 + width, y2 - y1 + width);
            rectangle.setPosition(x1, y1);
            rectangle.draw(batch);
        }
    }

    public static void drawCircle(int centerX, int centerY, int radius, boolean filled) {

    }
}