package com.deeep.sod2.network;

import com.deeep.sod2.entities.Snake;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/28/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkPlayer {
    private Snake snake;
    private int id = 0;

    public NetworkPlayer(ServerWorld serverWorld, int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
