package components;

import java.util.Objects;

import javax.swing.JPanel;

import parameters.decorators.RandomSequencedParameter;

@SuppressWarnings("serial")
public class RandomSequencedParameterComponent extends JPanel
{
	public RandomSequencedParameterComponent(RandomSequencedParameter parameter)
	{
		this.parameter = Objects.requireNonNull(parameter);
		
		add(new FloatParameterSlider(parameter.onePoleParameter));
		add(new FloatParameterSlider(parameter.rate));
		add(new FloatParameterSlider(parameter.sequenceLength));
		add(new IntParameterSlider(parameter.seed));
	}
	
	@SuppressWarnings("unused")
	private RandomSequencedParameter parameter;
}
