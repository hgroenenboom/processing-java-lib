package presets;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import processing.data.JSONObject;

public class PresetLoader 
{
	public static JSONObject load()
	{
		JFileChooser fileChooser = new JFileChooser();
		File presetDir = new File("data" + File.separator + "presets");
		presetDir.mkdirs();
		fileChooser.setCurrentDirectory(presetDir);
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setApproveButtonText("load");
		fileChooser.setDialogTitle("Load an existing preset");
		fileChooser.setFileFilter(new PresetFileFilter());
		
		int result = fileChooser.showOpenDialog(null);
		
		if(result != JFileChooser.APPROVE_OPTION)
		{
			return null;
		}
		
		try 
		{
			File selected = fileChooser.getSelectedFile();

		    String data = readFile(selected.getAbsolutePath(), StandardCharsets.UTF_8);
			return JSONObject.parse(data);
		} 
		catch (IOException iox) 
		{
			iox.printStackTrace();
		}
		
		return null;
	}
	
	private static String readFile(String path, Charset encoding)
			throws IOException
	{
	    byte[] encoded = Files.readAllBytes(Paths.get(path));
	    return new String(encoded, encoding);
	}
}
