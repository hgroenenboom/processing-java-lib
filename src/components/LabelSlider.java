package components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class LabelSlider extends JPanel
{
	public LabelSlider(String name, float min, float max)
	{	
		label.setText(name);
		label.add(slider);
		add(label);

		slider.setMinimum((int)Math.ceil(min * 1000.0f));
		slider.setMaximum((int)(max * 1000.0f));
		assert slider.getMaximum() - slider.getMinimum() != 0 : "Slider range should never be 0";
		
		slider.setPaintLabels(true);
		add(slider);
	}
	
	public JLabel label = new JLabel();
	public JSlider slider = new JSlider();
}
