package dsp;

import java.util.Objects;

import data.Listener;

public class Phasor implements Listener<ProcessSettings>, Modulator<Float>
{
	public Phasor(ProcessSettings settings)
	{
		m_settings = Objects.requireNonNull(settings);
		m_settings.addListener(this);
		
		frequency(1.0f);
	}
	
	public void frequency(float frequency)
	{	
		m_frequency = frequency;
		
		// period = sampleRate / frequency
		// period = 100hz / 10hz = 10;
		// increment = reciprocalPeriod = frequency / sampleRate
		m_increment = m_settings.rSampleRate() * frequency;
		
	}
	
	public float frequency()
	{
		return m_frequency;
	}
	
	public float tick()
	{
		output += m_increment;
		
		output = fMod(output);
		
		return output;
	}
	
	@Override
	public Float get()
	{
		return output;
	}
	
	@Override
	public void valueChanged(ProcessSettings newValue)
	{
		// Update sample period from the new ProcessSettings and the current frequency
		frequency(m_frequency);
	}
	
	public static float fMod(float value)
	{
		return value - (float)Math.floor(value);
	}
	
	private ProcessSettings m_settings;
	
	private float m_frequency;
	private float m_increment;
	
	private float output = 0.0f;
}
