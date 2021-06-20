package parameters;

import java.util.Objects;

import processing.data.JSONObject;

public class FloatParameter extends NumericParameter<Float>
{
	public FloatParameter(String id, ParameterManager manager, float min, float max)
	{
		super(Objects.requireNonNull(id), Objects.requireNonNull(manager), min, max);

		value = Math.min(max(), Math.max(min(), 0.0f));
	}
	
	public FloatParameter(String id, float min, float max)
	{
		super(Objects.requireNonNull(id), min, max);

		value = Math.min(max(), Math.max(min(), 0.0f));
	}

	public void randomize()
	{
		set( min() + (max() - min()) * (float) Math.random() );
	}

	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();

		child.setFloat("min", min());
		child.setFloat("max", max());
		child.setFloat("value", value);

		Objects.requireNonNull(json).setJSONObject(id, child);
	}

	public void load(JSONObject json)
	{
		JSONObject child = json.getJSONObject(id);
		if (child == null)
		{
			return;
		}

		min( child.getFloat("min") );
		max( child.getFloat("max") );

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
		value = Math.min(max(), Math.max(min(), newValue));

		updateListeners(value);
	}
	
	public float normalized()
	{
		return ( value - min() ) / (float)range();
	}
	
	public float normalized(float normalized)
	{
		set( min() + (float)range() * normalized );
		return get();
	}

	private float value;
}
