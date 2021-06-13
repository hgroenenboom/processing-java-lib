package components;

import java.util.Objects;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import parameters.IntParameter;
import parameters.Parameter;
import parameters.ParameterListener;

public class IntParameterSlider extends LabelSlider implements ParameterListener<Integer>, ChangeListener
{
	public IntParameterSlider(IntParameter parameter)
	{
		super(Objects.requireNonNull(parameter).id, parameter.getMin(), parameter.getMax());
		
		m_parameter = parameter;
		parameter.addListener(this);
		
        slider.addChangeListener(this);
	}

	@Override
	public void onValueChanged(Parameter<Integer> source, Integer newValue) 
	{
		// NOTE: this prevents the feedback loop where updating the slider, updates the FloatParameter again
		if(updatedFromGUI)
		{
			updatedFromGUI = false;
		}
		else
		{
			slider.setValue(newValue);
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		updatedFromGUI = true;
		
		m_parameter.set(slider.getValue());
	}
	
	final IntParameter parameter() { return m_parameter; }

	private IntParameter m_parameter;
	
	private boolean updatedFromGUI = false;
	
	private static final long serialVersionUID = 1L;
}
