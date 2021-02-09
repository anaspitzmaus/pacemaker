package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.rose.person.Patient;

public class TbrIsynet extends JToolBar{

	private static final long serialVersionUID = -448715487081035921L;

	private JLabel lblIsynet;
	private JTextField txtPatient;
	private JLabel lblPatient;
	private JButton btnRefresh;
	private Component horizontalStrut;
	private JSeparator separator;
	private Component horizontalStrut_1;
	
	public TbrIsynet() {
		lblIsynet = new JLabel("lblIsynet");
		lblIsynet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblIsynet);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 128, 0));
		separator.setForeground(new Color(0, 0, 0));
		add(separator);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1);
		
		lblPatient = new JLabel("lblPatient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPatient);
		
		txtPatient = new JTextField();
		txtPatient.setHorizontalAlignment(SwingConstants.LEFT);
		txtPatient.setEditable(false);
		txtPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPatient);
		txtPatient.setColumns(10);
		
		btnRefresh = new JButton("btnRefresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnRefresh);
	}

	public void setPatientData(Patient patient) {
		txtPatient.setText(patient.getSurname() + ", " + patient.getFirstname());
		
	}

	protected void setBtnRefreshIcon(ImageIcon iconRefresh) {
		btnRefresh.setIcon(iconRefresh);		
	}

	protected void setBtnRefreshText(String txt) {
		btnRefresh.setText(txt);		
	}
	
	
}
