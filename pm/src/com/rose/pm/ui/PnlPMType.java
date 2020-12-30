package com.rose.pm.ui;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.rose.pm.material.Manufacturer;

import net.miginfocom.swing.MigLayout;

public class PnlPMType extends PnlBase{
	JTextField txtNotation;
	JTextField txtNotice;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2047308671221451668L;

	/**
	 * Create the panel.
	 */
	public PnlPMType() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][grow]", "[]"));
		
		JLabel lblNotation = new JLabel("lblNotation");
		lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblNotation, "cell 0 0,alignx trailing");
		
		txtNotation = new JTextField();
		txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(txtNotation, "cell 1 0");
		txtNotation.setColumns(10);
		
		JLabel lblManufacturer = new JLabel("lblManufacturer");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblManufacturer, "cell 2 0,alignx trailing");
		
		JComboBox<Manufacturer> cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(cbxManufacturer, "cell 3 0");
		
		JLabel lblMRI = new JLabel("lblMRI");
		lblMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblMRI,  "cell 4 0");
		
		JCheckBox checkMRI = new JCheckBox();
		pnlInput.add(checkMRI, "cell 5 0");
		
		JLabel lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblNotice, "cell 6 0");
		
		txtNotice = new JTextField();
		txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(txtNotice, "cell 7 0, growx");
		txtNotice.setColumns(10);
	}

}
