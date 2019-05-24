package org.rainbowlabs.awesomeroadtrip.java;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;
import org.rainbowlabs.awesomeroadtrip.core.utility.Settings;

import java.util.Set;

public class AwesomeRoadTripDesktop {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Awesome Math Roadtrip");
        config.useVsync(true);
        config.setResizable(false);
        config.setWindowIcon( Files.FileType.Internal,"icons/awesome128.png");
        new Lwjgl3Application(new AwesomeRoadTrip(), config);
    }
}
