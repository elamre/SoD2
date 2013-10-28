package com.deeep.sod2.network;

import com.deeep.sod2.entities.Entity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/28/13
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerWorld {
    Server server;
    private ArrayList<Entity> entities;

    public ServerWorld(Server server) {
        this.server = server;
    }

    public NetworkPlayer addPlayer(int id) {
        return new NetworkPlayer(this, id);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void update(float deltaT) {

    }
}
