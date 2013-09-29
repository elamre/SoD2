package com.deeep.sod2;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "SoD2";
        cfg.useGL20 = true;         //TODO should be false really
        cfg.width = 1000;
        cfg.height = 480;

        new LwjglApplication(new Core(), cfg);
    }
}
