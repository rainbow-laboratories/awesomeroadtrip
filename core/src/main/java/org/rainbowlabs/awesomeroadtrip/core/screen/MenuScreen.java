package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class MenuScreen implements Screen {
    private AwesomeRoadTrip parent;
    private Table table;
    protected Stage stage;
    protected Texture background;


    public MenuScreen(AwesomeRoadTrip parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        background = new Texture("worldmap.jpg");
        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void render(float delta) {

        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resize MenuScreen");


        // change the stage's viewport when the screen size has changed
        stage.getViewport().update(width, height, true);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences", skin);
        TextButton exit = new TextButton("Exit", skin);
        table.add().expandX().fillX();
        table.add(newGame).expandX().fillX();
        table.add().expandX().fillX();
        table.row().pad(10, 0, 10, 0);
        table.add().expandX().fillX();
        table.add(preferences).expandX().fillX();
        table.add().expandX().fillX();
        table.row();
        table.add().expandX().fillX();
        table.add(exit).expandX().fillX();
        table.add().expandX().fillX();
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
