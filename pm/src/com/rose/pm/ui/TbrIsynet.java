package com.rose.pm.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
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
	private JLabel lblPatBirth;
	private JTextField txtPatBirth;
	private JLabel lblPatNr;
	private JTextField txtPatNr;
	
	public TbrIsynet() {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
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
		
		lblPatBirth = new JLabel("lblPatBirth");
		lblPatBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPatBirth);
		
		txtPatBirth = new JTextField();
		txtPatBirth.setEditable(false);
		txtPatBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPatBirth);
		txtPatBirth.setColumns(10);
		
		lblPatNr = new JLabel("lblPatNr");
		lblPatNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPatNr);
		
		txtPatNr = new JTextField();
		txtPatNr.setEditable(false);
		txtPatNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPatNr);
		txtPatNr.setColumns(10);
		
		btnRefresh = new JButton("btnRefresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnRefresh);
	}

	public void setPatientData(Patient patient) {
		txtPatient.setText(patient.getSurname() + ", " + patient.getFirstname());
		txtPatBirth.setText(patient.getBirthday().getDayOfMonth() + "." + patient.getBirthday().getMonthValue() + "." + patient.getBirthday().getYear());
		txtPatNr.setText(String.valueOf(patient.getNumber()));
	}

	protected void setBtnRefreshIcon(ImageIcon iconRefresh) {
		btnRefresh.setIcon(iconRefresh);		
	}

	protected void setBtnRefreshText(String txt) {
		btnRefresh.setText(txt);		
	}

	protected void addUpdateListener(ActionListener listener) {
		btnRefresh.addActionListener(listener);		
	}

	protected void setLblPatientTxt(String txt) {
		lblPatient.setText(txt);		
	}
	
	protected void setLblIsynetText(String txt) {
		lblIsynet.setText(txt);
	}
	
	protected void setLblPatBirthText(String txt) {
		lblPatBirth.setText(txt);	
	}
	
	protected void setTxtPatBirth(LocalDate date) {
		if(date instanceof LocalDate) {
			txtPatBirth.setText(date.getDayOfMonth() + "." + date.getMonth() + "." + date.getYear());
		}else{
			txtPatBirth.setText("");
		}
	}
	
	protected void setLblPatNrText(String txt) {
		lblPatNr.setText(txt);
	}
	
	protected void setTxtPatientNr(Integer nr) {
		txtPatNr.setText(String.valueOf(nr));
	}
}

	
	