package parameters.decorators;

import java.util.Objects;

import dsp.OnePole;
import parameters.FloatParameter;
import parameters.Parameter;

public class OnePoleParameter<T extends Number> extends FloatParameter
{
	public OnePoleParameter(Parameter<T> parameterToSmooth)
	{
		super(Objects.requireNonNull(parameterToSmooth.id) + "-onepole",
				Objects.requireNonNull(parameterToSmooth.manager), 0.0f, 0.5f);

		this.parameterToSmooth = parameterToSmooth;

		set(-1.0f + 1.0f / 20.0f);
	}

	@Override
	public Float get()
	{
		return onePole.filter(parameterToSmooth.get().floatValue());
	}

	@Override
	public void set(Float newValue)
	{
		super.set(newValue);

		final float clampedValue = super.get();
		onePole.setLowpass(clampedValue);
	}

	public OnePole onePole = new OnePole(0.0f);

	Parameter<T> parameterToSmooth;
}
