package data;

import java.util.Random;

/** Precomputed list of random numbers, makes it possible to generate sequences of pseudorandom numbers */
public class RandomSequence
{ 
  public RandomSequence(long seed, int size)
  {
    this.SIZE = size;
    size(size);
    
    // Set seed to fill this random list from
    random.setSeed(seed);
    
    // Create random values
    randoms = new float[SIZE];
    for(int i = 0; i < SIZE; i++)
    {
      randoms[i] = random.nextFloat();
    }
  }
  
  public float nextFloat(float min, float max)
  {
    counter = (counter + 1) % activeSize;
    return min + (max - min) * randoms[counter];
  }
  
  public void size(int newSize)
  {
	  assert newSize > 0 && newSize <= SIZE : "new size is not valid";
	  activeSize = newSize;
  }
  
  public int size()
  {
	  return activeSize;
  }
  
  final int SIZE;
  
  int activeSize;
  int counter = 0;
  float[] randoms;
  
  private Random random = new Random();
}
