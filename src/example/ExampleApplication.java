package example;

import java.util.ArrayList;

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
		
		branchAngleOffset = new FloatParameter("Branch angle offset", manager, 0.0f, 2.0f);
		branchingChance = new FloatParameter("Branching chance", manager, 0.0f, 1.0f);
		
		branchMovement = new FloatParameter("Branch movement", manager, 0.0f, 1.0f);
		widthExpansion = new FloatParameter("Width expansion", manager, 0.5f, 1.5f);
		widthMultiplier = new FloatParameter("Width multiplier", manager, 0.0f, 2.0f);

		branchCount = new IntParameter("Branch count", manager, 0, 10);
		branchCountReduction = new IntParameter("Branch count reduction", manager, 0, 3);
		randomListSeed = new IntParameter("Random sequence seed", manager, 0, 200);
		
		initSpeed = new FloatParameter("Initial speed", manager, 0.0f, 10.0f);
		minSpeed = new FloatParameter("Minimum speed", manager, 0.0f, 0.2f);
		speedMultiplier = new FloatParameter("Speed multiplier", manager, 0.0f, 2.0f);
		speedIncrement = new FloatParameter("Speed increment", manager, -0.5f, 0.5f);
		speedRandomDeviation = new FloatParameter("Speed random deviation", manager, 0.0f, 0.2f);
		
		//tree = new AnimatedTree(10, manager);
	}

	@Override
	@SuppressWarnings("unused")
	public void draw()
	{
		strokeWeight(0);
		fill(255.0f, alpha.get());
		rect(0.0f, 0.0f, width, height);
		
		final float halfWidth = width / 2.0f;
		final float halfHeight = height / 2.0f;
		 
	  if (mousePressed == true) 
	    trees.add(new MorphingTree(mouseX, mouseY, initSpeed.get(), branchCount.get(), this));
	  
	  for(MorphingTree tree : trees)
	    tree.draw(this);
	}

	final float FRAME_RATE = 60.0f;
	final int BRANCH_MAXIMUM_COUNT = 5000;

	ProcessSettings settings = new ProcessSettings(FRAME_RATE);
	
	ArrayList<MorphingTree> trees = new ArrayList<MorphingTree>();
	
	public FloatParameter branchAngleOffset, branchingChance;
	public FloatParameter branchMovement, widthExpansion, widthMultiplier;
	public IntParameter branchCount, randomListSeed, branchCountReduction;
	public FloatParameter initSpeed, minSpeed, speedMultiplier, speedIncrement, speedRandomDeviation;
		
	FloatParameter alpha = new FloatParameter("Alpha", manager, 0.0f, 255.0f);
	

}
