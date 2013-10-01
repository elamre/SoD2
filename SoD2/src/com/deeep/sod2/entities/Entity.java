package com.deeep.sod2.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.deeep.sod2.graphics.ShapeRenderer;
import com.deeep.sod2.utility.Camera;
import com.deeep.sod2.utility.Logger;

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
    /** The angle of the entity IN DEGREES. */
    protected boolean debug = true;
    /** The position of the entity */
    public float x;
    public float y;
    protected float angle;
    protected TextureRegion textureRegion;
    /** The id this particular entity goes by */
    private int id;
    /** The type of the entity */
    private int entityType;
    /** The debug timer which will get compared to the threshold */
    private float debugTimer = 0;
    /** The owner of this entity */
    private int owner = 0;
    private Logger logger = Logger.getInstance();

    /** USE THIS ONLY FOR REGISTERING THE ENTITY! SHOULD NOT BE USED OTHERWISE! */
    public Entity() {

    }


    public Entity(int id, int owner, int x, int y) {
        this.entityType = EntityList.getEntityType(this);
        this.owner = owner;
        this.id = id;
        onCreate();
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
            implementDraw_1(spriteBatch);
            if (debug) {
                drawDebug(spriteBatch);
            }
            if (textureRegion != null) {
                spriteBatch.draw(textureRegion, x + (1 - width), y + (1 - height), width / 2, height / 2, width, height, 1, 1, angle);
                spriteBatch.setColor(.7f, .7f, .7f, .7f);
                spriteBatch.draw(textureRegion, x - (1 - width) * 2, y - (1 - height) * 2, width / 2, height / 2, width, height, 1, 1, angle);
                spriteBatch.setColor(1, 1, 1, 1);
            }
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
        this.width = 0.8f;
        this.height = 0.8f;
    }

    public String toString() {
        return "id: " + this.id + " [" + (int) getX() + "," + (int) getY() + "] size: [" + width + "," + height + "]";
    }
}
