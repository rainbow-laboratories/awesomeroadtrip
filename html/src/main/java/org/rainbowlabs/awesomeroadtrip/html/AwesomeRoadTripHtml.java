package org.rainbowlabs.awesomeroadtrip.html;

import org.rainbowlabs.awesomeroadtrip.core.AwesomeRoadTrip;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class AwesomeRoadTripHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new AwesomeRoadTrip();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
