package parameters.decorators;

import java.util.Objects;

import data.Listener;
import dsp.OnePole;
import dsp.ProcessSettings;
import parameters.FloatParameter;
import parameters.Parameter;

public class OnePoleParameter<T extends Number> extends FloatParameter implements Listener<ProcessSettings>
{
	public OnePoleParameter(ProcessSettings settings, Parameter<T> parameterToSmooth)
	{
		super(Objects.requireNonNull(parameterToSmooth.id) + "-onepole-frequency",
				Objects.requireNonNull(parameterToSmooth.manager), 0.0f, 1.0f);

		this.parameterToSmooth = parameterToSmooth;
		
		this.settings = Objects.requireNonNull(settings);
		settings.addListener(this);
	}

	@Override
	public Float get()
	{
		return output;
	}
	
	public void tick()
	{
		output = onePole.filter(parameterToSmooth.get().floatValue());		
	}

	@Override
	public void set(Float newValue)
	{
		// First set the internal FloatParameters parameter value
		super.set(newValue);
		
		updateFilter();
	}

	@Override
	public void valueChanged(ProcessSettings settings)
	{
		updateFilter();
	}
	
	private void updateFilter()
	{
		onePole.setLowpass( 0.5f * super.get() );
	}

	public OnePole onePole = new OnePole(0.0f);

	Parameter<T> parameterToSmooth;
	
	final ProcessSettings settings;
	
	private float output;
}
