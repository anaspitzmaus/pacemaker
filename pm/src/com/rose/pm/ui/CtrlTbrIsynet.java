package com.rose.pm.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.Isynet;
import com.rose.person.Patient;

public class CtrlTbrIsynet {

	TbrIsynet tbrIsynet;
	Isynet isynet;
	HashMap<Integer, String> patData;
	Patient patient;
	
	protected TbrIsynet getToolBar() {
		return tbrIsynet;
	}


	public CtrlTbrIsynet() {
		isynet = new Isynet();
		tbrIsynet = new TbrIsynet();
		readPatInfo();
		if(patData != null) {
			createPatient();
			if(patient instanceof Patient) {
				insertPatient();
			}
		}
	}
	
	private void readPatInfo(){
		
		try {
			patData = isynet.readPatInfo();
			//create a new Patient out of the data 
			//put the patients name to the menuBar
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dateipfad" , JOptionPane.ERROR_MESSAGE);
		}catch(StringIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage() + "PatInfo file is empty", "leere PatInfo Datei" , JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dateipfad" , JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	/**
	 * create a patient out of the file data
	 */
	private void createPatient() {
		String lastname, firstname;
		
		if(!patData.isEmpty()) {
			
			String patName = patData.get(3301);
			String[] val= patName.split(",");
			if(val.length == 2) {
				lastname = val[0];
				firstname = val[1];
				patient = new Patient(lastname, firstname);
			}
		}
	}
	
	/**
	 * insert the patient data to the toolBar
	 */
	protected void insertPatient() {
		tbrIsynet.setPatientData(patient);
	}
}
