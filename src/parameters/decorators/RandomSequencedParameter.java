package parameters.decorators;

import java.util.Objects;
import data.RandomSequence;
import dsp.OnePole;
import parameters.FloatParameter;
import parameters.IntParameter;
import parameters.Parameter;
import parameters.ParameterListener;
import processing.data.JSONObject;

@SuppressWarnings("rawtypes")
public class RandomSequencedParameter extends Parameter<Float> implements ParameterListener
{
	@SuppressWarnings("unchecked")
	public RandomSequencedParameter(FloatParameter parameter)
	{
		super(Objects.requireNonNull(parameter).id + "-randomsequenced", parameter.manager);
		
		onePoleParameter = new FloatParameter("smoothed-onepole-frequency", 0.0f, 0.5f);
		onePoleParameter.addListener(this);
		onePoleParameter.set(1.0f / 5.0f);
		
		seed = new IntParameter("seed", 0, 200);
		seed.addListener(this);
		seed.set(0);
		
		sequenceLength = new FloatParameter("length-normalized", 0.0f, 1.0f);
		sequenceLength.addListener(this);
		sequenceLength.set(0.5f);
		
		rate = new FloatParameter("rate-normalized", 0.0f, 1.0f);
		
		child = parameter;
	}

	@Override
	public void randomize()
	{
		onePoleParameter.randomize();
		seed.randomize();
		sequenceLength.randomize();
		rate.randomize();
	}

	@Override
	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();
		
		onePoleParameter.save(child);
		seed.save(child);
		sequenceLength.save(child);
		rate.save(child);
		
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
		
		onePoleParameter.load(child);
		seed.load(child);
		sequenceLength.load(child);
		rate.load(child);
	}

	@Override
	public Float get()
	{
		final float maxFreq = 10.0f;
		// frequency: 0 - 40
		final float period = maxFreq / ( 0.00000001f + 0.1f * maxFreq * rate.get() );
		
		if(counter > period)
		{
			out = sequence.nextFloat(child.min(), child.max());
			counter -= period;
		}
		counter += 1.0f;

		return onePole.filter(out);
	}

	@Override
	public void set(Float newData)
	{
		// Not sure what we want here
	}
	
	@Override
	public void onValueChanged(Parameter source, Object newValue)
	{
		if(source == onePoleParameter)
		{
			onePole.setLowpass(onePoleParameter.get());
		}
		else if(source == sequenceLength)
		{
			sequence.size( (int)(1.0f + sequenceLength.get() * ( maxLength - 1.00001f ) ) );
		}
		else if(source == seed)
		{
			final int activeSize = sequence != null ? sequence.size() : maxLength;
			sequence = new RandomSequence(seed.get(), maxLength);
			sequence.size(activeSize);
		}
	}
	
	// Constants settings
	public final int maxLength = 12;
	
	// Const reference to controlled parameter
	public final FloatParameter child;

	// Parmeters
	public FloatParameter onePoleParameter;
	public FloatParameter rate;
	public FloatParameter sequenceLength;
	public IntParameter seed;
	
	// The random sequence which is looped
	private RandomSequence sequence; 

	// State
	private float out = 0.0f;
	private float counter = 0;

	// Output value onepole filter
	private OnePole onePole = new OnePole(0.0f);
}
