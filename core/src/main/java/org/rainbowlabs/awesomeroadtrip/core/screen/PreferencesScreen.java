package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.utility.Settings;

import java.util.Set;


public class PreferencesScreen implements Screen {
    private AwesomeRoadTrip game;
    private Preferences settings;
    private Table table;
    protected Stage stage;
    protected Texture background;

    public PreferencesScreen(AwesomeRoadTrip game) {
        this.game = game;
        settings = Settings.getPreferences();
    }

    @Override
    public void render(float delta) {
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
        stage.draw();

        Gdx.input.setInputProcessor(stage);

        settings.putBoolean("soundOn", true);
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.changeScreen(AwesomeRoadTrip.MENU);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stage.getCamera().update();
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        background = new Texture("worldmap.jpg");
        // Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        table.pad(Settings.getDEFAULTPAD());
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        CheckBox volumeMute = new CheckBox("Volume mute", skin);
        volumeMute.setChecked(Settings.getPreferences().getBoolean("soundOn"));
        TextButton backButton = new TextButton("Back to Menu", skin);
        backButton.setHeight(Settings.getBUTTONHEIGHT());
        backButton.setWidth(Settings.getBUTTONWIDTH());
        backButton.setPosition(Gdx.graphics.getWidth() - backButton.getWidth() - Settings.getDEFAULTPAD(), Settings.getDEFAULTPAD());
        stage.addActor(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.changeScreen(AwesomeRoadTrip.MENU);
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

        SelectBox resolutionSelect = new SelectBox(skin);
        resolutionSelect.setWidth(Settings.getBUTTONWIDTH());
        resolutionSelect.setHeight(Settings.getBUTTONHEIGHT());
        resolutionSelect.setName("Resolution");
        resolutionSelect.setItems(Settings.getSupportedResolutions());
        resolutionSelect.setSelected(String.valueOf(Settings.getPreferences().getInteger("resolutionWidth")) + "x" + String.valueOf(Settings.getPreferences().getInteger("resolutionHeight")));
        resolutionSelect.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int index = resolutionSelect.getSelectedIndex();
                System.out.println(index);
                switch (index) {
                    case 0:
                        Settings.changeResolution(640, 480, Settings.getPreferences().getBoolean("showFullscreen"));
                        resize(640, 480);
                    case 1:
                        Settings.changeResolution(800, 600, Settings.getPreferences().getBoolean("showFullscreen"));
                        resize(800, 600);
                    case 2:
                        Settings.changeResolution(1024, 768, Settings.getPreferences().getBoolean("showFullscreen"));
                        resize(1024, 768);
                    case 3:
                        Settings.changeResolution(1280, 1024, Settings.getPreferences().getBoolean("showFullscreen"));
                        resize(1280, 1024);
                    case 4:
                        Settings.changeResolution(1920, 1080, Settings.getPreferences().getBoolean("showFullscreen"));
                        resize(1920, 1080);
                }
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