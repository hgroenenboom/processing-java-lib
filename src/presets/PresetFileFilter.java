package presets;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PresetFileFilter extends FileFilter
{
	@Override
	public boolean accept(File file) 
	{
		return file.isFile() && checkExtension(file, "json");
	}

	@Override
	public String getDescription() {
		return "JSON preset files";
	}

	private boolean checkExtension(File file, String extension)
	{
		return file.getAbsolutePath().endsWith(extension);
	}
}
