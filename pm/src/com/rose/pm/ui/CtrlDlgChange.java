package com.rose.pm.ui;

import java.time.LocalDate;

import javax.swing.JDialog;

import com.rose.person.Patient;
import com.rose.pm.Ctrl_PnlSetDate;
import com.rose.pm.ui.Listener.NotationListener;
import com.rose.pm.ui.Listener.ProvideListener;


public class CtrlDlgChange {
	DlgChange dlgChange;
	Ctrl_PnlSetDate ctrlPnlSetDate;
	Listener listener;
	NotationListener serialNrListener, noticeListener;
	ProvideListener provideListener;
	Patient patient;
	
	
	
	protected JDialog getDialog() {
		return this.dlgChange;
	}
	
	public CtrlDlgChange() {
		
		initiateDialog();
		dlgChange.setLblSerialNrText("Seriennummer:");
		dlgChange.setLblNoticeText("Bemerkung:");
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now());
		ctrlPnlSetDate.getPanel().setLabelDateText("Ablaufdatum:");
		dlgChange.placePnlDate(ctrlPnlSetDate.getPanel());
		setListener();
		dlgChange.setProvidedPatientToCheckBox(provideListener.getActualPatient());
	}
	
	protected void initiateDialog() {
		dlgChange = new DlgChange(); 
	}
	
	protected void setListener() {
		listener = new Listener();
		serialNrListener = listener.new NotationListener();
		dlgChange.addSerialNrListener(serialNrListener);
		noticeListener = listener.new NotationListener();
		dlgChange.addNoticeListener(noticeListener);
		provideListener = listener.new ProvideListener();
		dlgChange.addProvideListener(provideListener);
	}
	
	
	
	
}
