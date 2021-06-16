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
		// TODO: look for a way so that the name of the implementer class does not have to be hardcoded here
		PApplet.main(new String[] { "--present", example.ExampleApplication.class.getName() });
	}

	public void createWindow()
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
