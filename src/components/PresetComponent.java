package components;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import parameters.ParameterManager;
import presets.PresetLoader;
import presets.PresetSaver;
import processing.data.JSONObject;

public class PresetComponent extends JPanel implements ActionListener
{
	public PresetComponent(ParameterManager manager)
	{    
       this.manager = manager;
	       
       randomizeButton.addActionListener(this);
	   add(randomizeButton); // Adds Button to content pane of frame
       
       saveButton.addActionListener(this);
       add(saveButton);
       
       loadButton.addActionListener(this);
       add(loadButton);
       
       setMinimumSize(new Dimension((int)(3 * randomizeButton.getMinimumSize().getWidth()), (int)randomizeButton.getMinimumSize().getHeight()));
       setLayout(new GridBagLayout());
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		
		if(randomizeButton == source)
		{
			manager.randomize();
		}
		else if (saveButton == source)
		{
			JSONObject json = new JSONObject();
			manager.save(json);
			PresetSaver.save(json);
		}
		else if (loadButton == source)
		{
			JSONObject json = PresetLoader.load();
			if (json != null)
			{
				manager.load(json);				
			}
		}
	}
	
	
    private JButton randomizeButton = new JButton("Randomize");
	
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");  
	
	private ParameterManager manager;
	
	private static final long serialVersionUID = 1L;
}
