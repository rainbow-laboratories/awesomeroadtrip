package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class MenuScreen implements Screen {
    private AwesomeRoadTrip game;
    private Table table;
    protected Stage stage;
    protected Texture background;


    public MenuScreen(AwesomeRoadTrip game) {
        this.game = game;
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
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture("worldmap.jpg");
        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.debug();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButton newGame = new TextButton("New Game", skin);
        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.APPLICATION);
            }
        });
        TextButton lvl1 = new TextButton("Level 1", skin);
        lvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.LVL1);
            }
        });
        TextButton lvl2 = new TextButton("Level 2", skin);
        lvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.LVL2);
            }
        });
        TextButton lvl3 = new TextButton("Level 3", skin);
        lvl3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.LVL3);
            }
        });        TextButton lvl4 = new TextButton("Level 4", skin);
        lvl4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.LVL4);
            }
        });        TextButton lvl5 = new TextButton("Level 5", skin);
        lvl5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.LVL5);
            }
        });
        TextButton preferences = new TextButton("Preferences", skin);
        preferences.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.PREFERENCES);
            }
        });
        TextButton exit = new TextButton("Exit", skin);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.ENDGAME);
            }
        });
        table.add().expandX().fillX();
        table.add(lvl1).expandX().fillX();
        table.add().expandX().fillX();
        table.row().pad(10, 0, 10, 0);
        table.add().expandX().fillX();
        table.add(lvl2).expandX().fillX();
        table.add().expandX().fillX();
        table.row().pad(10, 0, 10, 0);
        table.add().expandX().fillX();
        table.add(lvl3).expandX().fillX();
        table.add().expandX().fillX();
        table.row().pad(10, 0, 10, 0);
        table.add().expandX().fillX();
        table.add(lvl4).expandX().fillX();
        table.add().expandX().fillX();
        table.row().pad(10, 0, 10, 0);
        table.add().expandX().fillX();
        table.add(lvl5).expandX().fillX();
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
        this.dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();

    }
}
