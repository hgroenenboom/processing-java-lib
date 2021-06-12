package example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import components.FloatParameterSlider;
import components.PresetComponent;

import main.MainWindow;
import parameters.FloatParameter;

@SuppressWarnings("serial")
public class MainComponent extends MainWindow
{
	public MainComponent(Main main)
	{
		super(main);
		
		presetComponent = new PresetComponent(main.manager);

        setLayout(new GridBagLayout());
        final int padding = 3;
        constraints.insets = new Insets(padding, padding, padding, padding);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(presetComponent, constraints);

		radiusSlider = addSlider(main.radius);
		xPos = addSlider(main.xPos);
		yPos = addSlider(main.yPos);
	}
	
	private FloatParameterSlider addSlider(FloatParameter parameter)
	{
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridy += 2;
		
		FloatParameterSlider slider = new FloatParameterSlider(parameter);
		add(slider, constraints);
		
		return slider;
	}
	
	private GridBagConstraints constraints = new GridBagConstraints();
	
	private PresetComponent presetComponent;
	
	private FloatParameterSlider radiusSlider;
	private FloatParameterSlider xPos, yPos;
}
