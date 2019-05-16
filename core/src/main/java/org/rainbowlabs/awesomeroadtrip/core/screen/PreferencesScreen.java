package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

import java.awt.*;


public class PreferencesScreen implements Screen {
    private AwesomeRoadTrip game;
    public static Preferences settings;
    private Table table;
    protected Stage stage;
    protected Texture background;
    protected static float buttonWidth = 125.0f;
    protected static float buttonHeight = 50.0f;
    protected static float defaultPad = 25.0f;

    public PreferencesScreen(AwesomeRoadTrip game) {
        this.game = game;
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
//        settings = Gdx.app.getPreferences("settings");
//        settings.putBoolean("volume", true);
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
        table.pad(defaultPad);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        CheckBox volumeMute = new CheckBox("Volume mute", skin);
        TextButton backButton = new TextButton("Back to Menu", skin);
        backButton.setHeight(buttonHeight);
        backButton.setWidth(buttonWidth);
        backButton.setPosition(Gdx.graphics.getWidth() - backButton.getWidth() - defaultPad, defaultPad);
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
                }
            }
        });
        Slider volumeSlider = new Slider(0.0f, 10.0f, 0.5f, false, skin);
        volumeSlider.setName("Volume");
        volumeSlider.setWidth(buttonWidth);
        volumeSlider.setHeight(buttonHeight);
        SelectBox resolutionSelect = new SelectBox(skin);
        resolutionSelect.setWidth(buttonWidth);
        resolutionSelect.setHeight(buttonHeight);
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
                int index = resolutionSelect.getSelectedIndex();
                System.out.println(index);
                switch (index) {
                    case 0:
                        resize(640, 480);
                    case 1:
                        resize(800, 600);
                    case 2:
                        resize(1024, 768);
                    case 3:
                        resize(1280, 1024);
                    case 4:
                        resize(1920, 1080);
                        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                    case 5:
                }
            }
        });
        table.pad(defaultPad);
        table.columnDefaults(3);
        table.setFillParent(true);
        table.debug();
        table.add(volumeMute).setActorX(buttonWidth);
        table.row();
        table.add(volumeSlider).setActorX(buttonWidth);
        table.row();
        table.add(resolutionSelect).setActorX(buttonWidth);
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