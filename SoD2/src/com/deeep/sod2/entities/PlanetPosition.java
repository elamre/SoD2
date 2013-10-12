package com.deeep.sod2.entities;

/*
 * Class : PlanetPosition
 * Package : com.deeep.sod2.entities
 * Author : Andreas
 * Date : 12-10-13
 */

public class PlanetPosition {

    //TODO Make these pls
    public static final PlanetPosition MERCURY = new PlanetPosition(
            new GUIPlanet("MERCURY", 1f, 0, 0, 80, 80),
            null, //NULL because planet is not in the view, as it is further in the solar system
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );

    public GUIPlanet mercury;
    public GUIPlanet venus;
    public GUIPlanet earth;
    public GUIPlanet mars;
    public GUIPlanet jupiter;
    public GUIPlanet saturn;
    public GUIPlanet uranus;
    public GUIPlanet neptune;
    public GUIPlanet pluto;

    public PlanetPosition(GUIPlanet mercury,
                          GUIPlanet venus,
                          GUIPlanet earth,
                          GUIPlanet mars,
                          GUIPlanet jupiter,
                          GUIPlanet saturn,
                          GUIPlanet uranus,
                          GUIPlanet neptune,
                          GUIPlanet pluto){
        this.mercury = mercury;
        this.venus = venus;
        this.earth = earth;
        this.mars = mars;
        this.jupiter = jupiter;
        this.saturn = saturn;
        this.uranus = uranus;
        this.neptune = neptune;
        this.pluto = pluto;
    }

}
