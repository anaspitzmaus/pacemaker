package com.rose.pm.ui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class FrmMenuBar extends JMenuBar{
	JButton btnManufacturer;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4199586390816416921L;
	
	public FrmMenuBar() {
		btnManufacturer = new JButton("btnManufacturer");
		btnManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnManufacturer);
		
	}
	
	protected void addManufacturerListener(ActionListener l) {
		btnManufacturer.addActionListener(l);
	}

	

}
