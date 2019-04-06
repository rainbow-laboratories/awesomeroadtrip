package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;


public class PreferencesScreen implements Screen {
    private AwesomeRoadTrip game;
    public static Preferences settings;
    private Table table;
    protected Stage stage;
    protected Texture background;

    public PreferencesScreen(AwesomeRoadTrip game) {
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
//        settings = Gdx.app.getPreferences("settings");
//        settings.putBoolean("volume", true);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.changeScreen(AwesomeRoadTrip.MENU);
        }
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
        table.setFillParent(true);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        CheckBox volumeMute = new CheckBox("Volume mute", skin);
        SelectBox resolutionSelect = new SelectBox(skin);
        resolutionSelect.setName("Resolution");
        String[] resArray = new String[6];
        resArray[0] = "640x480";
        resArray[1] = "800x600";
        resArray[2] = "1024x768";
        resArray[3] = "1280x720";
        resArray[4] = "1920x1080";
        resArray[5] = "Full Screen";
        resolutionSelect.setItems(resArray);
        resolutionSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Object select = resolutionSelect.getSelected();
                System.out.println(resolutionSelect.getSelected());
                switch (select.toString()) {
                    case "640x480":
                        Gdx.graphics.setWindowedMode(640, 480);
                        game.resize(640,480);
                        resolutionSelect.setSelected(select);
                    case "800x600":
                        Gdx.graphics.setWindowedMode(800, 600);
                        game.resize(800,600);
                        resolutionSelect.setSelected(select);
                    case "1024x768":
                        Gdx.graphics.setWindowedMode(1024, 768);
                        game.resize(1024,768);
                        resolutionSelect.setSelected(select);
                    case "1280x720":
                        Gdx.graphics.setWindowedMode(1280, 720);
                        game.resize(1280,720);
                        resolutionSelect.setSelected(select);
                    case "1920x1080":
                        Gdx.graphics.setWindowedMode(1920, 1080);
                        game.resize(1920,1080);
                        resolutionSelect.setSelected(select);
                    case "Full Screen":
                        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                        resolutionSelect.setSelected(select);
                }
            }
        });
        table.add().expandX().fillX();
        table.add(volumeMute).expandX().fillX();
        table.add().expandX().fillX();
        table.add(resolutionSelect).expandX().fillX();
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