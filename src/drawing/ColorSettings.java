package drawing;

import java.util.Objects;

import processing.core.PApplet;

public class ColorSettings
{
	public ColorSettings(PApplet context, ColorMode initialColorMode)
	{
		m_context = Objects.requireNonNull(context);
		
		colorMode(initialColorMode);
	}
	
	public void colorMode(ColorMode newColorMode)
	{
		assert newColorMode != ColorMode.HSL : "Colourmode HSL is not yet supported (Processing does not have native support for this)"; 
		
		m_colorMode = newColorMode;
		m_context.colorMode(newColorMode == ColorMode.RGB ? PApplet.RGB : PApplet.HSB, 1.0f);
	}
	
	public ColorMode colorMode()
	{
		return m_colorMode;
	}
	
	private PApplet m_context;
	private ColorMode m_colorMode;
}
