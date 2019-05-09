package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class MainScreen implements Screen {
    private AwesomeRoadTrip game;
    protected SpriteBatch batch;
    protected Texture background;
    protected ShapeRenderer shapeRenderer;
    protected Vector2 position;
    protected Stage stage;
    float maxVelocity = 100.0f;
    float playerWidth = 30.0f;
    float playerHeight = 60.0f;

    public MainScreen(AwesomeRoadTrip game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= maxVelocity * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += maxVelocity * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += maxVelocity * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= maxVelocity * delta;
        }
        if (position.x < 0.0f) {
            position.x = 0.0f;
        } else if (position.x > Gdx.graphics.getWidth() - playerWidth) {
            position.x = Gdx.graphics.getWidth() - playerWidth;
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        } else if (position.y > Gdx.graphics.getHeight() - playerHeight) {
            position.y = Gdx.graphics.getHeight() - playerHeight;
        }
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(position.x, position.y, playerWidth, playerHeight);
        shapeRenderer.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.changeScreen(AwesomeRoadTrip.MENU);
        }
    }

    @Override
    public void resize(int width, int height) {
        //stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.getBatch().getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("worldmap.jpg");
        shapeRenderer = new ShapeRenderer();
        position = new Vector2(Gdx.graphics.getWidth() / 2 - playerWidth, playerHeight);

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
        background.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
