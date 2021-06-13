package dsp;

public class MovingAverageFilter
{
	public MovingAverageFilter(int bufferSize, float initval)
	{
		this.SIZE = bufferSize;
		
		memory = new float[bufferSize];

		for (int i = 0; i < SIZE; i++)
		{
			memory[i] = initval;
		}
	}

	public void tick(float input)
	{
		// Substract the first added value from the running sum
		runningSum -= memory[(writePointer - averageSize + SIZE) % SIZE];
		
		// Add the new input value
		memory[writePointer] = input;
		runningSum += input;

		// Move the writepointer
		writePointer++;
		writePointer %= SIZE;
	}

	public float get()
	{
		return rAverageSize * runningSum;
	}
	
	public void setNumSamples(int numSamples)
	{
		assert numSamples > 0 && numSamples < SIZE : "New average size is invalid";
		
		averageSize = numSamples;
		rAverageSize = 1.0f / (float)averageSize;
		
		final float currentValue = memory[writePointer];

		// Initialize running sum
		runningSum = averageSize * currentValue;
		
		// Initialize delay memory
		for(int i = 0; i < SIZE; i++)
		{
			memory[i] = currentValue;
		}
	}

	public float filter(float input)
	{
		tick(input);

		return get();
	}

	final public int SIZE;

	private int writePointer = 0;

	private int averageSize = 0;
	private float rAverageSize = 0.0f;
	private float runningSum = 0.0f;

	private float[] memory;
}
