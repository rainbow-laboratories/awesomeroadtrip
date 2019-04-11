package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import sun.jvm.hotspot.utilities.BitMap;

import java.awt.*;

public class PauseScreen implements Screen {

    private AwesomeRoadTrip parent;
    private Table table;
    private Label label;
    private Skin skin;

    protected Stage stage;


    public PauseScreen(AwesomeRoadTrip awesomeRoadTrip) {
        this.parent = awesomeRoadTrip;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        label = new Label("Paused" ,skin);
        label.setFontScale(10, 10f);

        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.E)){

            //Switch to paused screen
            parent.changeScreen(AwesomeRoadTrip.RESUME);

        }
    }

    @Override
    public void resize(int width, int height) {
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


        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(AwesomeRoadTrip.APPLICATION);
            }
        });
        TextButton preferences = new TextButton("Preferences", skin);
        preferences.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(AwesomeRoadTrip.PREFERENCES);
            }
        });
        TextButton exit = new TextButton("Resume Game", skin);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.changeScreen(AwesomeRoadTrip.RESUME);
            }
        });
        table.add().expandX().fillX();
        table.add(label).expandX().fillX();
        table.add().expandX().fillX();
        table.row();
        table.row();
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
