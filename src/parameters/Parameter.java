package parameters;

import java.util.HashSet;
import java.util.Objects;

import processing.data.JSONObject;

public abstract class Parameter<T>
{
	public Parameter(String id, ParameterManager manager)
	{
		this.id = Objects.requireNonNull(id);
		this.manager = Objects.requireNonNull(manager);

		manager.add(this);
	}

	public abstract void randomize();

	public abstract void save(JSONObject json);

	public abstract void load(JSONObject json);

	public abstract T get();

	public abstract void set(T newData);

	public void addListener(ParameterListener<T> newListener)
	{
		listeners.add(newListener);
	}

	public void removeListener(ParameterListener<T> listenerToRemove)
	{
		listeners.remove(listenerToRemove);
	}

	protected void updateListeners(T newValue)
	{
		for (ParameterListener<T> listener : listeners)
		{
			listener.onValueChanged(this, newValue);
		}
	}

	public final String id;
	public ParameterManager manager;

	private HashSet<ParameterListener<T>> listeners = new HashSet<ParameterListener<T>>();
}
