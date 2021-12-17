package model;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter {

	private String extension;
	private String descripttion;
	
	
	
	public FileTypeFilter(String extension, String descripttion) {
		super();
		this.extension = extension;
		this.descripttion = descripttion;
	}

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		
		if(f.isDirectory()) {
			return true;
		}
		return f.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return descripttion + String.format(" (*%s)", extension);
	}

}
