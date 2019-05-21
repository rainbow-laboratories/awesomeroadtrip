package org.rainbowlabs.awesomeroadtrip.java;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.utility.Settings;

public class AwesomeRoadTripDesktop {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Awesome Math Roadtrip");
        config.useVsync(true);
        config.setWindowIcon( Files.FileType.Internal,"icons/awesome128.png");

        Settings.initializeSettings();

        Settings.getPreferences().putBoolean("showFullscreen", false);
        Settings.getPreferences().putInteger("resolutionWidth", 640);
        Settings.getPreferences().putInteger("resolutionHeight", 480);

        Settings.getPreferences().flush();

        new Lwjgl3Application(new AwesomeRoadTrip(), config);
    }
}
