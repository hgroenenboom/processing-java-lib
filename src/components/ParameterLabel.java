package components;

import java.awt.Label;

import parameters.Parameter;

public class ParameterLabel extends Label
{
	@SuppressWarnings("rawtypes")
	public ParameterLabel(Parameter parameter)
	{
		setText(parameter.id);
	}

	private static final long serialVersionUID = -4146694079497522524L;
}
