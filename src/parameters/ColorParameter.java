package parameters;

import java.util.ArrayList;
import java.util.Objects;

import drawing.Color;
import drawing.ColorMode;
import processing.data.JSONObject;

public class ColorParameter extends Parameter<Color> implements ParameterListener<Float>
{
	public ColorParameter(String id, ParameterManager manager)
	{
		super(id, manager);
		
		initialize();
	}
	
	public ColorParameter(String id)
	{
		super(id);
		
		initialize();
	}

	@Override
	public void randomize()
	{		
		for(FloatParameter value : values)
		{
			value.randomize();
		}
	}

	@Override
	public void save(JSONObject json)
	{
		JSONObject child = new JSONObject();
		
		for(FloatParameter value : values)
		{
			value.save(child);
		}
		
		json.setJSONObject(id, child);
	}

	@Override
	public void load(JSONObject json)
	{
		JSONObject child = json.getJSONObject(id);
		if(child == null)
		{
			return;
		}
		
		for(FloatParameter value : values)
		{
			value.load(child);
		}
	}

	@Override
	public Color get()
	{
		return color;
	}

	@Override
	public void set(Color newData)
	{
		Objects.requireNonNull(newData);
		
		values[0].set(color.r());
		values[1].set(color.g());
		values[2].set(color.b());
		values[3].set(color.alpha());
	}
	
	private void initialize()
	{
		for(int i = 0; i < 4; i++)
		{
			values[i] = new FloatParameter(ids.get(i), 0.0f,  1.0f);
			values[i].addListener(this);
		}
	}

	@Override
	public void onValueChanged(Parameter<Float> source, Float newValue)
	{
		for(int i = 0; i < values.length; i++)
		{
			if(values[i] == source)
			{
				assert color.getColorMode() == ColorMode.RGB;
				color.values[i] = newValue;
			}
		}
	}

	private Color color = new Color();
	
	@SuppressWarnings("serial")
	final private ArrayList<String> ids = new ArrayList<String>() {{ add("r"); add("g"); add("b"); add("alpha");  }};
	
	public FloatParameter[] values = new FloatParameter[4];
}
