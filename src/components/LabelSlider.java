package components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class LabelSlider extends JPanel
{
	public LabelSlider(String name, int min, int max)
	{	
		label.setText(name);
		add(label);

		slider.setMinimum(min);
		slider.setMaximum(max);
		assert slider.getMaximum() - slider.getMinimum() > 0 : "Slider range should never be smaller or equal to 0";
		add(slider);
	}
	
	public JLabel label = new JLabel();
	public JSlider slider = new JSlider();
}
