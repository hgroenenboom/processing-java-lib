package parameters;

import processing.data.JSONObject;

public class IntParameter extends IParameter<Integer>
{
	public IntParameter(String id, ParameterManager manager, int min, int max)
	{
		super(id, manager);

		this.min = min;
		this.max = max;

		value = Math.min(max, Math.max(min, 0));
	}

	public void randomize()
	{
		set( min + (int)((float)(max - min) * (float) Math.random()) );
	}

	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();

		child.setInt("min", min);
		child.setInt("max", max);
		child.setInt("value", value);

		json.setJSONObject(id, child);
	}

	public void load(JSONObject json)
	{
		JSONObject child = json.getJSONObject(id);
		if (child == null)
		{
			return;
		}

		min = child.getInt("min");
		max = child.getInt("max");

		set( child.getInt("value") );
	}

	@Override
	public Integer get()
	{
		return value;
	}

	@Override
	public void set(Integer newValue)
	{
		value = Math.min(max, Math.max(min, newValue));

		updateListeners(value);
	}

	public Integer getMin()
	{
		return min;
	}

	public Integer getMax()
	{
		return max;
	}

	private int min;
	private int max;

	private int value;
}
