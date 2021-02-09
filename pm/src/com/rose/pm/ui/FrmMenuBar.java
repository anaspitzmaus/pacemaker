package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;



import java.awt.Color;

public class FrmMenuBar extends JMenuBar{
	JButton btnManufacturer;
	JButton btnSettings;
	
	Font font;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4199586390816416921L;
	
	public FrmMenuBar() {
		setBackground(Color.ORANGE);
		font = new Font("Tahoma", Font.PLAIN, 14);
		
		btnManufacturer = new JButton("btnManufacturer");
		btnManufacturer.setBackground(new Color(0, 206, 209));
		btnManufacturer.setFont(font);
		add(btnManufacturer);
		
		
		
		btnSettings = new JButton("btnSettings");
		btnSettings.setForeground(new Color(0, 0, 0));
		btnSettings.setBackground(new Color(0, 206, 209));
		btnSettings.setFont(font);
		add(Box.createHorizontalGlue());  //horizontal glue
		add(btnSettings);
		
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

	

	

}
