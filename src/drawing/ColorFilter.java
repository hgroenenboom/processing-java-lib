package drawing;

import java.util.Objects;

import dsp.OnePole;

public class ColorFilter
{
	public final ColorMode mode;
	OnePole filters[] = new OnePole[4];

	public ColorFilter(final Color initialColor)
	{
		mode = initialColor.getColorMode();

		for(int i = 0; i < 4; i++)
		{
			filters[i] = new OnePole(Objects.requireNonNull(initialColor).values[i]);
		}
	}
	
	public void tick(final Color input)
	{
		assert mode == input.getColorMode();
		
		for(int i = 0; i < 4; i++)
		{
			filters[i].tick(input.values[i]);
		}
	}

	public void setLowpass(float Fc)
	{
		for(OnePole filter : filters)
		{
			filter.setLowpass(Fc);
		}
	}
	
	public void setHighpass(float Fc)
	{
		for(OnePole filter : filters)
		{
			filter.setHighpass(Fc);
		}
	}

	public Color get() 
	{
		Color outputColor = new Color();
		
		if(mode == ColorMode.RGB)
		{
			outputColor.setRgb(filters[0].get(), filters[1].get(), filters[2].get(), filters[3].get());			
		}
		else if (mode == ColorMode.HSL)
		{
			outputColor.setHsl(filters[0].get(), filters[1].get(), filters[3].get(), filters[4].get());
		}
		
		return outputColor;
	}

	public Color filter(final Color input) 
	{
		tick(input);
    
		return get();
	}
	
}
