package org.rainbowlabs.awesomeroadtrip.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import org.rainbowlabs.awesomeroadtrip.core.screen.*;
import org.rainbowlabs.awesomeroadtrip.core.utility.Settings;

import java.util.Set;

import static com.badlogic.gdx.Gdx.graphics;

public class AwesomeRoadTrip extends Game {
    public LoadingScreen loadingScreen;
    public PreferencesScreen preferencesScreen;
    public MenuScreen menuScreen;
    public MainScreen mainScreen;
    public EndScreen endScreen;
    public float delta;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;
    public final static int LOADING = 4;

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void create() {
        Settings.initializeSettings();
        Settings.getPreferences().flush();
        this.changeScreen(new LoadingScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(Settings.getPreferences().getInteger("resolutionWidth"), Settings.getPreferences().getInteger("resolutionHeight"));
    }

    public void changeScreen(Screen screen) {
        this.setScreen(screen);
    }
}
