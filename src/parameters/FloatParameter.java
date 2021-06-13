package parameters;

import java.util.HashSet;
import processing.data.JSONObject;

public class FloatParameter extends IParameter 
{
  public FloatParameter(String id, ParameterManager manager, float min, float max)
  {
    super(id);
    
    this.min = min;
    this.max = max;
    
    set(0.0f);
    
    manager.add(this);
  }
  
  public void randomize()
  {
    value = min + (max - min) * (float) Math.random();
    
    updateListeners();
  }
  
  public void save(JSONObject json)
  {
    JSONObject child = new JSONObject();
  
    child.setFloat("min", min);
    child.setFloat("max", max);
    child.setFloat("value", value);
    
    json.setJSONObject(id, child);
  }
  
  public void load(JSONObject json)
  {
    JSONObject child = json.getJSONObject(id);
    if(child == null)
    {
      return;
    }
    
    min = child.getFloat("min");
    max = child.getFloat("max");
    
    set(child.getFloat("value"));
  }
  
  public float get()
  {
    return value;
  }
  
  public void set(float newValue)
  {
    value = Math.min(max, Math.max(min, newValue));
    
    updateListeners();
  }
  
  public void addListener(FloatParameterListener newListener)
  {
	  listeners.add(newListener);
  }
  
  public void removeListener(FloatParameterListener listenerToRemove)
  {
	  listeners.remove(listenerToRemove);
  }
  
  private void updateListeners()
  {
      for(FloatParameterListener listener : listeners)
      {
    	  listener.onValueChanged(value);
      }
  }
  
  // TODO: should be private
  public float min;
  public float max;
  
  private float value;
  
  private HashSet<FloatParameterListener> listeners = new HashSet<FloatParameterListener>();
}
