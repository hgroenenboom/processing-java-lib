package components;

import java.util.Objects;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import parameters.FloatParameter;
import parameters.Parameter;
import parameters.ParameterListener;

public class FloatParameterSlider extends LabelSlider implements ParameterListener<Float>, ChangeListener
{
	public FloatParameterSlider(FloatParameter parameter)
	{
		super(Objects.requireNonNull(parameter).id, (int)(1000.0f * parameter.min()), (int)(1000.0f * parameter.max()));
		
		m_parameter = parameter;
		parameter.addListener(this);
		
        slider.addChangeListener(this);
	}

	@Override
	public void onValueChanged(Parameter<Float> source, Float newValue) 
	{
		// NOTE: this prevents the feedback loop where updating the slider, updates the FloatParameter again
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
