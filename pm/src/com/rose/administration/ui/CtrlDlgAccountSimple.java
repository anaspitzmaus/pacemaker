package com.rose.administration.ui;

import javax.swing.DefaultComboBoxModel;

import com.rose.administration.AccountingType;

public class CtrlDlgAccountSimple {

	DlgAccountSimple dlgAccountSimple;
	DefaultComboBoxModel<AccountingType> accountingTypeModel;

	public DlgAccountSimple getDialog() {
		return dlgAccountSimple;
	}
	
	public CtrlDlgAccountSimple() {
		dlgAccountSimple = new DlgAccountSimple();	
		accountingTypeModel = new DefaultComboBoxModel<>(AccountingType.values());
	}
	
	
	
}
