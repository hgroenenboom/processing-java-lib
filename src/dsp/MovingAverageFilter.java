package dsp;

public class MovingAverageFilter
{
	public MovingAverageFilter(int numvalues, float initval)
	{
		this.SIZE = numvalues;
		this.R_SIZE = 1.0f / SIZE;

		memory = new float[numvalues];

		for (int i = 0; i < SIZE; i++)
		{
			memory[i] = initval;
			runningSum += memory[i];
		}
	}

	public void tick(float input)
	{
		runningSum -= memory[writePointer];
		memory[writePointer] = input;

		writePointer++;
		writePointer %= SIZE;

		runningSum += memory[writePointer];
	}

	public float get()
	{
		return R_SIZE * runningSum;
	}

	public float filter(float input)
	{
		tick(input);

		return get();
	}

	final public int SIZE;
	final public float R_SIZE;

	private int writePointer = 0;

	private float runningSum = 0.0f;

	private float[] memory;
}
