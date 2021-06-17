package data;

import java.util.HashSet;

public class Broadcaster<T>
{
	public void addListener(Listener<T> newListener)
	{
		listeners.add(newListener);
	}
	
	public Listener<T> removeListener(Listener<T> oldListener)
	{
		boolean succes = listeners.remove(oldListener);
		
		return succes ? oldListener : null;
	}
	
	private HashSet<Listener<T>> listeners = new HashSet<Listener<T>>();
}
