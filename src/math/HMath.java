package math;

public class HMath
{
    public static float linearInterpolated( float amount, final float first, final float second ) 
    {
  	  // TODO: might be unnecessary exspensive clamping
      amount = Math.min( 1.0f, Math.max( 0.0f, amount ) );
      
      return amount * second + ( 1.0f - amount ) * first;
    }
    
    // TODO: can this be generic? Or is better to create overloads for different numeric types?
    public static float sum(float[] array, int length)
    {
    	// TODO: could add length assertion
    	
    	float sum = 0.0f;
    	
    	for(int i = 0; i < length; i++)
    	{
    		sum += array[i];
    	}
    	
    	return sum;
    }
    
    public static float average(float[] array, int length)
    {
    	// TODO: could add length assertion
    	
    	return sum(array, length) / (float)length;
    }
}
