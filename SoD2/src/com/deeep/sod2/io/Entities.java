package com.deeep.sod2.io;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/18/13
 * Time: 4:30 PM
 * All the pickups should have a red tint
 */
public class Entities {
    /** Red */
    public static final int HEARTH_PICKUP = 255;
    public static final int CHECKPOINT_PICKUP = 254;
    public static final int COMPASS_PICKUP = 253;
    public static final int BULLET_PICKUP = 252;
    public static final int KEY_PICKUP = 251;
    public static final int SPEED_PICKUP = 250;
    public static final int TELEPORT_PICKUP = 249;
    public static final int FINISH = 248;
    public static final int ENEMY_TURRET = 1;
    /** Green = direction in dir/5 (90/5 = 18), Blue = on off time (1111 1111 = 15 s on 15 s off). */
    public static final int ENEMY_LASER_TURRET = 2;
    /**                                         */
}
