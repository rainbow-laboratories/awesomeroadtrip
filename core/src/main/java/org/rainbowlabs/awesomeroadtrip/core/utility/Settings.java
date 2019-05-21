package org.rainbowlabs.awesomeroadtrip.core.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    private Settings() {}

    private static Preferences preferences;
    public static boolean volumeMute;
    public static boolean fullScreen;
    public static float volume;
    public static String resolution;

    public static float BUTTONWIDTH = 125.0f;
    public static float BUTTONHEIGHT = 50.0f;
    public static float DEFAULTPAD = 25.0f;

    public static Preferences getPreferences() {
        return preferences;
    }

    public static void initializeSettings() {
        preferences = Gdx.app.getPreferences("Game Settings");
    }

    public static void changeResolution(int width, int height) {
        preferences.putInteger("resolutionWidth", width);
        preferences.putInteger("resolutionHeight", height);
        preferences.flush();
    }

    public static void changeResolution(int width, int height, boolean showFullscreen) {
        preferences.putInteger("resolutionWidth", width);
        preferences.putInteger("resolutionHeight", height);
        preferences.putBoolean("showFullscreen", showFullscreen);
        preferences.flush();
    }
}
