package parameters.decorators;

import java.util.Objects;

import dsp.Modulator;
import parameters.NumericParameter;
import parameters.Parameter;
import processing.data.JSONObject;

// TODO: make this valid for Int parameters as well
public class ModulatedParameter extends Parameter<Float>
{
	public ModulatedParameter(NumericParameter<Float> parameter, Modulator<Float> modulator)
	{
		super(Objects.requireNonNull(parameter).id + "-modulation", parameter.manager);
		
		this.modulator = Objects.requireNonNull(modulator);
		modulatedParameter = parameter;
	}

	@Override
	public void randomize()
	{
		amount = -1.0f + 2.0f * (float) Math.random();
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
		// parameterValue + amount * modulation * parameterRange
		return modulatedParameter.get().floatValue() + amount * modulator.get().floatValue() * (float)modulatedParameter.range();
	}

	@Override
	public void set(Float newData)
	{
		amount = newData;
	}
	
	private float amount = 0.0f;
	
	private Modulator<Float> modulator;
	private NumericParameter<Float> modulatedParameter;
}
