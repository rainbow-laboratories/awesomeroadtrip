package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.HealthBar;

import java.util.concurrent.TimeUnit;

public class ExampleLevelScreen implements Screen {

    private AwesomeRoadTrip parent;
    private Stage stage;
    private OrthographicCamera cam;
    private float rotationSpeed;
    private SpriteBatch batch;
    private Sprite mapSprite;
    private HealthBar healthBar;
    private TextField userInput;
    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    private long lastUpdate = 0L;




    public ExampleLevelScreen(AwesomeRoadTrip awesomeRoadTrip) {
        // Placement of background asset
        mapSprite = new Sprite(new Texture(Gdx.files.internal("Lvl2.jpg"))); //<-- Place your lvl background here
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        this.parent = awesomeRoadTrip;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera(30, 30 * (h / w));
        batch = new SpriteBatch();

        //Cam positioning
        cam.zoom = 3.3f;
        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
        cam.position.y += 5;

        // Spawn HealthBar
        healthBar = new HealthBar(100, 10);
        healthBar.setPosition(10, Gdx.graphics.getHeight() - 20);
        stage.addActor(healthBar);

        // Place User Input
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        userInput = new TextField("", skin);
        userInput.setMessageText("Antwort");
        userInput.setPosition(30, 30);
        stage.addActor(userInput);
    }

    @Override
    public void render(float delta) {
        if(!parent.isGAME_PAUSED()) {
            handleInput();
            cam.update();
            batch.setProjectionMatrix(cam.combined);

            // clear the screen ready for next set of images to be drawn
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            mapSprite.draw(batch);
            batch.end();

            // Only a example
            if (System.currentTimeMillis() - lastUpdate > TimeUnit.SECONDS.toMillis(5)) {
                healthBar.setValue(healthBar.getValue() - 0.1f);
                lastUpdate = System.currentTimeMillis();
            }
            stage.draw();
            stage.act();
        }
    }

    private void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            //Switch to pause screen
            parent.changeScreen(AwesomeRoadTrip.PAUSESCREEN);
        }else if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            System.out.println("User Input: " + userInput.getText());
        }

        handleCamInput();
    }

    /**
     * Function which controlls the camera.
     * In a real lvl it should not be controlled by the player.
     */
    private void handleCamInput() {
        rotationSpeed = 0.5f;

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_0)) {
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
        stage.dispose();

    }
}
