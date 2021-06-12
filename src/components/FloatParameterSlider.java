package components;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import parameters.FloatParameter;
import parameters.FloatParameterListener;

public class FloatParameterSlider extends JSlider implements FloatParameterListener, ChangeListener
{
	public FloatParameterSlider(FloatParameter parameter)
	{
		assert parameter != null;
		
		m_parameter = parameter;
		
		setMinimum((int)Math.ceil(parameter.min * 1000.0f));
		setMaximum((int)(parameter.max * 1000.0f));
		
        addChangeListener(this);
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
			setValue((int)(1000.0f * newValue));
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) 
	{
		updatedFromGUI = true;
		
		float newValue = 0.001f * getValue();
		m_parameter.set(newValue);
	}
	
	final FloatParameter parameter() { return m_parameter; }

	private FloatParameter m_parameter;
	
	private boolean updatedFromGUI = false;
	
	private static final long serialVersionUID = 1L;
}
