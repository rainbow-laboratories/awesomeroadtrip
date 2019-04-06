package org.rainbowlabs.awesomeroadtrip.java;



import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class AwesomeRoadTripDesktop {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Awesome Math Roadtrip";
        config.vSyncEnabled = true;
        config.addIcon("icons/awesome32.png", Files.FileType.Internal);
        config.addIcon("icons/awesome128.png", Files.FileType.Internal);
//        config.setWindowIcon(Files.FileType.Internal, "icons/pined-path32");
//        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

        new LwjglApplication(new AwesomeRoadTrip(), config);
    }
}
