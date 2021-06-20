package components;

import java.util.Objects;

import javax.swing.JPanel;

import parameters.decorators.RandomSequencedParameter;

@SuppressWarnings("serial")
public class RandomSequencedParameterComponent extends JPanel
{
	public RandomSequencedParameterComponent(RandomSequencedParameter parameter)
	{
		add(new ParameterLabel(Objects.requireNonNull(parameter)));
		
		add(new FloatParameterSlider(parameter.onePoleParameter));
		add(new FloatParameterSlider(parameter.rate));
		add(new FloatParameterSlider(parameter.sequenceLength));
		add(new IntParameterSlider(parameter.seed));
		
		add(new RandomizeButton(parameter));
	}
}
