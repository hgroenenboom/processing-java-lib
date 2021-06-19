package dsp;

import java.util.Vector;

public class Delay<T> implements Modulator<T>
{
	public Delay()
	{
		
	}
	
	public void setSize(int size)
	{
		delay = Math.min(size - 1, delay);
		
		memory.setSize(size);
		writePointer %= memory.size();
	}
	
	public void setDelay(int numSamples)
	{
		assert numSamples >= 0 && numSamples < memory.size() : "Invalid delay size given with " + numSamples;
		
		delay = numSamples;
	}
	
	public void push(T newElement)
	{
		memory.set(writePointer, newElement);
		writePointer = ( writePointer + 1 ) % memory.size();
	}
	
	@Override
	public T get()
	{
		return memory.elementAt(readPointer());
	}	
	
	private int readPointer()
	{
		return ( writePointer + memory.size() - delay ) % memory.size(); 
	}
	
	private int delay = 0;
	
	private int writePointer = 0;
	private Vector<T> memory = new Vector<T>();
}
