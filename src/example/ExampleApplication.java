package example;

import dsp.ProcessSettings;
import main.MainApplication;
import main.MainWindow;

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
	}

	@Override
	@SuppressWarnings("unused")
	public void draw()
	{
		rect(0.0f, 0.0f, width, height);

		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
	}
	
	final float FRAME_RATE = 60.0f;
	
	ProcessSettings settings = new ProcessSettings(FRAME_RATE);
}
