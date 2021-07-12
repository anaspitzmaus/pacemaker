package com.rose.administration.ui;

import javax.swing.DefaultComboBoxModel;

import com.rose.administration.AccountingType;
import com.rose.person.Patient;
import com.rose.pm.examination.E_ExamKinds;
import com.rose.pm.examination.ExamKind;
import com.rose.pm.material.Material;

public class CtrlDlgAccountSimple {

	DlgAccountSimple dlgAccountSimple;
	DefaultComboBoxModel<AccountingType> accountingTypeModel;
	DefaultComboBoxModel<E_ExamKinds > examKindModel;
	Material material;
	Patient patient;
	ExamKind examKind;	
	
	public DlgAccountSimple getDialog() {
		return dlgAccountSimple;
	}
	
	public CtrlDlgAccountSimple(Patient patient, Material material) {
		this.material = material;
		this.patient = patient;
		dlgAccountSimple = new DlgAccountSimple();	
		setComponentText();
		setModel();
		
	}
	
	private void setComponentText() {
		dlgAccountSimple.getLblExamNr().setText("Untersuchungsnummer:");
		dlgAccountSimple.getLblInsurance().setText("Versicherung:");
		dlgAccountSimple.getLblAccount().setText("Abrechnung:");
		dlgAccountSimple.getLblDate().setText("Untersuchungsdatum:");
		dlgAccountSimple.getLblExamKind().setText("Untersuchungsart:");
		
		dlgAccountSimple.getTxtInsurance().setText(patient.getInsurance().getNotation());		
	}
	
	private void setModel() {
		accountingTypeModel = new DefaultComboBoxModel<>(AccountingType.values());
		dlgAccountSimple.getCbxAccount().setModel(accountingTypeModel);	
		
		examKindModel = new DefaultComboBoxModel<>(E_ExamKinds.values());
		dlgAccountSimple.getCbxExamKind().setModel(examKindModel);
		
	}
	
}
