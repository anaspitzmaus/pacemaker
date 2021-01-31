package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.AbstractTableModel;



import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Electrode;
import com.rose.pm.ui.CtrlPnlElectrode.ElectrodeTblModel;

public class CtrlDlgChangeElectrode extends CtrlDlgChange {
	
	Electrode electrode;
	AbstractTableModel model;
	CreateListener createListener;
	
	public CtrlDlgChangeElectrode(Electrode electrode, AbstractTableModel model) {
		this.electrode = electrode;
		this.model = model;
	
		dlgChange.setSerialNrText(this.electrode.getSerialNr());
		dlgChange.setNoticeText(this.electrode.getNotice());
		ctrlPnlSetDate.setDate(electrode.getExpireDate());
		setCreateListener();
	}
	
	protected void initiateDialog() {
		dlgChange = new DlgChangeElectrode();
		
	}
	
	private void setCreateListener() {
		createListener = new CreateListener();
		dlgChange.addCreateListener(createListener);
	}
	
	class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "") {
				electrode.setExpireDate(ctrlPnlSetDate.getDate());
				electrode.setSerialNr(serialNrListener.getNotation());
				electrode.setNotice(noticeListener.getNotation());
				if(provideListener.isPatientProvided()) {
					electrode.setPatient(provideListener.getActualPatient());
				}
				updateDBAndTblModel();				
			}
		}
		
		
		protected void updateDBAndTblModel() {
			SQL_UPDATE.electrode(electrode);				
			((ElectrodeTblModel)model).setElectrodes(SQL_SELECT.electrodes(electrode.getElectrodeType()));
			model.fireTableDataChanged();
			dlgChange.dispose();
		}
		
	}
}
