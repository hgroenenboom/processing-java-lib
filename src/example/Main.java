package example;

import main.MainApplication;
import parameters.FloatParameter;

public class Main extends MainApplication 
{
	public Main()
	{
		xPos = new FloatParameter("xPos", manager, 0.0f, 1920);
		yPos = new FloatParameter("yPos", manager, 0.0f, 1080);
		
		mainComponent = new MainComponent(this);
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
		
		float _radius = radius.get();
		ellipse(xPos.get(), yPos.get(), _radius, _radius);
	}
	
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
	public FloatParameter xPos;
	public FloatParameter yPos;
}
