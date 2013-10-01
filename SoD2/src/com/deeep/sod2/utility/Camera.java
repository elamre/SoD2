package com.deeep.sod2.utility;

import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 9/30/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class Camera {
    /** The singleton instance */
    private static Camera ourInstance = new Camera();
    /** 2 points to make the boundary box */
    private Vector3 point1;
    private Vector3 point2;
    /** The boundary box itself */
    private BoundingBox boundingBox;
    /** The reference to the frustum */
    private Frustum frustum;

    /** Default constructor */
    private Camera() {
        boundingBox = new BoundingBox();
        point1 = new Vector3(0, 0, 0);
        point2 = new Vector3(1, 1, 0);
    }

    public static Camera getInstance() {
        if (ourInstance == null)
            ourInstance = new Camera();
        return ourInstance;
    }

    public void setFrustum(Frustum frustum) {
        this.frustum = frustum;
    }

    /**
     * This function will check if the designated object is in vision or not. This will save a lot of
     * processing power if we don't draw objects which are outside the vision
     *
     * @param x      the x of the object we want to check
     * @param y      the y of the object we want to check
     * @param width  the width of the object
     * @param height the height of the object
     * @return true if no frustum exists, or if the object is within vision. false otherwise
     */
    public boolean inVision(float x, float y, float width, float height) {
        point1.set(x, y, 0);
        point2.set(x + width, y + height, 0);
        boundingBox.set(point1, point2);
        if (frustum == null)        //no frustum to begin with
            return true;
        if (frustum.boundsInFrustum(boundingBox))
            return true;
        return false;
    }
}
