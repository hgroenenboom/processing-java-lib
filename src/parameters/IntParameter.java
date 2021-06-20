package parameters;

import processing.data.JSONObject;

public class IntParameter extends NumericParameter<Integer>
{
	public IntParameter(String id, ParameterManager manager, int min, int max)
	{
		super(id, manager, min, max);

		set(min());
	}
	
	public IntParameter(String id, int min, int max)
	{
		super(id, min, max);

		set(min());
	}

	public void randomize()
	{
		set( min() + (int)( Math.random() * range() ) );
	}

	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();

		child.setInt("min", min());
		child.setInt("max", max());
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

		min( child.getInt("min") );
		max( child.getInt("max") );

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
		value = Math.min(max(), Math.max(min(), newValue));

		updateListeners(value);
	}

	private int value;
}
