package drawing;

import processing.core.PApplet;

public class Background
{
	public static void draw(PApplet context, float r, float g, float b, float alpha, boolean debug)
	{
		if (debug)
		{
			assert alpha >= 0.0f && alpha <= 1.0f : "alpha should be value between 0 and 1, with alpha=" + alpha;
			assert r >= 0.0f && r <= 1.0f : "r should be value between 0 and 1, with r=" + r;
			assert g >= 0.0f && g <= 1.0f : "g should be value between 0 and 1, with g=" + g;
			assert b >= 0.0f && b <= 1.0f : "b should be value between 0 and 1, with b=" + b;
		}

		context.fill(r, g, b, alpha);

		context.rect(0, 0, context.width, context.height);
	}
}
