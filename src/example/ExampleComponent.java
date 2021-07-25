package example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import components.FloatParameterSlider;
import components.PresetComponent;
import components.RandomSequencedParameterComponent;
import main.MainWindow;
import parameters.FloatParameter;
import parameters.decorators.RandomSequencedParameter;

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
		
		addSlider(model.speed);
		addSlider(model.change);
		addSlider(model.radius);
		addSlider(model.dist);
		addSlider(model.tilt);
	}
	
	@SuppressWarnings("unused")
	private void addSlider(FloatParameter parameter)
	{
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.gridy += 2;
		
		FloatParameterSlider slider = new FloatParameterSlider(parameter);
		add(slider, constraints);
	}
	
	@SuppressWarnings("unused")
	private void addRandomSequencedParameterComponent(RandomSequencedParameter randomSequencedParameter)
	{
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.gridy += 4;
		
		add(new RandomSequencedParameterComponent(randomSequencedParameter), constraints);
	}
	
	private GridBagConstraints constraints = new GridBagConstraints();
	
	private PresetComponent presetComponent;
}
