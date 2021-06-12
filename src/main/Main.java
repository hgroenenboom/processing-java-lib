package main;

import processing.core.*;

import parameters.ParameterManager;
import parameters.FloatParameter;

public class Main extends PApplet 
{
	public static void main(String args[]) 
	{
		PApplet.main(new String[] {"--present", "main.Main"});
	}
	
	public void setup()
	{		
		mainComponent.setVisible(true);
	}
	
	public void settings()
	{
		fullScreen();
	}
	
	public void draw()
	{		
		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		
		float _radius = radius.get();
		ellipse(halfWidth, halfHeight, _radius, _radius);
	}
	
	public ParameterManager manager = new ParameterManager();
	
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
	
	private MainComponent mainComponent = new MainComponent(this);
}
