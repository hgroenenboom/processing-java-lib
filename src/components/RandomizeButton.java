package components;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import parameters.Parameter;

@SuppressWarnings("rawtypes")
public class RandomizeButton extends Button implements ActionListener
{
	public RandomizeButton(Parameter parameter)
	{
		this.parameter = Objects.requireNonNull(parameter);
		
		setLabel("randomize");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		parameter.randomize();
	}

	private Parameter parameter;
	
	private static final long serialVersionUID = -5484927513740461858L;
}
