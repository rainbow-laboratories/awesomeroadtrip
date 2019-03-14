package org.rainbowlabs.awesomeroadtrip.java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

public class AwesomeRoadTripDesktop {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = true;
		config.resizable = true;
		new LwjglApplication(new AwesomeRoadTrip(), config);
	}
}
