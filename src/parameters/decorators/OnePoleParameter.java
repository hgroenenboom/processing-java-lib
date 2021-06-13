package parameters.decorators;

import java.util.Objects;

import dsp.OnePole;

import parameters.FloatParameter;
import parameters.IParameter;

public class OnePoleParameter<T> extends FloatParameter
{
	public OnePoleParameter(IParameter<T> parameter)
	{
		super(Objects.requireNonNull(parameter).id + "a1parameter", parameter.manager, -0.9999999f, 0.9999999f);
		set(-1.0f + 1.0f / 20.0f);

		child = parameter;
	}

	@Override
	public Float get()
	{
		return onePole.filter((float) child.get());
	}

	@Override
	public void set(Float newValue)
	{
		super.set(newValue);

		final float clampedValue = super.get();
		onePole.a1 = clampedValue;
		onePole.b0 = 1.0f - Math.abs(clampedValue);
		System.out.println(onePole.a1);
		System.out.println(onePole.b0);
	}

	public OnePole onePole = new OnePole(0.0f);

	public final IParameter<T> child;
}
