package main;

import processing.core.*;

import parameters.ParameterManager;

public abstract class MainApplication extends PApplet
{
	public MainApplication()
	{
	}
	
	public static void main(String args[]) 
	{
		PApplet.main(new String[] {"--present", "example.ExampleApplication"});
	}
	
	@Override
	public void setup()
	{
		assert mainComponent != null : "You have to specify explicitly create a MainWindow object in your constructor";
		mainComponent.setVisible(true);
	}
	
	public ParameterManager manager = new ParameterManager();
	
	protected MainWindow mainComponent;
}
