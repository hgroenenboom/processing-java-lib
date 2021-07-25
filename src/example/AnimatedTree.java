package example;

import java.util.ArrayList;

import dsp.Vector;
import parameters.FloatParameter;
import parameters.IntParameter;
import parameters.ParameterManager;
import processing.core.PApplet;

public class AnimatedTree
{
	// Settings
	public final int randomCount;

	// Mouse/UX state
	private boolean addingTree = false;
	private Vector mousePos = new Vector(0, 0);

	// Algorithm state
	private int counter = 0;
	private int offset = 0;

	ArrayList<MorphingTree> trees = new ArrayList<MorphingTree>();
	
	public FloatParameter branchAngleOffset, branchingChance;
	public FloatParameter branchMovement, widthExpansion, widthMultiplier;
	public IntParameter branchCount, randomListSeed, branchCountReduction;
	public FloatParameter initSpeed, minSpeed, speedMultiplier, speedIncrement, speedRandomDeviation;
	
	public IntParameter numTrees, drift; 

	final int BRANCH_MAXIMUM_COUNT = 5000;

	public AnimatedTree(int randomArraySize, ParameterManager manager)
	{
		this.randomCount = randomArraySize;
		
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
		
		numTrees = new IntParameter("Number of trees", 1, 100);
		drift = new IntParameter("Animation drift", 0, 100);
	}

	public void draw(PApplet context)
	{
		final int NUM_TREES = numTrees.get();
		
		// Flag tree to be added if mouse if pressed (once)
		if ( !addingTree )
		{
			addingTree = context.mousePressed;
			mousePos.setPosition(context.mouseX, context.mouseY);

			// Reset state
			counter = 0;
			offset = 0;
		}

		if ( addingTree )
		{
			// Calculate position offset and random seed to pass to new tree object
			offset += drift.get();
			// offset += drift - counter;
			final float randomSeed = 1.0f / NUM_TREES * (float) counter;

			// Add new tree object
			trees.add(new MorphingTree((int) mousePos.getX() + offset, (int) mousePos.getY() + offset, initSpeed.get(),
					branchCount.get(), randomSeed, randomCount, this));

			// Increment counter and disable adding of trees if given amount of trees is
			// reached
			counter++;
			if ( counter == NUM_TREES )
				addingTree = false;
		}

		for (MorphingTree tree : trees)
			tree.draw(context);
	}
};
