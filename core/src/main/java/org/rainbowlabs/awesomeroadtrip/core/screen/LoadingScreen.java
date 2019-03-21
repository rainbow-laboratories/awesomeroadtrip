package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Screen;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class LoadingScreen implements Screen {

    private AwesomeRoadTrip parent;

    public LoadingScreen(AwesomeRoadTrip awesomeRoadTrip) {
        this.parent = awesomeRoadTrip;
    }

    @Override
    public void render(float delta) {
        parent.changeScreen(AwesomeRoadTrip.MENU);

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
