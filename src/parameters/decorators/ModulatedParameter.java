package parameters.decorators;

import java.util.Objects;

import dsp.Modulator;
import parameters.FloatParameter;
import parameters.NumericParameter;

// TODO: make this valid for Int parameters as well
public class ModulatedParameter extends FloatParameter
{
	public ModulatedParameter(NumericParameter<Float> parameter, Modulator<Float> modulator, float min, float max)
	{
		super(Objects.requireNonNull(parameter).id + "-modulation", parameter.manager, min, max);
		
		this.modulator = Objects.requireNonNull(modulator);
		modulatedParameter = parameter;
	}
	
	public ModulatedParameter(NumericParameter<Float> parameter, Modulator<Float> modulator)
	{
		super(Objects.requireNonNull(parameter).id + "-modulation", parameter.manager, -1.0f, 1.0f);
		
		this.modulator = Objects.requireNonNull(modulator);
		modulatedParameter = parameter;
	}

	@Override
	public Float get()
	{
		// parameterValue + amount * modulation * parameterRange
		return modulatedParameter.get().floatValue() + amount * modulator.get().floatValue() * (float)modulatedParameter.range();
	}
	
	private float amount = 0.0f;
	
	private Modulator<Float> modulator;
	private NumericParameter<Float> modulatedParameter;
}
