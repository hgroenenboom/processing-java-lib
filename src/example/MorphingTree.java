package example;

import dsp.Vector;
import processing.core.PApplet;

public class MorphingTree
{
	ExampleApplication parameters;

	/**
	 * A Tree origin of which branches are drawn. A Tree can contain multiple Tree's
	 * which are essentially subbranches. The draw call recursively calls all the
	 * subtrees to also draw when necessary.
	 * 
	 * @param x             The x origin position of the Tree
	 * @param y             The y origin position of the Tree
	 * @param speedInPixels The starting drawing speed (in pixesl) of the Tree
	 * @param numLines      The amount of lines/branches to spawn of the origin
	 *                      point
	 */
	MorphingTree(int x, int y, float speedInPixels, int numLines, ExampleApplication parameters)
	{
		this.parameters = parameters;
		
		// Init variables
		this.numLines = numLines;
		this.speed = speedInPixels;

		// Initialize data structures
		directions = new Vector[numLines];
		positions = new Vector[numLines];
		trees = new MorphingTree[numLines];

		for (int i = 0; i < numLines; i++)
		{
		      // Create random directions
		      directions[i] = new Vector(- 2.0f * (float)Math.PI * (float)Math.random());
		      
			// Set inital line positions on centre point
			positions[i] = new Vector(x, y);
		}
	}

	void draw(PApplet context)
	{
		// 1. Low speed disabled drawing
		if ( speed <= parameters.minSpeed.get() || branches >= parameters.BRANCH_MAXIMUM_COUNT )
			return;

		// 2. Draw the branches if they exist (recursively)
		for (int i = 0; i < numLines; i++)
			if ( trees[i] != null )
				trees[i].draw(context);

		// 3. main draw loop (through branches)
		for (int i = 0; i < numLines; i++)
		{
			if ( trees[i] != null )
				continue;

			// i. Get current position of branch
			final float oldX = positions[i].getX();
			final float oldY = positions[i].getY();

			// ii. Move current position along the current direction
			positions[i].moveTo(directions[i].getAngle(), speed);

			// iii. Draw line between old and new position
			context.strokeWeight((float) Math.pow(parameters.widthMultiplier.get() * speed, parameters.widthExpansion.get()));
			context.line(oldX, oldY, positions[i].getX(), positions[i].getY());

			// iv. Add variation to the target angle of the branch
			directions[i]
					.changeAngle(directions[i].getAngle() + parameters.branchMovement.get() * (-1.0f + 2.0f * (float)Math.random()) );

			// v. Let chance decide whether whether this branch branches into new branches
			if ( Math.random() < parameters.branchingChance.get() )
			{
				final int newNumLines = Math.max(numLines - parameters.branchCountReduction.get(), 0);

				// Create a new branch (Tree)
				trees[i] = new MorphingTree((int) positions[i].getX(), (int) positions[i].getY(), speed, newNumLines, parameters);
				trees[i].topLevelTree = topLevelTree;
				topLevelTree.branches += newNumLines;

				// Generate new angle for each branch
				for (int j = 0; j < newNumLines; j++)
					trees[i].directions[j].changeAngle(directions[i].getAngle()
							+ parameters.branchMovement.get() * (-1.0f + 2.0f * (float)Math.random()) );
			}
		}

		// Adjust speed (usually decrease, otherwise the branch will forever keep
		// drawing, and a branch should eventually stop growing is the idea)
		speed += ( parameters.speedIncrement.get() + parameters.speedRandomDeviation.get() ) * context.random(-1.0f,  1.0f);
		speed *= parameters.speedMultiplier.get();
	}

	// The speed in pixels at which this tree is drawn
	private float speed;

	private MorphingTree topLevelTree = this;
	private int branches = 0;

	// Amount of branches to draw
	private final int numLines;

	// Directions (angles) and line positions for this tree
	private Vector[] directions;
	private Vector[] positions;

	// Sub trees for this tree segment
	private MorphingTree[] trees;
}