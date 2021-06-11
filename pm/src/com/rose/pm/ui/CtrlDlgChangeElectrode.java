package com.rose.pm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.rose.pm.db.SQL_UPDATE;
import com.rose.pm.material.Electrode;


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
		
		priceFormat = DecimalFormat.getInstance();
		priceFormat.setMinimumFractionDigits(2);
		priceFormat.setMaximumFractionDigits(2);
		DefaultFormatterFactory dff = new DefaultFormatterFactory(new NumberFormatter(priceFormat), new NumberFormatter(priceFormat), new NumberFormatter(NumberFormat.getNumberInstance()));
		dlgChange.setPriceFormatter(dff);
		dlgChange.setPriceValue(this.electrode.getPrice());
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
				electrode.setPrice(priceListener.getPrice());

				updateDBAndTblModel();				
			}
		}
		
		
		protected void updateDBAndTblModel() {
			SQL_UPDATE.electrode(electrode);
			
			//((ElectrodeTblModel)model).setElectrodes(SQL_SELECT.electrodes(electrode.getElectrodeType()));
			model.fireTableDataChanged();
			dlgChange.dispose();
		}
		
	}
}
