package example;

import data.RandomSequence;
import dsp.OnePole;
import main.MainApplication;
import main.MainWindow;

import parameters.FloatParameter;
import parameters.decorators.RandomSequencedParameter;

public class ExampleApplication extends MainApplication 
{
	public ExampleApplication()
	{
		xPos = new FloatParameter("xPos", manager, 0.0f, 1920);
		yPos = new FloatParameter("yPos", manager, 0.0f, 1080);
		xPosSequence = new RandomSequencedParameter(xPos);
		yPosSequence = new RandomSequencedParameter(yPos);
	}
	
	@Override
	public MainWindow createMainWindow() 
	{
		return new ExampleComponent(this);
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
		
		final float _diameter = radius.get();
		
		final float randomVariationInPixels = 50.0f * randomVariation.get();
		
		final float _xPos = xPosSequence.get();
		final float _yPos = yPosSequence.get();
		
		for(int i = 0; i < numBalls.get().intValue(); i++)
		{
			float randX = ( 2.0f * (float)Math.random() - 1.0f ) * randomVariationInPixels;
			float randY = ( 2.0f * (float)Math.random() - 1.0f ) * randomVariationInPixels;
			
			ellipse(_xPos + randX, _yPos + randY, _diameter, _diameter);
		}
	}
	
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
	public FloatParameter xPos, yPos;
	public RandomSequencedParameter xPosSequence, yPosSequence;
	public FloatParameter randomVariation = new FloatParameter("randomVariation", manager, 0.0f, 1.0f);
	public FloatParameter numBalls = new FloatParameter("numBalls", manager, 0.0f, 15.0f);
}
