package com.rose.pm.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.dataExchange.Isynet;
import com.rose.person.Patient;
import com.rose.pm.MyImages;

public class CtrlTbrIsynet {

	TbrIsynet tbrIsynet;
	Isynet isynet;
	HashMap<Integer, String> patData;
	Patient patient;
	UpdateListener updateListener;
	MyImages myImages;
	
	protected TbrIsynet getToolBar() {
		return tbrIsynet;
	}


	public CtrlTbrIsynet() {
		myImages = new MyImages();
		isynet = new Isynet();
		tbrIsynet = new TbrIsynet();
		setListener();		
		readPatInfo();
		if(patData != null) {
			createPatient();
			if(patient instanceof Patient) {
				insertPatient();
			}
		}
		
		ImageIcon iconRefresh = new ImageIcon(myImages.getImage("images/refresh_16.png"));
		iconRefresh.getImage();
		tbrIsynet.setBtnRefreshIcon(iconRefresh);
		tbrIsynet.setBtnRefreshText("");
		tbrIsynet.setLblPatientTxt("Patient:");
		tbrIsynet.setLblIsynetText("Isynet");
		tbrIsynet.setLblPatBirthText("geb. am:");
		tbrIsynet.setLblPatNrText("Patientennummer:");
	}
	
	private void setListener() {
		updateListener = new UpdateListener();
		tbrIsynet.addUpdateListener(updateListener);
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
			//surname and firstname
			String patName = patData.get(3301);
			String[] val= patName.split(",");
			if(val.length == 2) {
				lastname = val[0];
				firstname = val[1];
				patient = new Patient(lastname, firstname);
			}
			//birthday
			String birth = patData.get(3103);
			
			Integer day = Integer.valueOf(birth.substring(0, 2));
			Integer month = Integer.valueOf(birth.substring(2, 4));
			Integer year = Integer.valueOf(birth.substring(4, 8));
			
			
			LocalDate birthday = LocalDate.of(year, month, day);
			
			patient.setBirthday(birthday);
			
			//number of patient
			Integer nr = Integer.parseInt(patData.get(3600));
			patient.setNumber(nr);
		}
	}
	
	/**
	 * insert the patient data to the toolBar
	 */
	protected void insertPatient() {
		tbrIsynet.setPatientData(patient);
		
	}
	

	
	/**
	 * listener for refreshing the active patient as set in isynet
	 * @author Ekki
	 *
	 */
	class UpdateListener implements ActionListener{
		Isynet isynet;
		@Override
		public void actionPerformed(ActionEvent e) {
			readPatInfo();
			if(patData != null) {
				createPatient();
				if(patient instanceof Patient) {
					insertPatient();
				}
			}
		}
		
	}
}
