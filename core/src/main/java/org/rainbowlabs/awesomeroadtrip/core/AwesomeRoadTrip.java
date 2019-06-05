package org.rainbowlabs.awesomeroadtrip.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import org.rainbowlabs.awesomeroadtrip.core.screen.*;
import org.rainbowlabs.awesomeroadtrip.core.screen.level.*;


public class AwesomeRoadTrip extends Game {
    public LoadingScreen loadingScreen;
    public PreferencesScreen preferencesScreen;
    public MenuScreen menuScreen;
    public MainScreen mainScreen;
    public EndScreen endScreen;
    public PauseScreen pauseScreen;
    public ExampleLevelScreen levelScreen;
    public Screen pausedScreen;
    public Lvl1 lvl1;
    public Lvl2 lvl2;
    public Lvl3 lvl3;
    public Lvl4 lvl4;
    public Lvl5 lvl5;

    public float delta;

    private boolean GAME_PAUSED = false;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;
    public final static int LOADING = 4;
    public final static int PAUSESCREEN = 5;
    public final static int RESUME = 6;
    public final static int LVL1 = 7;
    public final static int LVL2 = 8;
    public final static int LVL3 = 9;
    public final static int LVL4 = 10;
    public final static int LVL5 = 11;

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
        System.out.println(screen);
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
            case LVL1:
                if (lvl1 == null) lvl1 = new Lvl1(this);
                setScreen(lvl1);
                break;
            case LVL2:
                if (lvl2 == null) lvl2 = new Lvl2(this);
                setScreen(lvl2);
                break;
            case LVL3:
                if (lvl3 == null) lvl3 = new Lvl3(this);
                setScreen(lvl3);
                break;
            case LVL4:
                if (lvl4 == null) lvl4 = new Lvl4(this);
                setScreen(lvl4);
                break;
            case LVL5:
                if (lvl5 == null) lvl5 = new Lvl5(this);
                setScreen(lvl5);
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
