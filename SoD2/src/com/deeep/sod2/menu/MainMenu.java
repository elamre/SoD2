package com.deeep.sod2.menu;
/*
 * Class : MainMenu
 * Package : com.deeep.sod2.menu
 * Author : Andreas
 * Date : 02-10-13
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MainMenu implements Screen{

    private Stage stage;

    public MainMenu() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();

        /** TODO remove */
        Table.drawDebug(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
