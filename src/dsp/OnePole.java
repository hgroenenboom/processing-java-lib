package dsp;

public class OnePole 
{
  public OnePole(float initValue) 
  {
	output = initValue;
  }

  public void tick(float input)
  {
	  output = b0 * input + a1 * output;
  }

  public void setLowpass(float Fc)
  {
	  assert Fc >= 0.0f && Fc <= 0.5f : "Fc is supposed to be a relative value inside the nyquist range, with Fc = " + Fc;
	  a1 = (float)Math.exp( -TWO_PI * Fc );
	  b0 = 1.0f - a1;
  }
  
  public void setHighpass(float Fc)
  {
	  assert Fc >= 0.0f && Fc <= 0.5f : "Fc is supposed to be a relative value inside the nyquist range";
	  a1 = - (float)Math.exp( -TWO_PI * (0.5f - Fc) );
	  b0 = 1.0f - a1;
  }
  
  public float get() 
  {
    return output;
  }

  public float filter(float input) 
  {
    tick(input);
    
    return output;
  }
  
  public float b0 = 1.0f;
  public float a1;
  
  private final float TWO_PI = 2.0f * (float)Math.PI;
  
  private float output = 0;
}
