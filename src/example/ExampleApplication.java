package example;

import main.MainApplication;
import parameters.FloatParameter;

public class ExampleApplication extends MainApplication 
{
	public ExampleApplication()
	{
		xPos = new FloatParameter("xPos", manager, 0.0f, 1920);
		yPos = new FloatParameter("yPos", manager, 0.0f, 1080);
		
		mainComponent = new ExampleComponent(this);
	}
	
	@Override
	public void settings()
	{
		fullScreen();
	}
	
	@Override
	@SuppressWarnings("unused")
	public void draw()
	{		
		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		
		final float _radius = radius.get();
		
		final float randomVariationInPixels = 50.0f * randomVariation.get();
		
		for(int i = 0; i < (int)numBalls.get(); i++)
		{
			float randX = ( 2.0f * (float)Math.random() - 1.0f ) * randomVariationInPixels;
			float randY = ( 2.0f * (float)Math.random() - 1.0f ) * randomVariationInPixels;
			
			ellipse(xPos.get() + randX, yPos.get() + randY, _radius, _radius);
		}
	}
	
	public int counter = 0;
	
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
	public FloatParameter xPos, yPos;
	public FloatParameter randomVariation = new FloatParameter("randomVariation", manager, 0.0f, 1.0f);
	public FloatParameter numBalls = new FloatParameter("numBalls", manager, 0.0f, 15.0f);
}
