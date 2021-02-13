package com.rose.pm.ui;

import java.awt.Font;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.rose.pm.material.Manufacturer;

import net.miginfocom.swing.MigLayout;


public class PnlSICDType extends PnlBase {

	JLabel lblManufacturer;
	JComboBox<Manufacturer> cbxManufacturer;
	
	
	
	private static final long serialVersionUID = 5280759806419439713L;

	/**
	 * Create the panel.
	 */
	public PnlSICDType() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][grow][]", "[][][][]"));
		
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");		
		pnlInput.add(txtNotation, "cell 1 0");
		
		
		lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(font);
		pnlInput.add(lblManufacturer, "cell 2 0,alignx trailing");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(font);
		pnlInput.add(cbxManufacturer, "cell 3 0");	
		
		
		pnlInput.add(lblNotice, "cell 4 0");		
		pnlInput.add(txtNotice, "cell 5 0, growx");
		
		pnlInput.add(btnCreate, "cell 6 0");
		
		btnDelete.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlSouth.add(btnDelete);
			
	}

}
