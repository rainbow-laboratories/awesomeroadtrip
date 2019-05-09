package org.rainbowlabs.awesomeroadtrip.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import org.rainbowlabs.awesomeroadtrip.core.screen.*;


public class AwesomeRoadTrip extends Game {
    public LoadingScreen loadingScreen;
    public PreferencesScreen preferencesScreen;
    public MenuScreen menuScreen;
    public MainScreen mainScreen;
    public EndScreen endScreen;
    private PauseScreen pauseScreen;
    private ExampleLevelScreen levelScreen;
    private Screen pausedScreen;

    public float delta;

    private boolean GAME_PAUSED = false;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;
    public final static int LOADING = 4;
    public final static int PAUSESCREEN = 5;
    public final static int RESUME = 6;

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void create() {
        this.changeScreen(AwesomeRoadTrip.LOADING);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                setScreen(menuScreen);
                break;
            case PREFERENCES:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if (mainScreen == null) mainScreen = new MainScreen(this);
                setScreen(mainScreen);
                break;
            case ENDGAME:
                if (endScreen == null) endScreen = new EndScreen(this);
                setScreen(endScreen);
                break;
            case LOADING:
                if (loadingScreen == null) loadingScreen = new LoadingScreen(this);
                setScreen(loadingScreen);
                break;
            case PAUSESCREEN:
                if (pauseScreen == null) pauseScreen = new PauseScreen(this);
                pauseScreen = new PauseScreen(this);
                setGAME_PAUSED(true);
                pausedScreen = this.getScreen();
                this.setScreen(pauseScreen);
                break;
            case RESUME:
                this.setScreen(pausedScreen);
                GAME_PAUSED = false;
                break;

        }
    }

    public boolean isGAME_PAUSED() {
        return GAME_PAUSED;
    }

    public void setGAME_PAUSED(boolean GAME_PAUSED) {
        this.GAME_PAUSED = GAME_PAUSED;
    }

}
