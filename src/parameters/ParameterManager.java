package parameters;

import java.util.ArrayList;
import processing.data.JSONObject;

public class ParameterManager 
{
  public void add(IParameter parameterToAdd)
  {
    for(IParameter parameter : parameters)
    {
      if(parameter.id == parameterToAdd.id)
      {
        return;
      }
    }
    
    parameters.add(parameterToAdd);
  }
  
  public void save(JSONObject parent)
  {
    JSONObject json = new JSONObject();
    
    for (IParameter parameter : parameters)
    {
      parameter.save(json);
    }

    parent.setJSONObject("parameters", json);
  }
  
  public void load(JSONObject parent)
  {
    JSONObject json = parent.getJSONObject("parameters");
    if(json == null)
    {
      return;
    }
    
    for (IParameter parameter : parameters)
    {
      parameter.load(json);
    }
  }
  
  public void randomize()
  {
    for(IParameter parameter : parameters)
    {
      parameter.randomize();
    }
  }
  
  private ArrayList<IParameter> parameters = new ArrayList<IParameter>();
}
