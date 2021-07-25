package example;

import dsp.ProcessSettings;
import main.MainApplication;
import main.MainWindow;
import parameters.FloatParameter;
import parameters.IntParameter;

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
		noCursor();

		createWindow();
	}

	@Override
	public void settings()
	{
		fullScreen();

		tree = new AnimatedTree(10, manager);
	}

	@Override
	@SuppressWarnings("unused")
	public void draw()
	{
		fill(255.0f, alpha.get());
		rect(0.0f, 0.0f, width, height);

		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		
		tree.draw(this);
	}

	final float FRAME_RATE = 60.0f;

	ProcessSettings settings = new ProcessSettings(FRAME_RATE);

	AnimatedTree tree;
	
	FloatParameter alpha = new FloatParameter("Alpha", 0.0f, 255.0f);
}
