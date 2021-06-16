package example;

import drawing.Background;
import drawing.Color;
import drawing.ColorMode;
import drawing.ColorSettings;
import dsp.Phasor;
import dsp.ProcessSettings;
import main.MainApplication;
import main.MainWindow;
import parameters.ColorParameter;
import parameters.FloatParameter;
import parameters.IntParameter;
import parameters.decorators.ModulatedParameter;
import parameters.decorators.OnePoleParameter;
import parameters.decorators.RandomSequencedParameter;

public class ExampleApplication extends MainApplication
{
	public ExampleApplication()
	{
		xPosSequence = new RandomSequencedParameter(new FloatParameter("xPos", manager, 0.0f, 1920));
		yPosSequence = new RandomSequencedParameter(new FloatParameter("yPos", manager, 0.0f, 1080));
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
		
		balls = new Ball[NUM_BALLS];
		for(int i = 0; i < NUM_BALLS; i++)
		{
			balls[i] = new Ball(width, height);
		}
		
		colorSettings = new ColorSettings(this, ColorMode.RGB);
		
		createWindow();
	}

	@Override
	public void settings()
	{
		fullScreen();
		
		radiusModulator.frequency(0.5f);
		modulatedRadius.set(0.5f);
	}

	@Override
	public void draw()
	{
		colorSettings.colorMode(ColorMode.RGB);
		
		final Color backgroundColor = color.get();
		Background.draw(this, backgroundColor.r(), backgroundColor.g(), backgroundColor.b(), backgroundColor.alpha(), false);
		
		stroke(0.0f);
		fill(255.0f);
		
		// Unused currently
		radiusModulator.tick();

		radiusSmooth.tick();
		final float _diameter = radiusSmooth.get();
		
		final float _xPos = xPosSequence.get();
		final float _yPos = yPosSequence.get();

		for(Ball ball : balls)
		{
			ball.updateState(_xPos, _yPos, _diameter);
		}
		
		for (int i = 0; i < numBalls.get(); i++)
		{
			balls[i].draw(this);
		}
	}

	// Const finals 
	final float FRAME_RATE = 60.0f;
	final int NUM_BALLS = 250;
	
	// Real-time settings
	ProcessSettings settings = new ProcessSettings(FRAME_RATE);
	ColorSettings colorSettings;
	
	// Drawing state objects
	Ball[] balls;
	
	// Parameters
	public FloatParameter radius = new FloatParameter("radius", manager, 20.0f, 500.0f);
	public Phasor radiusModulator = new Phasor(settings);
	public ModulatedParameter modulatedRadius = new ModulatedParameter(radius, radiusModulator, "-phasor-amount");
	public OnePoleParameter<Float> radiusSmooth = new OnePoleParameter<Float>(settings, radius);

	public RandomSequencedParameter xPosSequence, yPosSequence;

	public IntParameter numBalls = new IntParameter("numBalls", manager, 0, NUM_BALLS - 1);
	
	public ColorParameter color = new ColorParameter("background-color", manager);
}
