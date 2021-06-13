package parameters.decorators;

import java.util.Objects;
import dsp.OnePole;

import parameters.FloatParameter;
import parameters.IParameter;
import parameters.ParameterManager;

public class OnePoleParameter extends FloatParameter
{
	public OnePoleParameter(String id, ParameterManager manager)
	{
		super(Objects.requireNonNull(id) + "-onepole-a1parameter", Objects.requireNonNull(manager), -0.9999999f, 0.9999999f);
		set(-1.0f + 1.0f / 20.0f);
	}

	@Override
	public Float get()
	{
		return onePole.get();
	}

	@Override
	public void set(Float newValue)
	{
		super.set(newValue);

		final float clampedValue = super.get();
		onePole.a1 = clampedValue;
		onePole.b0 = 1.0f - Math.abs(clampedValue);
	}

	public OnePole onePole = new OnePole(0.0f);
}
