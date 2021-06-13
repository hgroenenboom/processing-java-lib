package dsp;

public class OnePole 
{
  public OnePole(float initValue) 
  {
	output = initValue;
  }

  public void set(float input)
  {
	  output = b0 * input - a1 * output;
  }

  public float get() 
  {
    return output;
  }

  public float setAndGet(float input) 
  {
    set(input);
    
    return output;
  }
  
  public float b0 = 1.0f;
  public float a1;
  
  private float output = 0;
}
