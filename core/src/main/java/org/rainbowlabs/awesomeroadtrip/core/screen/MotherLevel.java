package org.rainbowlabs.awesomeroadtrip.core.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.HealthBar;


public class MotherLevel {

    private AwesomeRoadTrip parent;
    private Stage stage;
    private OrthographicCamera cam;
    private Sprite mapSprite;
    private HealthBar healthBar;
    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;



    public MotherLevel(AwesomeRoadTrip awesomeRoadTrip, String background_file) {
        // Placement of background asset
        mapSprite = new Sprite(new Texture(Gdx.files.internal(background_file))); //<-- Place your lvl background here
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

    }

    public HealthBar get_healthbar(){
        return healthBar;
    }

    public OrthographicCamera get_camera(){
        return cam;
    }

    public Stage get_stage(){
        return stage;
    }

    public Sprite getMapSprite(){
        return mapSprite;
    }
}
