package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.utility.Settings;


public class PreferencesScreen implements Screen {
    private AwesomeRoadTrip game;
    private Preferences settings;
    private SelectBox resolutionSelect;
    private Table table;
    protected Stage stage;
    protected Texture background;
    private Camera camera;

    public PreferencesScreen(AwesomeRoadTrip game) {
        this.game = game;
        settings = Settings.getPreferences();
        camera = new OrthographicCamera(Settings.getResolutionWidth(), Settings.getResolutionHeight());
        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.getViewport().apply(true);
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        stage.getBatch().setTransformMatrix(stage.getCamera().view);
        stage.getBatch().setProjectionMatrix(stage.getCamera().projection);
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getViewport().update(Settings.getResolutionWidth(), Settings.getResolutionHeight());
        stage.draw();

        Gdx.input.setInputProcessor(stage);
        this.camera.update();
        settings.putBoolean("soundOn", true);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.changeScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().apply(true);
        System.err.println("Resize called in preferences with res: " + width + "x" + height);
        stage.getViewport().update(width, height, true);
        stage.getCamera().update();
        stage.getBatch().begin();
        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.getBatch().end();
        camera.update();

        table.setFillParent(true);
    }

    @Override
    public void show() {
        background = new Texture("worldmap.jpg");
        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        table.pad(Settings.getDEFAULTPAD());
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        CheckBox volumeMute = new CheckBox("Volume mute", skin);
        volumeMute.setChecked(Settings.getPreferences().getBoolean("soundOn"));

        CheckBox fullscreenMode = new CheckBox("Fullscreen", skin);
        fullscreenMode.setChecked(Settings.getPreferences().getBoolean("showFullscreen"));

        TextButton backButton = new TextButton("Back to Menu", skin);
        backButton.setHeight(Settings.getBUTTONHEIGHT());
        backButton.setWidth(Settings.getBUTTONWIDTH());
        backButton.setPosition(Gdx.graphics.getWidth() - 2*backButton.getWidth() - Settings.getDEFAULTPAD(), Settings.getDEFAULTPAD());

        TextButton saveButton = new TextButton("Save", skin);
        saveButton.setHeight(Settings.getBUTTONHEIGHT());
        saveButton.setWidth(Settings.getBUTTONWIDTH());
        saveButton.setPosition(Gdx.graphics.getWidth() - backButton.getWidth() - Settings.getDEFAULTPAD(), Settings.getDEFAULTPAD());

        stage.addActor(backButton);
        stage.addActor(saveButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(new MenuScreen(game));
            }
        });

        saveButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                String[] resolutionValues = ((String) resolutionSelect.getSelected()).split("x");
                resize(Integer.valueOf(resolutionValues[0]), Integer.valueOf(resolutionValues[1]));
                Settings.changeResolution(Integer.valueOf(resolutionValues[0]), Integer.valueOf(resolutionValues[1]),true);
                resolutionSelect.setSelected(Settings.getPreferences().getInteger("resolutionWidth") + "x" + Settings.getPreferences().getInteger("resolutionHeight"));
            }
        });

        volumeMute.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (volumeMute.isChecked()) {
                    Settings.getPreferences().putBoolean("soundOn", true);
                    Settings.getPreferences().flush();

                } else {
                    Settings.getPreferences().putBoolean("soundOn", false);
                    Settings.getPreferences().flush();
                }
            }
        });


        fullscreenMode.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if(fullscreenMode.isChecked()) {
                    Settings.getPreferences().putBoolean("showFullscreen", true);
                    Settings.getPreferences().flush();
                    Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                } else {
                    Settings.getPreferences().putBoolean("showFullscreen", false);
                    Settings.getPreferences().flush();
                    Gdx.graphics.setWindowedMode(Settings.getPreferences().getInteger("resolutionWidth"), Settings.getPreferences().getInteger("resolutionHeight"));
                }
            }
        });

        Slider volumeSlider = new Slider(0.0f, 10.0f, 0.5f, false, skin);
        volumeSlider.setName("Volume");
        volumeSlider.setWidth(Settings.getBUTTONWIDTH());
        volumeSlider.setHeight(Settings.getBUTTONHEIGHT());
        volumeSlider.setValue(Settings.getPreferences().getFloat("volume"));

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.getPreferences().putFloat("volume", volumeSlider.getValue());
                Settings.getPreferences().flush();
            }
        });

        resolutionSelect = new SelectBox(skin);
        resolutionSelect.setWidth(Settings.getBUTTONWIDTH());
        resolutionSelect.setHeight(Settings.getBUTTONHEIGHT());
        resolutionSelect.setName("Resolution");
        resolutionSelect.setItems(Settings.getSupportedResolutions());
        resolutionSelect.setSelected(Settings.getPreferences().getInteger("resolutionWidth") + "x" + Settings.getPreferences().getInteger("resolutionHeight"));
        resolutionSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        table.pad(Settings.getDEFAULTPAD());
        table.columnDefaults(3);
        table.setFillParent(true);
        table.debug();
        table.add(volumeMute).setActorX(Settings.getBUTTONWIDTH());
        table.row();
        table.add(volumeSlider).setActorX(Settings.getBUTTONWIDTH());
        table.row();
        table.add(resolutionSelect).setActorX(Settings.getBUTTONWIDTH());
        table.row();
        table.add(fullscreenMode).setActorX(Settings.getBUTTONWIDTH());
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
        stage.dispose();
    }
}