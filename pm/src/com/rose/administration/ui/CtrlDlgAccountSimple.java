package com.rose.administration.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import com.rose.administration.AccountingType;
import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.examination.E_ExamKinds;
import com.rose.pm.examination.ExamKind;
import com.rose.pm.material.Material;
import com.rose.pm.material.Status;

public class CtrlDlgAccountSimple {

	DlgAccountSimple dlgAccountSimple;
	DefaultComboBoxModel<AccountingType> accountingTypeModel;
	DefaultComboBoxModel<E_ExamKinds > examKindModel;
	Material material;
	Patient patient;
	ExamKind examKind;
	OKListener okListener;
	AbstractTableModel tblModel;
	Ctrl_PnlSetDate ctrlPnlSetDate;
	AccountListener accountListener;
	
	public DlgAccountSimple getDialog() {
		return dlgAccountSimple;
	}
	
	public CtrlDlgAccountSimple(Patient patient, Material material, AbstractTableModel tblModel) {
		this.material = material;
		this.patient = patient;
		this.tblModel = tblModel;
		dlgAccountSimple = new DlgAccountSimple();	
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(1));
		dlgAccountSimple.addDatePanel(ctrlPnlSetDate.getPanel());
		setComponentText();
		setModel();
		setListener();
		
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
	
	private void setListener() {
		okListener = new OKListener();
		dlgAccountSimple.addOKListener(okListener);
		accountListener = new AccountListener();
		dlgAccountSimple.addAccountListener(accountListener);
		accountListener.accType = dlgAccountSimple.getAccountingType(); //that the first (selected) item to the variable		
	}
	
	class OKListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			material.setStatus(Status.Implantiert);
			Date date = Date.from(ctrlPnlSetDate.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			material.setDateOfImplantation(date);
			material.setAccountingType(accountListener.getAccountingType());
			tblModel.fireTableDataChanged();
			try {
				SQL_UPDATE.setPatProvided(material);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlgAccountSimple.dispose();
		}
		
	}
	
	class AccountListener implements ActionListener{
		AccountingType accType;
		
		protected AccountingType getAccountingType() {
			return this.accType;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<AccountingType> cb = (JComboBox<AccountingType>)e.getSource();
	        accType = (AccountingType)cb.getSelectedItem();			
		}
		
	}
	
}
