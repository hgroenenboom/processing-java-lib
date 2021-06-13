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
		
		onePoleParameter = new FloatParameter(id + "-onepole", manager, 0.0f, 0.5f);
		onePoleParameter.addListener(this);
		onePoleParameter.set(1.0f / 5.0f);
		
		rate = new FloatParameter(id + "-rate", manager, 0.0f, 1.0f);
		
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
		final float maxFreq = 10.0f;
		// frequency: 0 - 40
		final float period = maxFreq / ( 0.00000001f + 0.1f * maxFreq * rate.get() );
		
		if(counter > period)
		{
			out = sequence.nextFloat(child.getMin(), child.getMax());
			counter -= period;
		}
		counter += 1.0f;

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
		if(source == onePoleParameter)
		{
			// Update OnePole parameters if the Parameter has changed
			onePole.setLowpass(newValue);
		}
	}

	private OnePole onePole = new OnePole(0.0f);
	public FloatParameter onePoleParameter;
	
	public FloatParameter rate;
	
	final int randomSequenceLength = 3;
	public RandomSequence sequence = new RandomSequence(new Random().nextLong(), randomSequenceLength);

	float out = 0.0f;
	float counter = 0;

	public final FloatParameter child;
}
