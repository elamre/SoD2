package com.deeep.sod2.missions;

import com.badlogic.gdx.Gdx;
import com.deeep.sod2.utility.Logger;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/12/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 * First field is the planet system identifier
 * Second field is the name of the planet system
 * Third field is the index of the planet
 * Fourth field is the planet name
 * Fifth field is the amount of levels for the planet
 * Sixth field is the shop data
 */
public class MissionParser {
    private static MissionParser missionParser;
    private HashMap<Integer, World> worlds = new HashMap<>();
    private Scanner scanner;

    public MissionParser() {
        try {
            parseMissions();
        } catch (FileNotFoundException e) {
            Logger.getInstance().error(MissionParser.class, "Could not find save files. Reinstall application.");
            System.exit(0);
        }
    }

    public static MissionParser getMissionParser() {
        if (missionParser == null) {
            missionParser = new MissionParser();
        }
        return missionParser;
    }

    public static void main(String[] args) throws FileNotFoundException {
        MissionParser missionParser = new MissionParser();
        missionParser.parseMissions();
    }

    public HashMap<Integer, World> getWorlds() {
        return worlds;
    }

    public void parseMissions() throws FileNotFoundException {
        scanner = new Scanner(Gdx.files.internal("data/missions.dat").file());
        int systemId;
        String systemName;
        int areaId;
        String areaName;
        int levelAmount;
        String shopData;
        while (scanner.hasNext()) {
            systemId = scanner.nextInt();
            systemName = scanner.next();
            areaId = scanner.nextInt();
            areaName = scanner.next();
            levelAmount = scanner.nextInt();
            shopData = scanner.next();
            if (!worlds.containsKey(systemId)) {
                worlds.put(systemId, new World(systemName));
            }
            worlds.get(systemId).addArea(areaId, new Area(areaName, systemName, shopData, levelAmount));
        }
        System.out.println("Finished parsing missions");
        finishUp();
    }

    private void finishUp() {
        Iterator<Integer> keySetIterator = worlds.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            worlds.get(key).finishAdding();
        }
    }
}
