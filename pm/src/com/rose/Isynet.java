package com.rose;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.person.Patient;
import com.rose.pm.ui.settings.CtrlSetPathPatInfo;

public class Isynet{

	static Preferences prefs;
	Path path;
	
//	public Patient getActualPatient() {
//		
//		Patient patient = new Patient("Test", "Fritz");
//		return patient;
//		
//	}
	
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
	
	public Patient getPatient() {
		Patient patient = null;
		String lastname, firstname;
		HashMap<Integer, String> data = null;;
		try {
			data = readPatInfo();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dateipfad" , JOptionPane.ERROR_MESSAGE);
		}catch(StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage() + "PatInfo file is empty", "leere PatInfo Datei" , JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dateipfad" , JOptionPane.ERROR_MESSAGE);
		}	
		
		
		
		if(data != null && !data.isEmpty()) {
			
			String patName = data.get(3301);
			String[] val= patName.split(",");
			if(val.length == 2) {
				lastname = val[0];
				firstname = val[1];
				patient = new Patient(lastname, firstname);
				
				//birthday
				String birth = data.get(3103);
				
				Integer day = Integer.valueOf(birth.substring(0, 2));
				Integer month = Integer.valueOf(birth.substring(2, 4));
				Integer year = Integer.valueOf(birth.substring(4, 8));
				
				
				LocalDate birthday = LocalDate.of(year, month, day);
				
				patient.setBirthday(birthday);
				
				//number of patient
				Integer nr = Integer.parseInt(data.get(3600));
				patient.setNumber(nr);
			}
		}
		
		return patient;
	}
	
	
}
