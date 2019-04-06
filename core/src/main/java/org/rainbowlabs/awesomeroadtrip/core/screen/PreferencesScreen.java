package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;


public class PreferencesScreen implements Screen {
    private AwesomeRoadTrip game;
    public static Preferences settings;

    public PreferencesScreen(AwesomeRoadTrip game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
//        settings = Gdx.app.getPreferences("settings");
//        settings.putBoolean("volume", true);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.changeScreen(AwesomeRoadTrip.MENU);
        }
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