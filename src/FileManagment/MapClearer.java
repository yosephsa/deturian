/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FileManagment;

import java.io.File;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public class MapClearer {
	
	/**
	 * clears everything within the gien directory
	 * @param dir 
	 */
	public static void clearDir(File dir)
	{
		if(!dir.exists())
			return;
		for (File file: dir.listFiles())
		{
			if (file.isDirectory())
				clearDir(file);
			file.delete();
		}
	}
	
	/**
	 * deletes everything including the given path directory.
	 * @param path 
	 */
	public static void clearDir(String path)
	{
		File dir = new File( System.getProperty("user.home") + "\\.Deturian\\saves\\" + path);
		for (File file: dir.listFiles())
		{
			if (file.isDirectory())
				clearDir(file);
			file.delete();
		}
		dir.delete();
	}

}
