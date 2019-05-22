package org.rainbowlabs.awesomeroadtrip.core.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    private Settings() {}

    private static Preferences preferences;
    private static final String[] supportedResolutions = {"640x480", "1024x768", "1280x720", "1920x1080"};


    public static boolean volumeMute;
    public static boolean fullScreen;
    public static float volume;
    public static String resolution;

    private static float BUTTONWIDTH = 125.0f;
    private static float BUTTONHEIGHT = 50.0f;
    private static float DEFAULTPAD = 25.0f;

    public static Preferences getPreferences() {
        return preferences;
    }

    public static void initializeSettings() {
        preferences = Gdx.app.getPreferences("game_settings");
    }

    public static String[] getSupportedResolutions() {
        return supportedResolutions;
    }

    public static void changeResolution(int width, int height, boolean showFullscreen) {
        preferences.putInteger("resolutionWidth", width);
        preferences.putInteger("resolutionHeight", height);
        preferences.putString("resolution", width + "x" + height);
        preferences.putBoolean("showFullscreen", showFullscreen);
        preferences.flush();
    }

    public static float getBUTTONWIDTH() {
        return BUTTONWIDTH;
    }

    public static void setBUTTONWIDTH(float BUTTONWIDTH) {
        Settings.BUTTONWIDTH = BUTTONWIDTH;
    }

    public static float getBUTTONHEIGHT() {
        return BUTTONHEIGHT;
    }

    public static void setBUTTONHEIGHT(float BUTTONHEIGHT) {
        Settings.BUTTONHEIGHT = BUTTONHEIGHT;
    }

    public static float getDEFAULTPAD() {
        return DEFAULTPAD;
    }

    public static void setDEFAULTPAD(float DEFAULTPAD) {
        Settings.DEFAULTPAD = DEFAULTPAD;
    }
}