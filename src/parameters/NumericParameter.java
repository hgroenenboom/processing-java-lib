package parameters;

public abstract class NumericParameter<T extends Number> extends Parameter<T>
{
	public NumericParameter(String id, ParameterManager manager, T min, T max)
	{
		super(id, manager);

		m_min = min;
		m_max = max;
		
		assertRange();
	}

	public double range()
	{
		return m_max.doubleValue() - m_min.doubleValue();
	}

	public T min()
	{
		return m_min;
	}
	
	public void min(T min)
	{
		m_min = min;
		assertRange();
	}
	
	public T max()
	{
		return m_max;
	}
	
	public void max(T max)
	{
		m_max = max;
		assertRange();
	}
	
	private void assertRange()
	{
		// TODO: assert max is always larger then min
	}
	
	private T m_min, m_max;
}
