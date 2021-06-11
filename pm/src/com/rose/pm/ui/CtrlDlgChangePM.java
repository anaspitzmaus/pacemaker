package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;


import com.rose.pm.db.SQL_UPDATE;

import com.rose.pm.material.PM;


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
		
		priceFormat = DecimalFormat.getInstance();
		priceFormat.setMinimumFractionDigits(2);
		priceFormat.setMaximumFractionDigits(2);
		DefaultFormatterFactory dff = new DefaultFormatterFactory(new NumberFormatter(priceFormat), new NumberFormatter(priceFormat), new NumberFormatter(NumberFormat.getNumberInstance()));
		dlgChange.setPriceFormatter(dff);
		dlgChange.setPriceValue(this.pm.getPrice());
		
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
				pm.setPrice(priceListener.getPrice());
				
				updateDBAndTblModel();
				
			}
		}
		
		
		protected void updateDBAndTblModel() {
			SQL_UPDATE.Pacemaker(pm);				
					
			model.fireTableDataChanged();
			dlgChange.dispose();
		}
		
	}
}
