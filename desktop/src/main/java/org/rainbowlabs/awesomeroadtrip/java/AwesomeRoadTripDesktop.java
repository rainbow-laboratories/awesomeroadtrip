package org.rainbowlabs.awesomeroadtrip.java;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class AwesomeRoadTripDesktop {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Awesome Math Roadtrip");
        config.useVsync(true);
        config.setWindowIcon( Files.FileType.Internal,"icons/awesome32.png");
//        config.setWindowIcon(Files.FileType.Internal, "icons/pined-path32");
//        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());

        new Lwjgl3Application(new AwesomeRoadTrip(), config);
    }
}
