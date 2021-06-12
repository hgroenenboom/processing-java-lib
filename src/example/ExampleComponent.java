package example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import components.FloatParameterSlider;
import components.PresetComponent;

import main.MainWindow;
import parameters.FloatParameter;

@SuppressWarnings("serial")
public class ExampleComponent extends MainWindow
{
	public ExampleComponent(ExampleApplication model)
	{
		super(model);
		
		presetComponent = new PresetComponent(model.manager);

        setLayout(new GridBagLayout());
        final int padding = 3;
        constraints.insets = new Insets(padding, padding, padding, padding);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(presetComponent, constraints);

		addSlider(model.radius);
		addSlider(model.xPos);
		addSlider(model.yPos);
		addSlider(model.numBalls);
		addSlider(model.randomVariation);
	}
	
	private FloatParameterSlider addSlider(FloatParameter parameter)
	{
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.gridy += 2;
		
		FloatParameterSlider slider = new FloatParameterSlider(parameter);
		add(slider, constraints);
		
		return slider;
	}
	
	private GridBagConstraints constraints = new GridBagConstraints();
	
	private PresetComponent presetComponent;
}
