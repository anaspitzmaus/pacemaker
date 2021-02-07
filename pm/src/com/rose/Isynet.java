package com.rose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

import com.rose.person.Patient;
import com.rose.pm.ui.settings.CtrlSetPathPatInfo;

public class Isynet {

	static Preferences prefs;
	Path path;
	
	public static Patient getActualPatient() {
		
		Patient patient = new Patient("Test", "Fritz");
		return patient;
		
	}
	
	public void readPatInfo() {
		BufferedReader reader;
		
		
		prefs = Preferences.userNodeForPackage(CtrlSetPathPatInfo.class);
		String patInfoPath = prefs.get("Path_PatInfo", "");
		path = Paths.get( patInfoPath);
		if(patInfoPath != "") {
			try {
				File file = new File(patInfoPath);
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while(line!= null) {
					System.out.println(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	
}
