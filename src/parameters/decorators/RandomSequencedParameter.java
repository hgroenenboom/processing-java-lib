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

		onePole = new OnePoleParameter(this.id, manager);
		
		onePole.set(-1.0f + 1.0f / 5.0f);
		
		child = parameter;
	}

	@Override
	public void randomize()
	{
	}

	@Override
	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();
		
		json.setJSONObject(id, child);
	}

	@Override
	public void load(JSONObject json)
	{
		JSONObject child = json.getJSONObject(id);
		if(child == null)
		{
			return;
		}
	}

	@Override
	public Float get()
	{
		final int rate = 10 + 5 * Math.abs(new Random().nextInt() % 2);

		if (counter++ % rate == 0)
		{
			out = sequence.nextFloat(child.getMin(), child.getMax());
		}

		return onePole.onePole.filter(out);
	}

	@Override
	public void set(Float newData)
	{
	}

	public OnePoleParameter onePole;
	
	final int randomSequenceLength = 3;
	public RandomSequence sequence = new RandomSequence(new Random().nextLong(), randomSequenceLength);

	float out = 0.0f;
	int counter = 0;

	public final FloatParameter child;
}
