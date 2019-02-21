package de.rainbowlabs.sequences;

import com.b3dgs.lionengine.Context;
import com.b3dgs.lionengine.Engine;
import com.b3dgs.lionengine.Resolution;
import com.b3dgs.lionengine.Verbose;
import com.b3dgs.lionengine.graphic.Graphic;
import com.b3dgs.lionengine.graphic.engine.Sequence;

public class StartSequence extends Sequence
{
    public static final Resolution NATIVE = new Resolution(800, 600, 60);

    private int count;

    public StartSequence(Context context)
    {
        super(context, NATIVE);
    }

    @Override
    public void load()
    {
        count = 0;
    }

    @Override
    public void update(double extrp)
    {

    }

    @Override
    public void render(Graphic g)
    {
        Verbose.info("SequenceFirst rendering number " + count);
    }

    @Override
    public void onTerminated(boolean hasNextSequence)
    {
        if (!hasNextSequence)
        {
            Engine.terminate();
        }
    }
}