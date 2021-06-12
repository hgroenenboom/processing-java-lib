package components;

import java.util.Objects;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import parameters.FloatParameter;
import parameters.FloatParameterListener;

public class FloatParameterSlider extends LabelSlider implements FloatParameterListener, ChangeListener
{
	public FloatParameterSlider(FloatParameter parameter)
	{
		super(Objects.requireNonNull(parameter).id, parameter.min, parameter.max);
		
		m_parameter = parameter;
		parameter.addListener(this);
		
        slider.addChangeListener(this);
	}

	@Override
	public void onValueChanged(float newValue) 
	{
		if(updatedFromGUI)
		{
			updatedFromGUI = false;
		}
		else
		{
			slider.setValue((int)(1000.0f * newValue));
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		updatedFromGUI = true;
		
		m_parameter.set(0.001f * slider.getValue());
	}
	
	final FloatParameter parameter() { return m_parameter; }

	private FloatParameter m_parameter;
	
	private boolean updatedFromGUI = false;
	
	private static final long serialVersionUID = 1L;
}
