package parameters;

import processing.data.JSONObject;

public abstract class IParameter 
{
  public IParameter(String id)
  {
    this.id = id;
  }
  
  public abstract void randomize();
  
  public abstract void save(JSONObject json);
  public abstract void load(JSONObject json);
  
  public String id;
}
