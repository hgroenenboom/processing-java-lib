package example;

import java.util.ArrayList;

import dsp.Delay;
import processing.core.PApplet;

public class Ball
{
	Ball(final int width, final int height)
	{
		for(Delay<Float> delay : delays)
		{
			delay.setSize(101);
			delay.setDelay((int)(100.0f * Math.random()));
			
			for(int i = 0; i < delay.size(); i++)
			{
				delay.push(0.0f);
			}
		}		
	}
	
	public void updateState(float newX, float newY, float newDiameter)
	{
		xPosition.push(newX);
		yPosition.push(newY);
		diameter.push(newDiameter);
	}
	
	public void draw(PApplet context)
	{
		final float _xPos = xPosition.get();
		final float _yPos = yPosition.get();
		final float _diameter = diameter.get();

		context.ellipse(_xPos, _yPos, _diameter, _diameter);
	}
	
	private Delay<Float> xPosition = new Delay<Float>();
	private Delay<Float> yPosition = new Delay<Float>();
	private Delay<Float> diameter = new Delay<Float>();
	
	@SuppressWarnings("serial")
	ArrayList<Delay<Float>> delays = new ArrayList<Delay<Float>>( ) {{ add(xPosition); add(yPosition); add(diameter); }};
}
