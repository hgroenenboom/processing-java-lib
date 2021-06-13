package parameters;

import processing.data.JSONObject;

public class FloatParameter extends IParameter<Float>
{
	public FloatParameter(String id, ParameterManager manager, float min, float max)
	{
		super(id, manager);

		this.min = min;
		this.max = max;

		value = Math.min(max, Math.max(min, 0.0f));
	}

	public void randomize()
	{
		set( min + (max - min) * (float) Math.random() );
	}

	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();

		child.setFloat("min", min);
		child.setFloat("max", max);
		child.setFloat("value", value);

		json.setJSONObject(id, child);
	}

	public void load(JSONObject json)
	{
		JSONObject child = json.getJSONObject(id);
		if (child == null)
		{
			return;
		}

		min = child.getFloat("min");
		max = child.getFloat("max");

		set( child.getFloat("value") );
	}

	@Override
	public Float get()
	{
		return value;
	}

	@Override
	public void set(Float newValue)
	{
		value = Math.min(max, Math.max(min, newValue));

		updateListeners(value);
	}

	public float getMin()
	{
		return min;
	}

	public float getMax()
	{
		return max;
	}

	private float min;
	private float max;

	private float value;
}
