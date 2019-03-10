package org.rainbowlabs.awesomeroadtrip.android;

import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AwesomeRoadTripActivity extends AndroidApplication {

	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
			initialize(new AwesomeRoadTrip(), config);
	}
}
