package dsp;

public class ProcessSettings
{
	public ProcessSettings(float initialSampleRate)
	{
		sampleRate(initialSampleRate);
	}

	public void sampleRate(float sampleRate)
	{
		assert sampleRate > 0.0f : "Samplerate should always be positive";
		m_sampleRate = sampleRate;
		m_rSampleRate = 1.0f / m_sampleRate;
	}

	public float sampleRate()
	{
		return m_sampleRate;
	}

	public float rSampleRate()
	{
		return m_rSampleRate;
	}

	private float m_sampleRate;
	private float m_rSampleRate;
}
