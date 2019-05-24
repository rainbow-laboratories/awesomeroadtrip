package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.utility.Settings;

public class LoadingScreen implements Screen {
    private AwesomeRoadTrip game;

    public LoadingScreen(AwesomeRoadTrip game) {

        this.game = game;
        if (Settings.getPreferences().getBoolean("showFullscreen")) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        } else {
            Gdx.graphics.setWindowedMode(Settings.getPreferences().getInteger("resolutionWidth"), Settings.getPreferences().getInteger("resolutionHeight"));
        }
    }

    @Override
    public void render(float delta) {
        this.game.changeScreen(new MenuScreen(game));
    }

    @Override
    public void resize(int width, int height) {
        System.err.println("Resize called in loading with res: " + width + "x" + height);
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
