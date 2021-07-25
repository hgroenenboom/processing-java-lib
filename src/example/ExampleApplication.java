package example;

import dsp.OnePole;
import dsp.ProcessSettings;
import main.MainApplication;
import main.MainWindow;
import parameters.FloatParameter;
import parameters.decorators.OnePoleParameter;

public class ExampleApplication extends MainApplication
{
	public ExampleApplication()
	{
	}

	@Override
	public MainWindow createMainWindow()
	{
		return new ExampleComponent(this);
	}
	
	@Override
	public void setup()
	{
		frameRate(FRAME_RATE);
		
		createWindow();
	}

	@Override
	public void settings()
	{
		fullScreen();
		
		radiusSmooth.set(0.01f);
		distanceSmooth.set(0.01f);
		tiltSmooth.set(0.01f);
		speedFilter.setLowpass(0.0004f);
	}

	@Override
	@SuppressWarnings("unused")
	public void draw()
	{
		strokeWeight(3.0f);

		fill(0.0f, 100.0f, 150.0f, 120.0f);
		rect(-5.0f, -5.0f, width + 10.0f, height + 10.0f);

		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		
		radiusSmooth.tick();
		distanceSmooth.tick();
		tiltSmooth.tick();
		
		rainDrops.updateParameters(
				radiusSmooth.get(), 
				(float)Math.pow(speedFilter.filter(speed.get()), 2.0f), 
				(float)Math.pow(change.get(), 2.0f), 
				distanceSmooth.get(),
				tiltSmooth.get()
		);
		
		rainDrops.draw(this);
	}
	
	final float FRAME_RATE = 60.0f;
	
	ProcessSettings settings = new ProcessSettings(FRAME_RATE);
	
	RainDrops rainDrops = new RainDrops(settings);
	
	FloatParameter speed = new FloatParameter("droplet-speed", manager, 0.0f, 2.0f);
	FloatParameter change = new FloatParameter("droplet-chance", manager, 0.0f, 2.0f);
	
	FloatParameter radius = new FloatParameter("droplet-radius", manager, 0.0f, 1.0f);
	public OnePoleParameter<Float> radiusSmooth = new OnePoleParameter<Float>(settings, radius);
	
	FloatParameter dist = new FloatParameter("dist", manager, 0.0f, 1.0f);
	public OnePoleParameter<Float> distanceSmooth = new OnePoleParameter<Float>(settings, dist);
	
	OnePole speedFilter = new OnePole(0.0f);
	
	FloatParameter tilt = new FloatParameter("tilt", manager, 1.0f, 4.0f);
	public OnePoleParameter<Float> tiltSmooth = new OnePoleParameter<Float>(settings, tilt);
}
