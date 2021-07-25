package dsp;

/**
 * Basic vector class for applying simple math/functions to positions and
 * angles.
 */
public class Vector
{
	private float x = 1.0f, y = 0.0f;
	private float angle = 0.0f;

	public Vector(float x, float y)
	{
		setPosition(x, y);
	}

	public Vector(float angle)
	{
		changeAngle(angle);
	}

	public void changeAngle(float angle)
	{
		this.angle = angle;

		final float distance = getDistance();
		x = (float) Math.cos(angle) * distance;
		y = (float) Math.sin(angle) * distance;
	}

	public void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;

		angle = (float) Math.atan2(y, x);
	}

	public void moveTo(float angle, float distance)
	{
		setPosition(x + (float) Math.cos(angle) * distance, y + (float) Math.sin(angle) * distance);
	}

	public float getDistance()
	{
		return (float) Math.pow(Math.pow(x, 2.0f) + Math.pow(y, 2.0f), 0.5);
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getAngle()
	{
		return angle;
	}
}