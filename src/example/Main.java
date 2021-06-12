package example;

import main.MainApplication;
import parameters.FloatParameter;

public class Main extends MainApplication 
{
	public Main()
	{
		mainComponent = new MainComponent(this);
	}
	
	@Override
	public void settings()
	{
		fullScreen();
	}
	
	@Override
	public void draw()
	{		
		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		
		float _radius = radius.get();
		ellipse(halfWidth, halfHeight, _radius, _radius);
	}
	
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
}
