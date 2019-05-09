package org.rainbowlabs.awesomeroadtrip.core.utility;

import com.badlogic.gdx.Preferences;

public class settings {

    public Preferences preferences;
    public boolean volumeMute;
    public boolean fullScreen;
    public float volume;
    public String resolution;

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public boolean isVolumeMute() {
        return volumeMute;
    }

    public void setVolumeMute(boolean volumeMute) {
        this.volumeMute = volumeMute;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

}
