package parameters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

import processing.data.JSONObject;

public class PresetSaver 
{
	public static void save(JSONObject json, String presetName)
	{
		json.setString("presetName", presetName);
		
		JFileChooser fileChooser = new JFileChooser();
		File presetDir = new File("data" + File.separator + "presets");		
		fileChooser.setCurrentDirectory(presetDir);
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setApproveButtonText("save");
		fileChooser.setDialogTitle("Save new preset");
		fileChooser.setFileFilter(new PresetFileFilter());
		fileChooser.setSelectedFile(new File(presetDir + File.separator + presetName + ".json"));

		int result = fileChooser.showOpenDialog(null);
		
		if(result != JFileChooser.APPROVE_OPTION)
		{
			return;
		}
		
		try 
		{
			File selected = fileChooser.getSelectedFile();
			selected.createNewFile();
			FileWriter data = new FileWriter(selected);
			data.write(json.toString());
			data.close();
		} 
		catch (IOException iox) 
		{
			iox.printStackTrace();
		}
	}
}
