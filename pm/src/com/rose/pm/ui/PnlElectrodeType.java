package com.rose.pm.ui;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.rose.pm.material.Manufacturer;

import net.miginfocom.swing.MigLayout;

public class PnlElectrodeType extends PnlBase {

	JTextField txtNotation;
	JTextField txtNotice;
	JSpinner spinLength;
	/**
	 * 
	 */
	private static final long serialVersionUID = 113685124454489212L;

	/**
	 * Create the panel.
	 */
	public PnlElectrodeType() {
		pnlInput.setLayout(new MigLayout("", "[][][][][][][][][][grow]", "[]"));
		
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
		
		JLabel lblLength = new JLabel("lblLength");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblLength,  "cell 6 0");
		
		spinLength = new JSpinner();
		spinLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinLength.setModel(new SpinnerNumberModel(50, 40, 60, 1));
		pnlInput.add(spinLength, "cell 7 0");
		
		JLabel lblNotice = new JLabel("lblNotice");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(lblNotice, "cell 8 0");
		
		txtNotice = new JTextField();
		txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInput.add(txtNotice, "cell 9 0, growx");
		txtNotice.setColumns(10);
		
	}

}
