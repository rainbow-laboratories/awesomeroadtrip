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
        if (preferences.get().isEmpty()) {
            preferences.putInteger("resolutionWidth", 1920);
            preferences.putInteger("resolutionHeight", 1080);
            preferences.putString("resolution", 1920 + "x" + 1080);
            preferences.putBoolean("showFullscreen", false);
            preferences.putBoolean("soundOn", false);
            preferences.flush();
        }
    }

    public static String[] getSupportedResolutions() {
        return supportedResolutions;
    }

    public static void changeResolution(int width, int height, boolean showFullscreen) {
        preferences.putInteger("resolutionWidth", width);
        preferences.putInteger("resolutionHeight", height);
        preferences.putString("resolution", width + "x" + height);
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

    public static int getResolutionWidth() {
        return Settings.preferences.getInteger("resolutionWidth");
    }

    public static int getResolutionHeight() {
        return Settings.preferences.getInteger("resolutionHeight");
    }
}