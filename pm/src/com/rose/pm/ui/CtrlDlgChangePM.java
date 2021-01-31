package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.AbstractTableModel;


import com.rose.pm.db.SQL_SELECT;
import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.PM;
import com.rose.pm.ui.CtrlPnlPM.AggregateTblModel;

public class CtrlDlgChangePM extends CtrlDlgChange{
	
	PM pm;
	AbstractTableModel model;
	CreateListener createListener;
	
	public CtrlDlgChangePM(PM pm, AbstractTableModel model) {
		
		this.pm = pm;
		this.model = model;
		dlgChange.setSerialNrText(this.pm.getSerialNr());
		dlgChange.setNoticeText(this.pm.getNotice());
		ctrlPnlSetDate.setDate(pm.getExpireDate());
		setCreateListener();
	}
	
	protected void initiateDialog() {
		dlgChange = new DlgChangeElectrode();
		
	}
	
	protected void setCreateListener() {
		createListener = new CreateListener();
		dlgChange.addCreateListener(createListener);
	}
	
	class CreateListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(serialNrListener.getNotation() != "") {
				pm.setExpireDate(ctrlPnlSetDate.getDate());
				pm.setSerialNr(serialNrListener.getNotation());
				pm.setNotice(noticeListener.getNotation());
				if(provideListener.isPatientProvided()) {
					pm.setPatient(provideListener.getActualPatient());
				}
				
				updateDBAndTblModel();
				model.fireTableDataChanged();
				dlgChange.dispose();
			}
		}
		
		
		protected void updateDBAndTblModel() {
			SQL_UPDATE.Pacemaker(pm);				
			((AggregateTblModel)model).setAggregats(SQL_SELECT.pacemakers(pm.getAggregatModel()));			
		}
		
	}
}
