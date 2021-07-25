package example;

import java.util.Objects;

import drawing.Color;
import drawing.ColorFilter;
import dsp.ProcessSettings;
import processing.core.PApplet;

public class RainDrop
{
	private float xPos = 0.5f;
	private float yPos = 0.5f;
	private float radius = 0.3f;
	private float speedInHz = 2.0f;
	private float triggerHz = 0.5f;
	private float tilt = 1.0f;
	private float distanceFactor = 1.0f;
	
	final Color oceanColor = new Color(0.3f, 0.5f, 0.7f, 1.0f);
	Color color = new Color();
	ColorFilter colorFilter = new ColorFilter(color);
	
	private int counter;
	private float _radius;
	
	private ProcessSettings settings;
	
	public RainDrop(ProcessSettings settings)
	{
		this.settings = Objects.requireNonNull(settings);
		
		colorFilter.setLowpass(0.01f);
	}
	
	public void draw(PApplet context)
	{
		final float screenSize = 0.5f * (context.width + context.height);
		
		final float triggerChanceInSamples = settings.sampleRate() / triggerHz;
		final float chance = 1.0f / triggerChanceInSamples;
		
		// Compute whether there is a new trigger for this RainDrop
		final boolean newTrigger = context.random(0.0f, 1.0f) < chance;
		
		if(newTrigger)
		{
			color.randomize();
		}
		Color filteredColor = colorFilter.filter(color);
		
		// Start, or continue drawing a raindrop
		if(newTrigger || counter > 0)
		{
			final float speedInSamples = settings.sampleRate() / speedInHz;
			final float radiusIncrement = radius / speedInSamples;
			
			counter++;
			_radius += screenSize * radiusIncrement;
			
			context.fill(0.0f, 0.0f);
			filteredColor.colorify(oceanColor, 0.95f, true);
			context.stroke(255.0f * filteredColor.r(), 255.0f * filteredColor.g(), 255.0f * filteredColor.b(), 255.0f * filteredColor.alpha());
			
			final float yInverted = 1.0f - yPos;
			final float sigmoidY = yInverted / (1.0f + Math.abs(yInverted));
			final float distanceMultiplierForYPos = (float)Math.pow( Math.max( 0.0000001f, 1.0f - sigmoidY ), distanceFactor );
			assert distanceMultiplierForYPos >= 0.0f;
			
			final float tiltedYPosition = 0.5f + (yPos - 0.5f) / tilt;
			final float tiltedYPositionWithDistance = 1.0f - (1.0f - tiltedYPosition) * distanceMultiplierForYPos;
			
			final float radiusWithDistance = _radius * distanceMultiplierForYPos;
			final float xPositionWithDistance = 0.5f + (xPos - 0.5f) * distanceMultiplierForYPos;
			
			context.ellipse(xPositionWithDistance * context.width, tiltedYPositionWithDistance * context.height, radiusWithDistance, radiusWithDistance / tilt);
			
			if(counter > Math.floor(speedInSamples))
			{
				_radius = 0.0f;
				counter = 0;
			}
		}
	}
	
	public void dist(float dist)
	{
		distanceFactor = dist;
	}

	public void setPosition(float xPos, float yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setParameters(float radius, float speedInHz, float triggerHz, float distance, float tilt)
	{
		this.radius = radius;
		this.speedInHz = speedInHz;
		this.triggerHz = triggerHz;
		this.distanceFactor = distance;
		this.tilt = tilt;
	}
}
