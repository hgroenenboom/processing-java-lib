package main;

import processing.core.*;
import dsp.ProcessSettings;
import parameters.ParameterManager;

public abstract class MainApplication extends PApplet
{
	public MainApplication()
	{
	}

	public static void main(String args[])
	{
		PApplet.main(new String[] { "--present", example.ExampleApplication.class.getName() });
	}

	@Override
	public void setup()
	{
		mainComponent = createMainWindow();
		if (mainComponent != null)
		{
			mainComponent.setVisible(true);
		}
	}

	public abstract MainWindow createMainWindow();

	public ParameterManager manager = new ParameterManager(new ProcessSettings(60.0f));

	private MainWindow mainComponent;
}
