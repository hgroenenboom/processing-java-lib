package parameters.decorators;

import java.util.Objects;
import java.util.Random;

import data.RandomSequence;
import dsp.OnePole;
import parameters.FloatParameter;
import parameters.IParameter;
import processing.data.JSONObject;

public class RandomSequencedParameter extends IParameter<Float>
{
	public RandomSequencedParameter(FloatParameter parameter)
	{
		super(Objects.requireNonNull(parameter).id + "-randomsequenced", parameter.manager);

		onePole.a1 = -1.0f + 1.0f / 5.0f;
		onePole.b0 = 1.0f - Math.abs(onePole.a1);

		child = parameter;
	}

	@Override
	public void randomize()
	{
	}

	@Override
	public void save(JSONObject json)
	{
	}

	@Override
	public void load(JSONObject json)
	{
	}

	@Override
	public Float get()
	{
		final int rate = 10 + 5 * Math.abs(new Random().nextInt() % 2);

		if (counter++ % rate == 0)
		{
			out = sequence.nextFloat(child.getMin(), child.getMax());
		}

		return onePole.filter(out);
	}

	@Override
	public void set(Float newData)
	{
	}

	public OnePole onePole = new OnePole(0.0f);
	
	final int randomSequenceLength = 3;
	public RandomSequence sequence = new RandomSequence(new Random().nextLong(), randomSequenceLength);

	float out = 0.0f;
	int counter = 0;

	public final FloatParameter child;
}
