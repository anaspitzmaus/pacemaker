package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

public class FrmMenuBar extends JMenuBar{
	JButton btnManufacturer;
	JButton btnSettings;
	JLabel lblPatient;
	JButton btnUpdatePatient;
	Font font;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4199586390816416921L;
	
	public FrmMenuBar() {
		font = new Font("Tahoma", Font.PLAIN, 14);
		btnManufacturer = new JButton("btnManufacturer");
		btnManufacturer.setFont(font);
		add(btnManufacturer);
		
		lblPatient = new JLabel("lblPatient");
		lblPatient.setFont(font);
		add(lblPatient);
		
		btnUpdatePatient = new JButton();
		btnUpdatePatient.setFont(font);
		add(btnUpdatePatient);
		
		btnSettings = new JButton("btnSettings");
		btnSettings.setFont(font);
		add(Box.createHorizontalGlue());  //horizontal glue
		add(btnSettings);
		
	}
	
	protected void setLblPatientText(String txt) {
		lblPatient.setText(txt);
	}
	
	protected void setBtnManufacturerText(String txt) {
		btnManufacturer.setText(txt);
	}
	
	protected void addManufacturerListener(ActionListener l) {
		btnManufacturer.addActionListener(l);
	}

	protected void setBtnSettingsIcon(ImageIcon icon) {
		btnSettings.setIcon(icon);		
	}

	protected void addSettingsListener(ActionListener settingsListener) {
		btnSettings.addActionListener(settingsListener);
		
	}

	protected void setBtnSettingsText(String txt) {
		btnSettings.setText(txt);
		
	}

	protected void setBtnUpdateIcon(ImageIcon icon) {
		btnUpdatePatient.setIcon(icon);		
	}
	
	protected void setBtnUpdateText(String txt) {
		btnUpdatePatient.setText(txt);
	}

	

}
