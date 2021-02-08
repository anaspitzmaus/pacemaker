package com.rose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.rose.person.Patient;
import com.rose.pm.ui.settings.CtrlSetPathPatInfo;

public class Isynet{

	static Preferences prefs;
	Path path;
	
	public static Patient getActualPatient() {
		
		Patient patient = new Patient("Test", "Fritz");
		return patient;
		
	}
	
	public HashMap<Integer, String> readPatInfo() throws FileNotFoundException, IOException, StringIndexOutOfBoundsException {
		BufferedReader reader;
		HashMap<Integer, String> data = new HashMap<Integer, String>();
		
		prefs = Preferences.userNodeForPackage(CtrlSetPathPatInfo.class);
		String patInfoPath = prefs.get("Path_PatInfo", "");
		path = Paths.get( patInfoPath);
		if(patInfoPath != "") {
			
			File file = new File(patInfoPath, "Patinf02.tmp");
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line!= null) {//for each line in the file
				String k = line.substring(0, 4); //get the key
				String v = line.substring(5);//get the value
				data.put(Integer.parseInt(k), v); //put the pair to the HashMap
				line = reader.readLine();//read next line
			}
			reader.close();					
		}
		return data;
	}
	
	
}
