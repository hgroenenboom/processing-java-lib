package example;

import data.RandomSequence;
import dsp.OnePole;
import main.MainApplication;
import main.MainWindow;

import parameters.FloatParameter;
import parameters.decorators.OnePoleParameter;

public class ExampleApplication extends MainApplication 
{
	public ExampleApplication()
	{
		xPos = new FloatParameter("xPos", manager, 0.0f, 1920);
		yPos = new FloatParameter("yPos", manager, 0.0f, 1080);
		xPosSmooth = new OnePoleParameter<Float>(xPos);
		yPosSmooth = new OnePoleParameter<Float>(yPos);
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
		
		onePole.a1 = - 0.9f;
		onePole.b0 = 0.1f;
	}
	
	@Override
	@SuppressWarnings("unused")
	public void draw()
	{		
		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		
		if(counter % 20 == 0) { r = sequenceOne.nextFloat(-1.0f,  1.0f); }
		final float filtered = 0.0f; //onePole.filter(r);
		
		final float _diameter = radius.get();
		
		final float randomVariationInPixels = 50.0f * randomVariation.get();
		
		final float _xPos = xPosSmooth.get();
		final float _yPos = yPosSmooth.get();
		
		for(int i = 0; i < numBalls.get().intValue(); i++)
		{
			float randX = ( 2.0f * (float)Math.random() - 1.0f ) * randomVariationInPixels;
			float randY = ( 2.0f * (float)Math.random() - 1.0f ) * randomVariationInPixels;
			
			ellipse(_xPos + randX + filtered * 1000.0f, _yPos + randY, _diameter, _diameter);
		}
		
		counter++;
	}
	
	RandomSequence sequenceOne = new RandomSequence(0, 5);
	OnePole onePole = new OnePole(0.0f);
	float r;
	
	public int counter = 0;
	
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
	public FloatParameter xPos, yPos;
	public OnePoleParameter<Float> xPosSmooth, yPosSmooth;
	public FloatParameter randomVariation = new FloatParameter("randomVariation", manager, 0.0f, 1.0f);
	public FloatParameter numBalls = new FloatParameter("numBalls", manager, 0.0f, 15.0f);
}
