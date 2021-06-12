package example;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import components.FloatParameterSlider;
import components.PresetComponent;

import main.MainWindow;

@SuppressWarnings("serial")
public class MainComponent extends MainWindow
{
	public MainComponent(Main main)
	{
		super(main);
		
		presetComponent = new PresetComponent(main.manager);
		radiusSlider = new FloatParameterSlider(main.radius);
		
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        final int padding = 3;
        constraints.insets = new Insets(padding, padding, padding, padding);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(presetComponent, constraints);

		constraints.gridy += 2;
		add(radiusSlider, constraints);
	}
	
	private PresetComponent presetComponent;
	
	private FloatParameterSlider radiusSlider;
}
