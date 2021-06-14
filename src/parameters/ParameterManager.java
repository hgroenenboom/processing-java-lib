package parameters;

import java.util.HashMap;
import java.util.Objects;

import dsp.ProcessSettings;
import processing.data.JSONObject;

@SuppressWarnings("rawtypes")
public class ParameterManager
{
	public ParameterManager(ProcessSettings settings)
	{
		m_processSettings = Objects.requireNonNull(settings);
	}

	public void add(Parameter parameterToAdd)
	{
		assert !parameters.containsKey(parameterToAdd.id) : "A parameter with the same ID has already been added.";

		parameters.put(parameterToAdd.id, parameterToAdd);
	}

	public void save(JSONObject parent)
	{
		JSONObject json = new JSONObject();

		for (Parameter parameter : parameters.values())
		{
			parameter.save(json);
		}

		parent.setJSONObject("parameters", json);
	}

	public void load(JSONObject parent)
	{
		JSONObject json = parent.getJSONObject("parameters");
		if (json == null)
		{
			return;
		}

		for (Parameter parameter : parameters.values())
		{
			parameter.load(json);
		}
	}

	public void randomize()
	{
		for (Parameter parameter : parameters.values())
		{
			parameter.randomize();
		}
	}

	public ProcessSettings processSettings()
	{
		return m_processSettings;
	}

	private HashMap<String, Parameter> parameters = new HashMap<String, Parameter>();

	private ProcessSettings m_processSettings;
}
