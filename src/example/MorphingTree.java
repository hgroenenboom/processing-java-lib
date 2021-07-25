package example;

import data.RandomSequence;
import dsp.Vector;
import processing.core.PApplet;

public class MorphingTree
{
	AnimatedTree host;

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
	MorphingTree(int x, int y, float speedInPixels, int numLines, float crossFade, int randomSize, AnimatedTree host)
	{
		this.host = host;
		
		// Init variables
		this.numLines = numLines;
		this.speed = speedInPixels;
		this.crossFade = crossFade;

		// Create two random lists with custom seeds
		this.randomArraySize = randomSize;
		randomLists = new RandomSequence[2];
		randomLists[0] = new RandomSequence(0, randomArraySize);
		randomLists[1] = new RandomSequence(1, randomArraySize);

		// Initialize data structures
		directions = new Vector[numLines];
		positions = new Vector[numLines];
		trees = new MorphingTree[numLines];

		for (int i = 0; i < numLines; i++)
		{
			// Create random directions
			directions[i] = new Vector(-randomOfTwo(0.0f, 2.0f * (float) Math.PI, crossFade));

			// Set inital line positions on centre point
			positions[i] = new Vector(x, y);
		}
	}

	void draw(PApplet context)
	{
		// 1. Low speed disabled drawing
		if ( speed <= host.minSpeed.get() || branches >= host.BRANCH_MAXIMUM_COUNT )
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
			context.strokeWeight((float) Math.pow(host.widthMultiplier.get() * speed, host.widthExpansion.get()));
			context.line(oldX, oldY, positions[i].getX(), positions[i].getY());

			// iv. Add variation to the target angle of the branch
			// TODODODODODODODODODODODO
			directions[i]
					.changeAngle(directions[i].getAngle() + randomOfTwo(-host.branchMovement.get(), host.branchMovement.get(), crossFade));

			// v. Let chance decide whether whether this branch branches into new branches
			if ( randomOfTwo(0.0f, 1.0f, crossFade) < host.branchingChance.get() )
			{
				final int newNumLines = Math.max(numLines - host.branchCountReduction.get(), 0);

				// Create a new branch (Tree)
				trees[i] = new MorphingTree((int) positions[i].getX(), (int) positions[i].getY(), speed, newNumLines,
						crossFade, randomArraySize, host);
				trees[i].topLevelTree = topLevelTree;
				topLevelTree.branches += newNumLines;

				// Generate new angle for each branch
				for (int j = 0; j < newNumLines; j++)
					trees[i].directions[j].changeAngle(directions[i].getAngle()
							+ randomOfTwo(-host.branchAngleOffset.get(), host.branchAngleOffset.get(), crossFade));
			}
		}

		// Adjust speed (usually decrease, otherwise the branch will forever keep
		// drawing, and a branch should eventually stop growing is the idea)
		speed += randomOfTwo(host.speedIncrement.get() - host.speedRandomDeviation.get(), host.speedIncrement.get() + host.speedRandomDeviation.get(), crossFade);
		speed *= host.speedMultiplier.get();
	}

	float randomOfTwo(float min, float max, float crossfade)
	{
		return (1.0f - crossfade) * randomLists[0].nextFloat(min, max) + crossfade * randomLists[1].nextFloat(min, max);
	}

	// The speed in pixels at which this tree is drawn
	private float speed;

	private MorphingTree topLevelTree = this;
	private int branches = 0;

	// Amount of branches to draw
	private final int numLines;

	/**
	 * Crossfade amount between the two randoms, can be used in combination with a
	 * random seed to create similar copies of a single tree
	 */
	RandomSequence[] randomLists;
	public final int randomArraySize;
	private final float crossFade;

	// Directions (angles) and line positions for this tree
	private Vector[] directions;
	private Vector[] positions;

	// Sub trees for this tree segment
	private MorphingTree[] trees;
}