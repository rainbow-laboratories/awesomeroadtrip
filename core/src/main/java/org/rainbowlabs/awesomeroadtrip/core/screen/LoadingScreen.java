package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

import java.awt.*;

public class LoadingScreen implements Screen {
    private AwesomeRoadTrip game;

    public LoadingScreen(AwesomeRoadTrip game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        this.game.changeScreen(AwesomeRoadTrip.MENU);
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
