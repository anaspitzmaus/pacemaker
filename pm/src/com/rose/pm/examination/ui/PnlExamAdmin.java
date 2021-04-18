package com.rose.pm.examination.ui;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.rose.administration.AccountingType;
import com.rose.person.Nurse;
import com.rose.person.Physician;
import com.rose.pm.examination.Examination;

import net.miginfocom.swing.MigLayout;

public class PnlExamAdmin extends JPanel {

	
	private static final long serialVersionUID = -6484706790520290275L;
	private JTextField txtPatient;
	private JTextField txtPatBirth;
	private JTextField txtCaseNr;
	private JTextField txtPatNr;
	JLabel lblPatient;
	JLabel lblPatBirth;
	JLabel lblPatNr;
	JLabel lblBilling;
	JComboBox<AccountingType> cbxBilling;
	JLabel lblCaseNr;
	JComboBox<Physician> cbxPhysician;
	JLabel lblPhysician;
	JLabel lblPhysicianAssist;
	JComboBox<Physician> cbxPhysicianAssist;
	JComboBox<Nurse> cbxNurse, cbxNurseAssist;
	JLabel lblNurse, lblNurseAssist;
	JLabel lblDateStart;
	JFormattedTextField ftxtDateStart;
	private JLabel lblExamKind;
	private JComboBox<Examination> cbxExamKind;
	
	public PnlExamAdmin() {
		setLayout(new MigLayout("", "[][grow][][grow][][grow][][]", "[][][][][]"));
		
		lblPatient = new JLabel("lblPatient");
		lblPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPatient, "cell 0 0,alignx left");
		
		txtPatient = new JTextField();
		txtPatient.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPatient.setText("");
		add(txtPatient, "cell 1 0,growx");
		txtPatient.setColumns(10);
		
		lblPatBirth = new JLabel("lblPatBirth");
		lblPatBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPatBirth, "flowx,cell 2 0,alignx left");
		
		txtPatBirth = new JTextField();
		txtPatBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPatBirth, "cell 3 0");
		txtPatBirth.setColumns(10);
		
		lblPatNr = new JLabel("lblPatNr");
		lblPatNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPatNr, "cell 4 0,alignx trailing");
		
		txtPatNr = new JTextField();
		txtPatNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPatNr.setText("");
		add(txtPatNr, "cell 5 0,growx");
		txtPatNr.setColumns(10);
		
		lblBilling = new JLabel("lblBilling");
		lblBilling.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblBilling, "cell 0 1,alignx left");
		
		cbxBilling = new JComboBox<AccountingType>();
		cbxBilling.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxBilling, "cell 1 1,growx");
		
		lblCaseNr = new JLabel("lblCaseNr");
		lblCaseNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblCaseNr, "cell 2 1,alignx left");
		
		txtCaseNr = new JTextField();
		txtCaseNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtCaseNr, "cell 3 1,growx");
		txtCaseNr.setColumns(10);
		
		lblPhysician = new JLabel("lblPhysician");
		lblPhysician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPhysician, "cell 0 2,alignx trailing");
		
		cbxPhysician = new JComboBox<Physician>();
		cbxPhysician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxPhysician, "cell 1 2,growx");
		
		lblPhysicianAssist = new JLabel("lblPhysicianAssist");
		lblPhysicianAssist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPhysicianAssist, "cell 2 2,alignx trailing");
		
		cbxPhysicianAssist = new JComboBox<Physician>();
		cbxPhysicianAssist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxPhysicianAssist, "cell 3 2,growx");
		
		lblNurse = new JLabel("lblNurse");
		lblNurse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNurse, "cell 4 2,alignx left");
		
		cbxNurse = new JComboBox<Nurse>();
		cbxNurse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxNurse, "cell 5 2,growx");
		
		lblNurseAssist = new JLabel("lblNurseAssist");
		lblNurseAssist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNurseAssist, "cell 6 2,alignx left");
		
		cbxNurseAssist = new JComboBox<Nurse>();
		cbxNurseAssist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxNurseAssist, "cell 7 2,growx");
		
		lblDateStart = new JLabel("lblDateStart");
		lblDateStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblDateStart, "cell 0 3,alignx trailing");
		
		ftxtDateStart = new JFormattedTextField();
		ftxtDateStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(ftxtDateStart, "cell 1 3,growx");
		
		lblExamKind = new JLabel("lblExamKind");
		lblExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblExamKind, "cell 0 4,alignx trailing");
		
		cbxExamKind = new JComboBox<Examination>();
		cbxExamKind.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxExamKind, "cell 1 4,growx");

	}

}
