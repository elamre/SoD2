package com.deeep.sod2.network;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/19/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    private static int entityId = 0;

    public Server() {

    }

    public static void main(String[] args) {

    }

    public int getNewEntityId() {
        return entityId++;
    }
}
