package example;

import java.util.ArrayList;
import java.util.Objects;

import dsp.ProcessSettings;
import processing.core.PApplet;

public class RainDrops
{
	public final int NUM_DROPS = 500;
	
	public RainDrops(ProcessSettings settings)
	{
		for(int i = 0; i < NUM_DROPS; i++)
		{
			rainDrops.add( new RainDrop(Objects.requireNonNull(settings)) );
			rainDrops.get(i).setPosition(-3.0f + 7.0f * (float)Math.random(), -10.0f + 11.5f * (float)Math.random());
		}
	}
	
	public void draw(PApplet context)
	{
		for(RainDrop rainDrop : rainDrops)
		{
			rainDrop.draw(context);
		}
	}
	
	public void updateParameters( float radius, float speedInHz, float triggerHz, float distance, float tilt )
	{
		for(RainDrop rainDrop : rainDrops)
		{
			rainDrop.setParameters(radius, speedInHz, triggerHz, distance, tilt);
		}
	}
	
    private ArrayList<RainDrop> rainDrops = new ArrayList<RainDrop>();
}
