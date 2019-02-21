package de.rainbowlabs.core;


import com.b3dgs.lionengine.Config;
import com.b3dgs.lionengine.Version;
import com.b3dgs.lionengine.awt.graphic.EngineAwt;
import com.b3dgs.lionengine.graphic.engine.Loader;
import de.rainbowlabs.sequences.StartSequence;

public class AwesomeRoadTripPc {
    public static void main(String[] args) {
        EngineAwt.start("AwesomeRoadTrip", Version.create(0, 0, 1), AwesomeRoadTripPc.class);
        Loader.start(Config.windowed(StartSequence.NATIVE.get2x()), StartSequence.class);
    }
}
