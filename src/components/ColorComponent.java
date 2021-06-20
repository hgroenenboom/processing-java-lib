package components;

import java.util.Objects;

import javax.swing.JPanel;

import parameters.ColorParameter;
import parameters.FloatParameter;

@SuppressWarnings("serial")
public class ColorComponent extends JPanel
{
	public ColorComponent(ColorParameter parameter)
	{
		add(new ParameterLabel(Objects.requireNonNull(parameter)));
		
		for(FloatParameter value : parameter.values)
		{
			add(new FloatParameterSlider(value));
		}
		
		add(new RandomizeButton(parameter));
	}
}
