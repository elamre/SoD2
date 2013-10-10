package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.deeep.sod2.gameplay.Map;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.utility.Camera;
import com.deeep.sod2.utility.Logger;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/30/13
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Entity {
    /** The interval between debug messages */
    private final float debugThreshold = 1;
    /** Width and height of the entity */
    protected float width = 1f;
    protected float height = 1f;
    protected boolean debug = false;
    /** The angle the entity is facing */
    protected float angle;
    /** The texture region to be drawn */
    protected TextureRegion textureRegion;
    /** The position of the entity */
    protected float x;
    protected float y;
    /** The id this particular entity goes by */
    private int id;
    /** The type of the entity */
    private int entityType;
    /** The debug timer which will get compared to the threshold */
    private float debugTimer = 0;
    /** The owner of this entity */
    private int owner = 0;
    /** Logger instance */
    private Logger logger = Logger.getInstance();
    /** If the entity has to be centered. Set false if it traverses cells with a speed */
    private boolean center = true;
    private boolean alive = true;
    private Rectangle hitBox = new Rectangle(1, 1, 1, 1);

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Entity() {

    }

    public Entity(int id, int owner, float x, float y) {
        this.entityType = EntityList.getEntityType(this);
        this.owner = owner;
        this.id = id;
        this.x = x;
        this.y = y;
    }


    public Entity(int id, int owner, int x, int y) {
        this.entityType = EntityList.getEntityType(this);
        this.owner = owner;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /** Use this function instead of the constructor */
    public abstract void onCreate();

    /**
     * update function
     *
     * @param deltaT time that has passed since previous update
     */
    public void update(float deltaT) {
        if (debug) {
            if (debugTimer < debugThreshold)
                debugTimer += deltaT;
            else {
                debugTimer = 0;
                logger.debug(this.getClass(), toString());
            }
        }
        implementUpdate_1(deltaT);
    }

    public abstract void implementUpdate_1(float deltaT);

    public void draw(SpriteBatch spriteBatch) {
        if (Camera.getInstance().inVision(x, y, width, height)) {
            if (debug) {
                drawDebug(spriteBatch);
            }
            if (textureRegion != null) {
                if (center)
                    spriteBatch.draw(textureRegion, x + (1 - width) / 2, y + (1 - height) / 2, width / 2, height / 2, width, height, 1, 1, angle);
                else
                    spriteBatch.draw(textureRegion, x, y, width / 2, height / 2, width, height, 1, 1, angle);
            }
            implementDraw_1(spriteBatch);
        }
    }

    public abstract void implementDraw_1(SpriteBatch spriteBatch);

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getEntityType() {
        if (entityType == 0) {
            logger.warn(this.getClass(), "Entity type is zero. Trying to get the id again.");
            entityType = EntityList.getEntityType(this);
        }
        return entityType;
    }

    public Rectangle getHitBox() {
        hitBox.set(x, y, width, height);
        return hitBox;
    }

    public boolean overlaps(Rectangle rectangle) {
        hitBox.set(x, y, width, height);
        if (hitBox.overlaps(rectangle))
            return true;
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    protected void drawDebug(SpriteBatch spriteBatch) {
        ShapeRenderer.setColor(Color.GREEN);
        ShapeRenderer.drawRectangle(spriteBatch, x, y, width, height, true);
    }

    public float getDistance(float x, float y) {
        return (float) Math.sqrt((x - this.x) * (x - this.x) + (y - this.y) * (y - this.y));
    }

    public int getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getOriginX() {
        return x + width / 2;
    }

    public float getOriginY() {
        return y + height / 2;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    protected void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        this.width = 1;
        this.height = 1;
    }

    public boolean isAlive() {
        return alive;
    }

    public String toString() {
        return "type: " + entityType + " id: " + this.id + " [" + (int) getX() + "," + (int) getY() + "] size: [" + width + "," + height + "]";
    }

    public void setCenter(boolean center) {
        this.center = center;
    }

    public void die() {
        alive = false;
    }
}
