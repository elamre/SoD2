package com.deeep.sod2.network;

import com.deeep.sod2.entities.Entity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/19/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private static int entityId = 0;
    private ArrayList<Entity> entities;
    private ArrayList<String> players;

    public Server() {

    }

    public static void main(String[] args) {

    }

    public void update(float deltaT) {

    }

    public int getNewEntityId() {
        return entityId++;
    }
}
