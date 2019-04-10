package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class EndScreen implements Screen {
    private AwesomeRoadTrip parent;

    public EndScreen(AwesomeRoadTrip awesomeRoadTrip) {
        this.parent = awesomeRoadTrip;
    }

    @Override
    public void render(float delta) {
        Gdx.app.exit();

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
