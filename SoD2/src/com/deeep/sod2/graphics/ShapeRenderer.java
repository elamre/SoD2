package com.deeep.sod2.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
        rectangle = Assets.getAssets().getBlankSprite();//new Sprite(Assets.getAssets().getRegion("snakes/snake_2_tail"));
        setColor(Color.WHITE);
    }

    public static void setColor(Color color1) {
        color = color1;
        rectangle.setColor(color);
    }

    public static void drawRectangle(SpriteBatch spriteBatch, Rectangle rectangle, boolean filled) {
        drawRectangle(spriteBatch, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), filled);
    }

    public static void drawRectangle(SpriteBatch batch, float x, float y, float width, float height, boolean filled) {
        if (Camera.getInstance().inVision(x, y, width, height)) {
            if (!filled) {
                drawLine(batch, x, y, x + width, y, 0.025f);
                drawLine(batch, x, y, x, y + height, 0.025f);
                drawLine(batch, x, y + height, x + width, y + height, 0.025f);
                drawLine(batch, x + width, y, x + height, y + height, 0.025f);
            } else {
                rectangle.setPosition(x, y);
                rectangle.setSize(width, height);
                rectangle.draw(batch);
            }
        }
    }

    public static void drawLine(SpriteBatch batch, float x1, float y1, float x2, float y2, float width) {
        if (Camera.getInstance().inVision(x1, y1, Math.max(1, Math.abs(x2 - x1)), Math.max(1, Math.abs(y2 - y1)))) {
            rectangle.setSize(x2 - x1 + width / 2, y2 - y1 + width / 2);
            rectangle.setPosition(x1 - width / 2, y1);
            rectangle.draw(batch);
        }
    }

    public static void drawCircle(int centerX, int centerY, int radius, boolean filled) {

    }
}
