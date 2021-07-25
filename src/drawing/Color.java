package drawing;

import java.util.Objects;

import math.HMath;

public class Color
{
	public float[] values = new float[4];

	public Color(final Color c)
	{
		set(c);
	}

	public Color()
	{
		setRgb(0.0f, 0.0f, 0.0f, 1.0f);
	}

	public Color(final float r, final float g, final float b, final float alpha)
	{
		setRgb(r, g, b, alpha);
	}

	public void set(final Color c)
	{
		for (int i = 0; i < 4; i++)
		{
			this.values[i] = c.values[i];
		}
		this.colorMode = c.colorMode;
	}

	public void setRgb(final float r, final float g, final float b, final float alpha)
	{
		values[0] = r;
		values[1] = g;
		values[2] = b;
		values[3] = alpha;
		colorMode = ColorMode.RGB;
	}

	public void setHsl(final float h, final float s, final float l, final float alpha)
	{
		values[0] = h;
		values[1] = s;
		values[2] = l;
		values[3] = alpha;
		colorMode = ColorMode.HSL;
	}

	public Color brighter(float amount)
	{
		return lightness(1.0f + amount);
	}

	public Color darker(float amount)
	{
		return lightness(1.0f - amount);
	}

	/** 2 = max, 0 = min */
	public Color lightness(float amount)
	{
		if ( colorMode == ColorMode.RGB )
		{
			for (int i = 0; i < 3; i++)
			{
				values[i] = pullTowards(amount, 0.0f, 1.0f, values[i]);
			}
		} 
		else if ( colorMode == ColorMode.HSL )
		{
			values[2] = pullTowards(amount, 0.0f, 1.0f, values[2]);
		}

		return this;
	}

	public void greyScale(final float amount)
	{
		if ( colorMode == ColorMode.RGB )
		{
			final float avg = HMath.average(values, 3);

			for (int i = 0; i < 3; i++)
			{
				values[i] = HMath.linearInterpolated(amount, avg, values[i]);
			}
		} 
		else if ( colorMode == ColorMode.HSL )
		{
			// Set saturation to 0
			values[1] = 0.0f;
		}
	}

	public void colorify(final Color other, float amount, final boolean useAlpha)
	{
		assert other
				.getColorMode() == colorMode : "Colors with different colorModes can't be merged, convert to the same colormode first (with colorModes "
						+ colorMode + " and " + other.colorMode;

		for (int i = 0; i < (useAlpha ? 4 : 3); i++)
		{
			values[i] = HMath.linearInterpolated(amount, values[i], other.values[i]);
		}
	}

	public Color invert()
	{
		for (int i = 0; i < 3; i++)
		{
			values[i] = 1.0f - values[i];
		}

		return this;
	}

	public final Color getInverted()
	{
		return new Color(this).invert();
	}

	public Color saturate(float amount)
	{
		assert colorMode == ColorMode.HSL;

		values[1] = pullTowards(amount, 0.0f, 1.0f, values[1]);

		return this;
	}

	public Color changeHue(float amount)
	{
		assert colorMode == ColorMode.HSL;

		// Adjust Hue
		values[0] += amount;

		// Wrap Hue in 0-1 range
		final float hue = values[0];
		values[0] = hue < 0.0f ? hue + 1.0f : hue;

		return this;
	}

	public float r()
	{
		assert colorMode == ColorMode.RGB;
		return values[0];
	}

	public float g()
	{
		assert colorMode == ColorMode.RGB;
		return values[1];
	}

	public float b()
	{
		assert colorMode == ColorMode.RGB;
		return values[2];
	}
	
	public float alpha()
	{
		return values[3];
	}

	public void toHSL()
	{
		// Get current RGB values and assert whether the Color is in RGB mode
		assert colorMode == ColorMode.RGB;
		final float red = r();
		final float green = g();
		final float blue = b();

		// Get max and min RGB values
		final float max = Math.max(red, Math.max(green, blue));
		final float min = Math.min(red, Math.min(green, blue));

		// compute initial HSL values
		float h = 0.5f * (max + min);
		float s = h;
		float l = h;

		if ( max == min )
		{
			// Greyscale
			h = 0.0f;
			s = 0.0f;
		} 
		else
		{
			final float d = max - min;

			s = (l > 0.5f) ? (d / (2.0f - max - min)) : (d / (max + min));

			if ( max == red )
			{
				h = (green < blue ? 6.0f : 0.0f) + (green - blue) / d;
			}
			if ( max == green )
			{
				h = 2.0f + (blue - red) / d;
			}
			if ( max == blue )
			{
				h = 4.0f + (red - green) / d;
			}
		}

		// devided by 6
		h *= 0.1666666f;

		values[0] = h;
		values[1] = s;
		values[2] = l;
		colorMode = ColorMode.HSL;
	}

	public void toRGB()
	{
		assert colorMode == ColorMode.HSL;
		final float h = values[0];
		final float s = values[1];
		final float l = values[2];

		if ( s == 0.0f )
		{
			// Greyscale
			values[0] = l;
			values[1] = values[0];
			values[2] = values[0];
		} 
		else
		{
			final float q = l < 0.5f ? l * (1.0f + s) : l + s - l * s;
			final float p = 2.0f * l - q;

			values[0] = hue2rgb(p, q, h + 0.333333333333333f);
			values[1] = hue2rgb(p, q, h);
			values[2] = hue2rgb(p, q, h - 0.333333333333333f);
		}

		colorMode = ColorMode.RGB;
	}

	public ColorMode getColorMode()
	{
		return colorMode;
	}
	
	public void randomize()
	{
		for(short i = 0; i < 4; i++)
		{			
			values[i] = (float)Math.random();
		}
	}

	public void print(final String identifier)
	{
		System.out.println(new StringBuilder().append("color ").append(Objects.requireNonNull(identifier)).append(": ")
				.append(values[0]).append(", ").append(values[1]).append(", ").append(values[2]).append(", ")
				.append(values[3]).append(", ").append(" with colourMode: ").append(colorMode.toString()).toString());
	}

	private float pullTowards(final float pull, final float min, final float max, float val)
	{
		assert pull >= 0.0f && pull <= 2.0f : "pull should be a value between 0 and 2 (given " + pull + ")";
		assert max >= min : "range should be positive";
		assert max >= val && min <= val : "value should be in the given range";

		final float maxRange = max - val;
		final float minRange = val - min;

		if ( pull < 1.0f )
		{
			val = min + pull * minRange;
		} 
		else
		{
			val = val + (pull - 1.0f) * maxRange;
		}

		return val;
	}

	private float hue2rgb(final float p, final float q, float t)
	{
		// t %= 1.0f;
		if ( t < 0.0f )
		{
			t += 1.0f;
		} 
		else if ( t > 1.0f )
		{
			t -= 1.0f;
		}

		if ( t < 0.1666666666666f )
		{
			return p + 6.0f * t * (q - p);
		} 
		else if ( t < 0.5f )
		{
			return q;
		} 
		else if ( t < 0.66666666666667f )
		{
			return p + 6.0f * (0.666666666667f - t) * (q - p);
		}

		return p;
	}

	private ColorMode colorMode;
}

/*
 * class HColorFilter { HColor output;
 * 
 * HColorFilter() { output = new HColor(0, 0, 0); }
 * 
 * public void filterColor(final HColor c) { output.colorify(c, 0.3f, true); }
 * 
 * public HColor get() { return output; } }
 */
