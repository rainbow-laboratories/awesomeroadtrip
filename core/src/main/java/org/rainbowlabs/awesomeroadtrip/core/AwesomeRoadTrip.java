package org.rainbowlabs.awesomeroadtrip.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import org.rainbowlabs.awesomeroadtrip.core.screen.*;

public class AwesomeRoadTrip extends Game {

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;
    private PauseScreen pauseScreen;
    private ExampleLevelScreen levelScreen;
    private Screen pausedScreen;
    
    private boolean GAME_PAUSED = false;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int EXAMPLELEVEL = 3;
    public final static int ENDGAME = 4;
    public final static int PAUSESCREEN = 5;
    public final static int RESUME = 6;


    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
    }

    @Override
    public void resize(int width, int height) {
        this.getScreen().resize(width, height);
    }

    @Override
    public void render() {

        // if (Gdx.input.isButtonPressed(Input.Keys.ESCAPE)) {
        //    this.changeScreen(AwesomeRoadTrip.MENU);
        // }

        this.getScreen().render(0);
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

    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if (mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case EXAMPLELEVEL:
                if (mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                if (endScreen == null) endScreen = new EndScreen(this);
                this.setScreen(endScreen);
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
