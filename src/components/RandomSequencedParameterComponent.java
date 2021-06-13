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
	}
	
	@SuppressWarnings("unused")
	private RandomSequencedParameter parameter;
}
