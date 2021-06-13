package parameters;

import java.util.Objects;

import processing.data.JSONObject;

public abstract class IParameter<T> 
{
  public IParameter(String id, ParameterManager manager)
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
  
  public final String id;
  public ParameterManager manager;
}
