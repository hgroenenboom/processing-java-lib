package parameters.decorators;

import java.util.Objects;
import java.util.Random;

import data.RandomSequence;
import dsp.OnePole;
import parameters.FloatParameter;
import parameters.Parameter;
import parameters.ParameterListener;
import processing.data.JSONObject;

public class RandomSequencedParameter extends Parameter<Float> implements ParameterListener<Float>
{
	public RandomSequencedParameter(FloatParameter parameter)
	{
		super(Objects.requireNonNull(parameter).id + "-randomsequenced", parameter.manager);
		
		onePoleParameter = new FloatParameter(id + "-onepole", manager, -0.999999999f, 0.999999999999f);
		onePoleParameter.addListener(this);
		onePoleParameter.set(-1.0f + 1.0f / 5.0f);
		
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

		return onePole.filter(out);
	}

	@Override
	public void set(Float newData)
	{
		// Not sure what we want here
		// out = newData;
	}
	
	@Override
	public void onValueChanged(Parameter<Float> source, Float newValue)
	{
		// Update OnePole parameters if the Parameter has changed
		onePole.a1 = newValue;
		onePole.b0 = 1.0f - Math.abs(newValue);
	}

	private OnePole onePole = new OnePole(0.0f);
	public FloatParameter onePoleParameter;
	
	final int randomSequenceLength = 3;
	public RandomSequence sequence = new RandomSequence(new Random().nextLong(), randomSequenceLength);

	float out = 0.0f;
	int counter = 0;

	public final FloatParameter child;
}
