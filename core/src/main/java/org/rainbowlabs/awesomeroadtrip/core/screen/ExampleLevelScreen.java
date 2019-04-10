package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class ExampleLevelScreen implements Screen {

    private AwesomeRoadTrip parent;
    private Stage stage;
    private Texture background;
    private OrthographicCamera cam;
    private float rotationSpeed;
    private SpriteBatch batch;
    private Sprite mapSprite;
    private ShapeRenderer shapeRenderer;
    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    private boolean GAME_PAUSED = false;


    public ExampleLevelScreen(AwesomeRoadTrip awesomeRoadTrip) {
        rotationSpeed = 0.5f;

        mapSprite = new Sprite(new Texture(Gdx.files.internal("Lvl2.jpg")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        this.parent = awesomeRoadTrip;
        stage = new Stage(new ScreenViewport());
        background = new Texture("Lvl2.jpg");
        Gdx.input.setInputProcessor(stage);
        shapeRenderer = new ShapeRenderer();





        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(30, 30 * (h / w));
        batch = new SpriteBatch();


    }


    @Override
    public void render(float delta) {

        if (!GAME_PAUSED) {
            handleInput();
            cam.update();
            batch.setProjectionMatrix(cam.combined);

            // clear the screen ready for next set of images to be drawn
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


            batch.begin();
            mapSprite.draw(batch);
            batch.end();
        }else{
            handleInput();
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            Gdx.gl.glDisable(GL20.GL_BLEND);

            batch.setProjectionMatrix(cam.combined);

            batch.begin();
            //sprite.draw(batch);
            batch.end();

            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(50, 50, 32);
            shapeRenderer.end();
        }
    }

    private void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            GAME_PAUSED = !GAME_PAUSED;
        }
        if (!GAME_PAUSED){
            handleCamInput();
        }
    }

    private void handleCamInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();
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
